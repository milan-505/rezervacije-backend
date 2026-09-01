/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.service;

import com.rezervacije.rezervacijebackend.domain.RezervacijaDTO;
import com.rezervacije.rezervacijebackend.jparepo.DogadjajRepository;
import com.rezervacije.rezervacijebackend.jparepo.KorisnikRepository;
import com.rezervacije.rezervacijebackend.jparepo.RezervacijaRepository;
import com.rezervacije.rezervacijebackend.jparepo.StoRepository;
import com.rezervacije.rezervacijebackend.mapper.RezervacijaMapper;
import com.rezervacije.rezervacijebackend.model.Dogadjaj;
import com.rezervacije.rezervacijebackend.model.Korisnik;
import com.rezervacije.rezervacijebackend.model.Rezervacija;
import com.rezervacije.rezervacijebackend.model.StatusRezervacije;
import com.rezervacije.rezervacijebackend.model.Sto;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milan
 */

@Service
public class RezervacijaService {

    private final RezervacijaRepository rezervacijaRepository;
    private final RezervacijaMapper rezervacijaMapper;
    private final KorisnikRepository korisnikRepository;
    private final StoRepository stoRepository;
    private final DogadjajRepository dogadjajRepository;

    public RezervacijaService(RezervacijaRepository rezervacijaRepository, RezervacijaMapper rezervacijaMapper,
                               KorisnikRepository korisnikRepository, StoRepository stoRepository,
                               DogadjajRepository dogadjajRepository) {
        this.rezervacijaRepository = rezervacijaRepository;
        this.rezervacijaMapper = rezervacijaMapper;
        this.korisnikRepository = korisnikRepository;
        this.stoRepository = stoRepository;
        this.dogadjajRepository = dogadjajRepository;
    }

    /**
     * Kreira rezervaciju. Korisnik koji je vlasnik rezervacije se cita iz
     * sesije (korisnikId), a ne iz tela zahteva - da niko ne moze da
     * rezervise "u ime" nekog drugog korisnika.
     */
    public String create(RezervacijaDTO dto, Long korisnikId) {
        try {
            if (korisnikId == null) {
                return "Niste prijavljeni.";
            }
            if (dto.getSto() == null || dto.getSto().getIdSto() == null) {
                return "Sto mora biti izabran.";
            }
            if (dto.getDogadjaj() == null || dto.getDogadjaj().getIdDogadjaj() == null) {
                return "Dogadjaj mora biti izabran.";
            }
            if (dto.getBrojGostiju() <= 0) {
                return "Broj gostiju mora biti veci od nule.";
            }

            Korisnik korisnik = korisnikRepository.findById(korisnikId)
                    .orElseThrow(() -> new RuntimeException("Korisnik nije pronadjen."));
            Sto sto = stoRepository.findById(dto.getSto().getIdSto())
                    .orElseThrow(() -> new RuntimeException("Sto nije pronadjen."));
            Dogadjaj dogadjaj = dogadjajRepository.findById(dto.getDogadjaj().getIdDogadjaj())
                    .orElseThrow(() -> new RuntimeException("Dogadjaj nije pronadjen."));

            if (dogadjaj.getDatum() != null && dogadjaj.getDatum().isBefore(LocalDate.now())) {
                return "Ne mozete rezervisati sto za dogadjaj koji je vec prosao.";
            }

            if (dto.getBrojGostiju() > sto.getKapacitet()) {
                return "Broj gostiju (" + dto.getBrojGostiju() + ") prevazilazi kapacitet stola (" + sto.getKapacitet() + ").";
            }

            if (sto.getProstorija() == null || sto.getProstorija().getMestoOdrzavanja() == null
                    || dogadjaj.getMestoOdrzavanja() == null
                    || !sto.getProstorija().getMestoOdrzavanja().getIdMestoOdrzavanja()
                            .equals(dogadjaj.getMestoOdrzavanja().getIdMestoOdrzavanja())) {
                return "Izabrani sto se ne nalazi u mestu odrzavanja izabranog dogadjaja.";
            }

            boolean zauzet = !rezervacijaRepository.findByStoAndDogadjajAndStatusIn(
                    sto, dogadjaj, List.of(StatusRezervacije.NA_CEKANJU, StatusRezervacije.POTVRDJENA)).isEmpty();
            if (zauzet) {
                return "Izabrani sto je vec rezervisan (ili je rezervacija na cekanju) za ovaj dogadjaj.";
            }

            RezervacijaDTO zaCuvanje = new RezervacijaDTO();
            zaCuvanje.setBrojGostiju(dto.getBrojGostiju());
            zaCuvanje.setDatumRezervacije(dto.getDatumRezervacije() != null ? dto.getDatumRezervacije() : LocalDate.now());
            zaCuvanje.setStatus(StatusRezervacije.NA_CEKANJU); // uvek isto na pocetku, ignorisemo sta posalje frontend

            Rezervacija rezervacija = rezervacijaMapper.toRezervacijaEntity(zaCuvanje, korisnik, dogadjaj, sto, null);
            rezervacijaRepository.save(rezervacija);
            return "Rezervacija je uspesno kreirana!";
        } catch (Exception ex) {
            return "Rezervacija nije mogla da se kreira!";
        }
    }

    @Transactional
    public String odluciORezervaciji(Long rezervacijaId, Long adminId, boolean potvrdjeno) {
        try {
            if (adminId == null) {
                return "Niste prijavljeni.";
            }
            Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaId)
                    .orElseThrow(() -> new RuntimeException("Rezervacija nije pronadjena."));
            Korisnik admin = korisnikRepository.findById(adminId)
                    .orElseThrow(() -> new RuntimeException("Admin nije pronadjen."));

            if (rezervacija.getStatus() != StatusRezervacije.NA_CEKANJU) {
                return "Rezervacija je vec obradjena (trenutni status: " + rezervacija.getStatus() + ").";
            }

            rezervacija.setStatus(potvrdjeno ? StatusRezervacije.POTVRDJENA : StatusRezervacije.ODBIJENA);
            rezervacija.setAdmin(admin);
            rezervacijaRepository.save(rezervacija);

            return potvrdjeno ? "Rezervacija je potvrdjena!" : "Rezervacija je odbijena!";
        } catch (Exception ex) {
            return "Greska prilikom obrade rezervacije!";
        }
    }

    /**
     * Otkazivanje rezervacije od strane gosta - samo dok je na cekanju i
     * samo ako je zaista njegova.
     */
    @Transactional
    public String cancel(Long rezervacijaId, Long korisnikId) {
        try {
            if (korisnikId == null) {
                return "Niste prijavljeni.";
            }
            Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaId)
                    .orElseThrow(() -> new RuntimeException("Rezervacija nije pronadjena."));

            if (rezervacija.getKorisnik() == null || !rezervacija.getKorisnik().getIdKorisnik().equals(korisnikId)) {
                return "Ne mozete otkazati tudju rezervaciju.";
            }
            if (rezervacija.getStatus() != StatusRezervacije.NA_CEKANJU) {
                return "Moze se otkazati samo rezervacija koja je na cekanju.";
            }

            rezervacijaRepository.delete(rezervacija);
            return "Rezervacija je uspesno otkazana!";
        } catch (Exception ex) {
            return "Greska prilikom otkazivanja rezervacije!";
        }
    }

    public Page<RezervacijaDTO> getPending(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idRezervacija").descending());
        return rezervacijaRepository.findByStatus(StatusRezervacije.NA_CEKANJU, pageable)
                .map(rezervacijaMapper::toRezervacijaDTO);
    }

    public Page<RezervacijaDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idRezervacija").descending());
        return rezervacijaRepository.findAll(pageable).map(rezervacijaMapper::toRezervacijaDTO);
    }

    /**
     * Generise PDF izvestaj rezervacija. Ako je status null, exportuju se
     * sve rezervacije, inace samo one sa prosledjenim statusom.
     */
    public byte[] exportPdf(StatusRezervacije status) {
        List<Rezervacija> rezervacije = status != null
                ? rezervacijaRepository.findByStatus(status)
                : rezervacijaRepository.findAll();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, out);
            document.open();

            Font naslovFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            String naslov = "Pregled rezervacija" + (status != null ? " - " + status : "");
            document.add(new Paragraph(naslov, naslovFont));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Stream.of("ID", "Gost", "Sto", "Dogadjaj", "Datum rezervacije", "Status")
                    .forEach(h -> table.addCell(new PdfPCell(new Phrase(h, headerFont))));

            for (Rezervacija r : rezervacije) {
                table.addCell(String.valueOf(r.getIdRezervacija()));
                table.addCell(r.getKorisnik() != null
                        ? r.getKorisnik().getIme() + " " + r.getKorisnik().getPrezime() : "-");
                table.addCell(r.getSto() != null ? r.getSto().getOznaka() : "-");
                table.addCell(r.getDogadjaj() != null ? r.getDogadjaj().getNaziv() : "-");
                table.addCell(r.getDatumRezervacije() != null ? r.getDatumRezervacije().toString() : "-");
                table.addCell(r.getStatus() != null ? r.getStatus().toString() : "-");
            }

            document.add(table);
            document.close();
            return out.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException("Greska prilikom generisanja PDF-a.", ex);
        }
    }

    public List<RezervacijaDTO> getByKorisnik(Long korisnikId) {
        Korisnik korisnik = korisnikRepository.findById(korisnikId).orElse(null);
        if (korisnik == null) return List.of();
        return rezervacijaRepository.findByKorisnik(korisnik).stream()
                .map(rezervacijaMapper::toRezervacijaDTO).collect(Collectors.toList());
    }
}

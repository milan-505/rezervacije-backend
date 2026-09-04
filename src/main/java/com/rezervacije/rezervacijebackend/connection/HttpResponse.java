/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.connection;

import java.util.HashMap;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Milan
 */
public class HttpResponse {
    public static Response getResponseWithData(String message, Map<?, ?> data, HttpStatus httpStatus) {
        return new Response(message, data, httpStatus);
    }

    public static Response getResponse(String message, HttpStatus httpStatus) {
        return new Response(message, httpStatus);
    }

    public static Map<String, Object> pageData(Page<?> page) {
        Map<String, Object> data = new HashMap<>();
        data.put("values", page.getContent());
        data.put("trenutnaStranica", page.getNumber());
        data.put("velicinaStranice", page.getSize());
        data.put("ukupnoElemenata", page.getTotalElements());
        data.put("ukupnoStranica", page.getTotalPages());
        return data;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.connection;

import java.util.Map;
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
}

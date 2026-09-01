/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.connection;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Milan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String message;
    private Map<?, ?> data;
    private HttpStatus status;
    
    public Response(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
    
    public Response(String message, Map<?, ?> data, HttpStatus status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<?, ?> getData() {
        return data;
    }

    public void setData(Map<?, ?> data) {
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    
    
}

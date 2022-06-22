package com.jroget.ncodingbackend.controllers;

import com.jroget.ncodingbackend.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    protected  ResponseEntity sendResponseOk(String message, Object data) {
        return new ResponseEntity(
                new Response("success",message, data),
                HttpStatus.OK
        );
    }

    protected ResponseEntity sendResponseNotFound(String message) {
        return new ResponseEntity(
                new Response("error",message, null),
                HttpStatus.NOT_FOUND
        );
    }

    protected ResponseEntity sendResponseServerError(String message, Object data) {
        return new ResponseEntity(
                new Response("error",message, data),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

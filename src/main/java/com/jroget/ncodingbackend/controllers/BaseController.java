package com.jroget.ncodingbackend.controllers;

import com.jroget.ncodingbackend.models.Response;
import com.jroget.ncodingbackend.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public class BaseController {
    protected  ResponseEntity<Response> sendResponseOk(String message, Object data) {
        return new ResponseEntity(
                new Response("success",message, data),
                HttpStatus.OK
        );
    }

    protected ResponseEntity<Response> sendResponseNotFound(String message) {
        return new ResponseEntity(
                new Response("error",message, null),
                HttpStatus.NOT_FOUND
        );
    }

    protected ResponseEntity<Response> sendResponseNotAllowed(String message) {
        return new ResponseEntity(
                new Response("error",message, null),
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }

    protected ResponseEntity<Response> sendResponseServerError(String message, Object data) {
        return new ResponseEntity(
                new Response("error",message, data),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

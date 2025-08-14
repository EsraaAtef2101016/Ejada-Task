package com.Back.TaskBack.Apis.Controllers;


import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//give public exception
@RestControllerAdvice
public record ExceptionHandlerController() {



    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException ex){
        List errors =ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "Status",400,
                "erros","Bad Request",
                "message", errors
        ));
    }

}

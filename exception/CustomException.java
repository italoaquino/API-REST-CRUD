package com.PrimeiroProjeto.Projeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundExecption.class)
    public final ResponseEntity<Object> handlerException(Exception ex, WebRequest request) throws Exception{
        logger.error("An error happened to call Api: {}", ex );
        return new ResponseEntity<>(new ExceptionalResponse(LocalDateTime.now(), ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);

    }


}

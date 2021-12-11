package com.PrimeiroProjeto.Projeto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.ejb.Local;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionalResponse {

    private LocalDateTime timestamp;
    private String message;
    private HttpStatus status;


}

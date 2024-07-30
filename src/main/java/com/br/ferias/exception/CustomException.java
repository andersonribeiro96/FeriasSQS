package com.br.ferias.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CustomException extends RuntimeException {

    public ProblemDetail toProblemDetail(){
        var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Erro Interno do Servidor");
        problemDetail.setProperty("timestamp", System.currentTimeMillis());
        return problemDetail;
    }

}

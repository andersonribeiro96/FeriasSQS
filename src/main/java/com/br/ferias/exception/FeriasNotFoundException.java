package com.br.ferias.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class FeriasNotFoundException extends CustomException {

    private String detail;

    public FeriasNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Funcionário não encontrado");
        problemDetail.setDetail(detail);
        return problemDetail;
    }

}

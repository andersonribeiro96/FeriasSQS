package com.br.ferias.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class FuncionarioNotFoundException extends CustomException {

    private String detail;

    public FuncionarioNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Solicitação de férias não encontradas");
        problemDetail.setDetail(detail);
        return problemDetail;
    }
}

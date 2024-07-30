package com.br.ferias.service;

import com.br.ferias.domain.Funcionario;
import com.br.ferias.exception.FuncionarioNotFoundException;
import com.br.ferias.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario buscarFuncionario(Long funcionarioId) {
        return funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new FuncionarioNotFoundException("Funcionário não encontrado com o ID: " + funcionarioId));
    }

}

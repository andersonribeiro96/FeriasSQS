package com.br.ferias.service;

import com.br.ferias.controller.dto.AprovaFeriasDTO;
import com.br.ferias.controller.dto.SolicitarFeriasDTO;
import com.br.ferias.domain.Ferias;
import com.br.ferias.domain.Funcionario;
import com.br.ferias.exception.FeriasNotFoundException;
import com.br.ferias.repository.FeriasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class FeriasService {

    private final FeriasRepository feriasRepository;

    private final FuncionarioService funcionarioService;

    private final EmailService emailService;

    public FeriasService(FeriasRepository feriasRepository, FuncionarioService funcionarioService, EmailService emailService) {
        this.feriasRepository = feriasRepository;
        this.funcionarioService = funcionarioService;
        this.emailService = emailService;
    }

    @Transactional
    public Ferias solicitarFerias(SolicitarFeriasDTO solicitarFeriasDTO) {
        Funcionario funcionario = funcionarioService.buscarFuncionario(solicitarFeriasDTO.funcionarioId());
        Ferias ferias = Ferias.criarFerias(funcionario, solicitarFeriasDTO.dataInicio(), solicitarFeriasDTO.dataFim());
        Ferias feriasCriada = feriasRepository.save(ferias);

        if (funcionario.getGestor() != null) {
            emailService.notificarGestorSolicitacaoDeFerias(feriasCriada);
        }

        return feriasCriada;

    }

    @Transactional
    public Ferias processarSolicitacaoDeFerias(AprovaFeriasDTO aprovaFeriasDTO) {
        Ferias ferias = buscarFerias(aprovaFeriasDTO.feriasId());
        ferias.setAprovado(aprovaFeriasDTO.aprovado());
        feriasRepository.save(ferias);
        emailService.notificarFuncionarioAprovacaoDeFerias(ferias);
        return ferias;
    }

    private Ferias buscarFerias(Long feriasId) {
        return feriasRepository.findById(feriasId)
                .orElseThrow(() -> new FeriasNotFoundException("Férias não encontradas com o ID: " + feriasId));
    }
}

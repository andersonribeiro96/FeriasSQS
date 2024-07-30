package com.br.ferias.controller;

import com.br.ferias.controller.dto.AprovaFeriasDTO;
import com.br.ferias.controller.dto.FeriasDTO;
import com.br.ferias.controller.dto.SolicitarFeriasDTO;
import com.br.ferias.domain.Ferias;
import com.br.ferias.service.FeriasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ferias")
public class FeriasController {

    private final FeriasService feriasService;

    public FeriasController(FeriasService feriasService) {
        this.feriasService = feriasService;
    }

    @PostMapping
    public ResponseEntity<FeriasDTO> solicitarFerias(@RequestBody SolicitarFeriasDTO solicitarFeriasDTO){
        Ferias ferias = feriasService.solicitarFerias(solicitarFeriasDTO);
        FeriasDTO feriasDTO = FeriasDTO.fromEntity(ferias);
        return ResponseEntity.ok(feriasDTO);
    }

    @PostMapping("/aprovar")
    public ResponseEntity<FeriasDTO> aprovarFerias(@RequestBody AprovaFeriasDTO aprovaFeriasDTO){
        Ferias ferias = feriasService.processarSolicitacaoDeFerias(aprovaFeriasDTO);
        FeriasDTO feriasDTO = FeriasDTO.fromEntity(ferias);
        return ResponseEntity.ok(feriasDTO);
    }


}

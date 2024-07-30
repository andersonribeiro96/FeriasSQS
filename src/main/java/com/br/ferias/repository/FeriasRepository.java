package com.br.ferias.repository;

import com.br.ferias.domain.Ferias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeriasRepository extends JpaRepository<Ferias, Long> {
    // Adicione métodos personalizados se necessário, por exemplo:
    // List<Ferias> findByFuncionarioId(Long funcionarioId);
}

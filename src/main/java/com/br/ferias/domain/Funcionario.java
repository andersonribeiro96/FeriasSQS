package com.br.ferias.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    private boolean isGestor;

    @ManyToOne
    @JoinColumn(name = "gestor_id")
    @JsonBackReference
    private Funcionario gestor;

    @OneToMany(mappedBy = "gestor")
    @JsonManagedReference
    private List<Funcionario> subordinados;

    public boolean isGestor() {
        return isGestor;
    }

    public void setIsGestor(boolean gestor) {
        isGestor = gestor;
    }

}

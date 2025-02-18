package com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private Integer idInstrutor;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Enumerated(EnumType.STRING)
    private Nivel nivel;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private Boolean ativo;

    public Curso(CursoRequest request) {
        this.titulo = request.titulo();
        this.idInstrutor = request.idInstrutor();
        this.categoria = request.categoria();
        this.nivel = request.nivel();
    }

    public void atualizarCurso(UpdateCursoRequest request) {

        if (request.titulo() != null && !request.titulo().isEmpty()) {
            this.titulo = request.titulo();
        }

        if (request.idInstrutor() != null) {
            this.idInstrutor = request.idInstrutor();
        }

        if (request.categoria() != null) {
            this.categoria = request.categoria();
        }

        if (request.nivel() != null) {
            this.nivel = request.nivel();
        }
    }
}

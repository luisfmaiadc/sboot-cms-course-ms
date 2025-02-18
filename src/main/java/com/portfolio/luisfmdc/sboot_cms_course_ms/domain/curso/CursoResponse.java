package com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CursoResponse(@NotBlank String titulo, String idInstrutor, @NotNull Categoria categoria,
                            @NotNull Nivel nivel, @NotNull LocalDateTime dataCriacao) {

    public CursoResponse(Curso curso) {
        this(curso.getTitulo(), curso.getIdInstrutor() != null ? String.valueOf(curso.getIdInstrutor()) : "Curso sem professor atribuído no momento.",
                curso.getCategoria(), curso.getNivel(), curso.getDataCriacao());
    }
}

package com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CursoRequest(
        @Size(min = 3, max = 75, message = "O nome do curso deve ter entre 3 e 75 caracteres")
        @NotBlank String titulo,
        Integer idInstrutor,
        @NotNull Categoria categoria,
        @NotNull Nivel nivel) {
}

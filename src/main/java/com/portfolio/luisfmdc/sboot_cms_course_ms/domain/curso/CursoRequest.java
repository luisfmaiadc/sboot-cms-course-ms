package com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoRequest(@NotBlank String titulo, Integer idInstrutor, @NotNull Categoria categoria,
                           @NotNull Nivel nivel) {
}

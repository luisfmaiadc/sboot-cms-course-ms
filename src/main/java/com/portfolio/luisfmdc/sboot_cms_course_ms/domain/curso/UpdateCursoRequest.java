package com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso;

public record UpdateCursoRequest(String titulo, Integer idInstrutor, Categoria categoria, Nivel nivel) {
}

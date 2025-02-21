package com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.portfolio.luisfmdc.sboot_cms_course_ms.infra.exception.InvalidRequestArgumentException;

import java.util.stream.Stream;

public enum Categoria {

    DESENVOLVIMENTO_SOFTWARE,
    BANCO_DADOS,
    SEGURANCA_DA_INFORMACAO,
    MARKETING_DIGITAL,
    GESTAO_PROJETOS,
    ADMINISTRACAO,
    FINANCAS,
    DESIGN,
    IDIOMAS,
    ENGENHARIA,
    CIENCIAS_EXATAS,
    CIENCIAS_HUMANAS,
    SAUDE,
    EDUCACAO,
    ARTES,
    MUSICA;

    @JsonCreator
    public static Categoria fromString(String value) {
        return Stream.of(Categoria.values())
                .filter(c -> c.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new InvalidRequestArgumentException(
                        "Categoria inválida. Valores permitidos: " + Stream.of(Categoria.values()).map(Enum::name).toList()
                ));
    }
}

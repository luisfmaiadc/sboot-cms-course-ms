package com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.portfolio.luisfmdc.sboot_cms_course_ms.infra.exception.InvalidRequestArgumentException;

import java.util.stream.Stream;

public enum Nivel {

    BASICO,
    INTERMEDIARIO,
    AVANCADO;

    @JsonCreator
    public static Nivel fromString(String value) {
        return Stream.of(Nivel.values())
                .filter(n -> n.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new InvalidRequestArgumentException(
                        "Nível inválido. Valores permitidos: " + Stream.of(Nivel.values()).map(Enum::name).toList()
                ));
    }
}

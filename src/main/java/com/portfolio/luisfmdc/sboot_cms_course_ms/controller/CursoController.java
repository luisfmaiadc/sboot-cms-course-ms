package com.portfolio.luisfmdc.sboot_cms_course_ms.controller;

import com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso.CursoRequest;
import com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso.CursoResponse;
import com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso.UpdateCursoRequest;
import com.portfolio.luisfmdc.sboot_cms_course_ms.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<CursoResponse> cadastrarCurso(@RequestBody @Valid CursoRequest request, UriComponentsBuilder uriComponentsBuilder) {
        return service.cadastrarCurso(request, uriComponentsBuilder);
    }

    @PutMapping("/atualizar/{idCurso}")
    public ResponseEntity<CursoResponse> atualizarCurso(@PathVariable Integer idCurso, @RequestBody UpdateCursoRequest request) {
        return service.atualizarCurso(idCurso, request);
    }

    @DeleteMapping("/deletar/{idCurso}")
    public ResponseEntity inativarCurso(@PathVariable Integer idCurso) {
        return service.inativarCurso(idCurso);
    }

    @GetMapping("/validar/{idCurso}")
    public ResponseEntity validarCurso(@PathVariable Integer idCurso) {
        return service.validarCurso(idCurso);
    }
}

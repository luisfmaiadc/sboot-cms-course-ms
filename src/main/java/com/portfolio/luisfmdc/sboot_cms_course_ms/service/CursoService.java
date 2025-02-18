package com.portfolio.luisfmdc.sboot_cms_course_ms.service;

import com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso.Curso;
import com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso.CursoRequest;
import com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso.CursoResponse;
import com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso.UpdateCursoRequest;
import com.portfolio.luisfmdc.sboot_cms_course_ms.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public ResponseEntity<CursoResponse> cadastrarCurso(CursoRequest request, UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = new Curso(request);
        if (request.idInstrutor() != null) {
            curso.setAtivo(Boolean.TRUE);
        } else {
            curso.setAtivo(Boolean.FALSE);
        }
        repository.save(curso);
        URI uri = uriComponentsBuilder.path("/cadastrar/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new CursoResponse(curso));
    }

    public ResponseEntity<CursoResponse> atualizarCurso(Integer idCurso, UpdateCursoRequest request) {
        Curso curso = repository.getReferenceById(idCurso);
        curso.atualizarCurso(request);
        if (curso.getIdInstrutor() != null && curso.getAtivo().booleanValue() != Boolean.TRUE) {
            curso.setAtivo(Boolean.TRUE);
        }
        repository.save(curso);
        return ResponseEntity.ok(new CursoResponse(curso));
    }

    public ResponseEntity inativarCurso(Integer idCurso) {
        Curso curso = repository.getReferenceById(idCurso);
        curso.setAtivo(Boolean.FALSE);
        repository.save(curso);
        return ResponseEntity.noContent().build();
    }
}
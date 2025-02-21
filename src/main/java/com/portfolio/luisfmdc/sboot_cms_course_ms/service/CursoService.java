package com.portfolio.luisfmdc.sboot_cms_course_ms.service;

import com.portfolio.luisfmdc.sboot_cms_course_ms.client.InstrutorClient;
import com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso.Curso;
import com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso.CursoRequest;
import com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso.CursoResponse;
import com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso.UpdateCursoRequest;
import com.portfolio.luisfmdc.sboot_cms_course_ms.infra.exception.ErrorClientException;
import com.portfolio.luisfmdc.sboot_cms_course_ms.infra.exception.InvalidTeacherException;
import com.portfolio.luisfmdc.sboot_cms_course_ms.repository.CursoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private InstrutorClient instrutor;

    public ResponseEntity<CursoResponse> cadastrarCurso(CursoRequest request, UriComponentsBuilder uriComponentsBuilder) {
        boolean professorValido = request.idInstrutor() == null || validarProfessor(request.idInstrutor());

        if(!professorValido) {
            throw new InvalidTeacherException("Professor inválido.");
        }

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
        boolean professorValido = request.idInstrutor() == null || validarProfessor(request.idInstrutor());

        if(!professorValido) {
            throw new InvalidTeacherException("Professor inválido.");
        }

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

    private boolean validarProfessor(Integer idProfessor) {
        try {
            instrutor.validarProfessor(idProfessor);
            return true;
        } catch (FeignException.NotFound e) {
            return false;
        } catch (FeignException e) {
            throw new ErrorClientException("Erro na tentativa de validar professor.");
        }
    }

    public ResponseEntity validarCurso(Integer idCurso) {
        Optional<Curso> optional = repository.findById(idCurso);

        if (optional.isPresent()) {
            Curso curso = repository.getReferenceById(idCurso);
            if (curso.getAtivo().booleanValue() == Boolean.TRUE) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
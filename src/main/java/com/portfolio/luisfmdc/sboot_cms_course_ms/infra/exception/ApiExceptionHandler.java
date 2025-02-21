package com.portfolio.luisfmdc.sboot_cms_course_ms.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErro>> handleInvalidArgument(MethodArgumentNotValidException ex) {
        var error = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(DadosErro::new).toList());
    }

    @ExceptionHandler(InvalidRequestArgumentException.class)
    public ResponseEntity<List<DadosErro>> handleInvalidRequestArgumentException(InvalidRequestArgumentException ex) {
        DadosErro erro = new DadosErro(extractFieldFromMessage(ex.getMessage()), ex.getMessage());
        return ResponseEntity.badRequest().body(List.of(erro));
    }

    @ExceptionHandler(InvalidTeacherException.class)
    public ResponseEntity handleInvalidTeacherException(InvalidTeacherException ex) {
        return ResponseEntity.status(404).body(new DadosErro("idInstrutor", ex.getMessage()));
    }

    @ExceptionHandler(ErrorClientException.class)
    public ResponseEntity handleErrorClientException(ErrorClientException ex) {
        return ResponseEntity.status(503).body(new DadosErro("idInstrutor", ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(404).body(new DadosErro("idCurso", "Curso informado inválido."));
    }

    private String extractFieldFromMessage(String message) {
        if (message.contains("Nível")) {
            return "nivel";
        } else if (message.contains("Categoria")) {
            return "categoria";
        } else if (message.contains("nome")) {
            return "titulo";
        } else {
            return "campo desconhecido";
        }
    }

    private record DadosErro(String campo, String mensagem) {
        DadosErro(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}

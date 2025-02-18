package com.portfolio.luisfmdc.sboot_cms_course_ms.repository;

import com.portfolio.luisfmdc.sboot_cms_course_ms.domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}

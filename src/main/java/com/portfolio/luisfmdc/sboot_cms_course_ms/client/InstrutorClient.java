package com.portfolio.luisfmdc.sboot_cms_course_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "sboot-cms-teacher-ms")
public interface InstrutorClient {

    @RequestMapping(method = RequestMethod.GET, value = "/professor/validar/{idProfessor}")
    ResponseEntity<Void> validarProfessor(@PathVariable Integer idProfessor);
}

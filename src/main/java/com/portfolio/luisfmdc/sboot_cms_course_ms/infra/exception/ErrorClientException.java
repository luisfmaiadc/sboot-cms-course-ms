package com.portfolio.luisfmdc.sboot_cms_course_ms.infra.exception;

public class ErrorClientException extends RuntimeException {
    public ErrorClientException(String message) {
        super(message);
    }
}

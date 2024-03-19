package org.flab.hyunsb.bootstrap.rest.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.flab.hyunsb.application.exception.ConstraintException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse validationException(
        MethodArgumentNotValidException exception, HttpServletRequest request) {
        logError(exception, request.getRequestURL().toString());

        String errorMessage = exception.getBindingResult().getFieldError().getDefaultMessage();
        return ErrorResponse.create(exception, HttpStatus.BAD_REQUEST, errorMessage);
    }

    @ExceptionHandler(ConstraintException.class)
    public ErrorResponse constraintException(
        ConstraintException exception, HttpServletRequest request) {
        logError(exception, request.getRequestURL().toString());

        return ErrorResponse.create(exception, HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    private void logError(Exception exception, String requestUrl) {
        log.error(
            "[{}]: requestUrl={}, stackTrace={}",
            exception.getClass(), requestUrl, exception.getStackTrace()
        );
    }
}

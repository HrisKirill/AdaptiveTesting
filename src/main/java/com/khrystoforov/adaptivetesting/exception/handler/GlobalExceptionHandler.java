package com.khrystoforov.adaptivetesting.exception.handler;

import com.khrystoforov.adaptivetesting.exception.EntityExistsException;
import com.khrystoforov.adaptivetesting.exception.EntityNotFoundException;
import com.khrystoforov.adaptivetesting.exception.ErrorResponse;
import com.khrystoforov.adaptivetesting.exception.MaxQuestionsAnsweredException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final Environment environment;

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(EntityNotFoundException ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(EntityExistsException ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(MaxQuestionsAnsweredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(MaxQuestionsAnsweredException ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ErrorResponse handleException(Throwable ex) {
        log.error(ex.getMessage());
        boolean isProduction = environment.matchesProfiles("production");
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), isProduction ? "" : ex.getMessage());
    }
}

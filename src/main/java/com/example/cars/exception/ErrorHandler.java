package com.example.cars.exception;

import com.example.cars.exception.exceptions.NotFoundException;
import com.example.cars.exception.exceptions.ServerErrorException;
import com.example.cars.exception.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND) //404
    public ErrorResponse handleNotFoundException(final NotFoundException e) {
        log.info("404: {}", e.getMessage());
        return new ErrorResponse(String.format("Ошибка с полем \"%s\".", e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    public ErrorResponse handleBadRequestException(final ValidationException e) {
        log.info("400: {}", e.getMessage());
        return new ErrorResponse(String.format("Ошибка с полем \"%s\".", e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
    public ErrorResponse handleServerErrorException(final ServerErrorException e) {
        log.info("500: {}", e.getMessage());
        return new ErrorResponse(String.format(e.getMessage()));
    }
}

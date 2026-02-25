package com.encurtador.configSecurity;

import com.encurtador.dtos.ExceptionResponse;
import com.encurtador.execeptions.UrlExpired;
import com.encurtador.execeptions.UrlNotFounded;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UrlNotFounded.class)
    public ResponseEntity<ExceptionResponse> UrlNotFounded(Exception ex, WebRequest webRequest){
            HttpStatus status = HttpStatus.NOT_FOUND;
            ExceptionResponse exceptionResponse = new ExceptionResponse(
                    LocalDateTime.now(),
                    status.value(),
                    status.getReasonPhrase(),
                    ex.getMessage(),
                    webRequest.getDescription(false)
            );

            return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
        }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> Urlformat(Exception ex, WebRequest webRequest){
            HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                webRequest.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UrlExpired.class)
    public ResponseEntity<ExceptionResponse> UrlExpired(Exception ex, WebRequest webRequest){
        HttpStatus status = HttpStatus.GONE;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                webRequest.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse,HttpStatus.GONE);
    }
    }


package com.B2Boost.RestAPIProblem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler({InternalServerErrorException.class, BadRequestException.class})
    public ResponseEntity<Object> handleGlobalException() {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .code(500)
                .message("An internal server error occurred")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException() {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .code(500)
                .message("An internal server error occurred because the id doesn't match")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> errorResponse () {
        ErrorResponse error =  ErrorResponse
                .builder()
                .code(400)
                .message("Bad request")
                .build();

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<Object> handleDuplicatedData () {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(400)
                .message("Duplicated reference")
                .build();

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException( ResourceNotFoundException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(404)
                .message("Partner with id " + e.getId() + " not found.")
                .build();

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND );
    }
}

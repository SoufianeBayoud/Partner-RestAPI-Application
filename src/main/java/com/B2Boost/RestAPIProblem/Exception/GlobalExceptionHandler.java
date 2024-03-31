package com.B2Boost.RestAPIProblem.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleGlobalException() {
        ErrorResponse errorResponse = new ErrorResponse(500, "An internal sdsdsds occurred");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<Object> handleBadRequest() {
//        ErrorResponse errorResponse = new ErrorResponse(400, "Bad request rgegtsegrg");
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }






//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
//        ErrorResponse errorResponse = new ErrorResponse(404, "Partner with id " + ex.getId() + " not found.");
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }
//}
}
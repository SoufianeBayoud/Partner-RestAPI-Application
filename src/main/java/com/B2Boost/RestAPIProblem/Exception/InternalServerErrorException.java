package com.B2Boost.RestAPIProblem.Exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }

}
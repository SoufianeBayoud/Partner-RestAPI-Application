package com.B2Boost.RestAPIProblem.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResourceNotFoundException extends RuntimeException{
    private Long id;
    public ResourceNotFoundException(String message) {
        super(message);
    }



}

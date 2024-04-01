package com.B2Boost.RestAPIProblem.Exception;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DuplicateDataException extends RuntimeException {
    private String message;
    private int code;
}

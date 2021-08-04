package com.harunisik.cakes.exception;

import lombok.Getter;

@Getter
public class CakeManagerException extends RuntimeException {

    private ExceptionType exceptionType;

    public CakeManagerException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }

}

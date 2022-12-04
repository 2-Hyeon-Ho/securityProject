package com.nhnacademy.springjpa.exception;

public class ResidentExistException extends RuntimeException {
    public ResidentExistException() {
        super("exist residentId!");
    }
}

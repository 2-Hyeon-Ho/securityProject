package com.nhnacademy.springjpa.exception;

public class ResidentNotFoundException extends RuntimeException{
    public ResidentNotFoundException() {
        super("residentId not found!");
    }
}

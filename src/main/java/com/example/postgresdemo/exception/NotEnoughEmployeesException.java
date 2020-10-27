package com.example.postgresdemo.exception;

public class NotEnoughEmployeesException extends RuntimeException {
    public NotEnoughEmployeesException(String skill) {
        super("Note enough employees of skill: " + skill);
    }
}

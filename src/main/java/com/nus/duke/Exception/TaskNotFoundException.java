package com.nus.duke.exception;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}

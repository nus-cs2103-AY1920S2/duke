package com.nus.duke.Exception;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}

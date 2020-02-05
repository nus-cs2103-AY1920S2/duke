package main.java.exceptions;

import main.java.storage.Storage;

public class StorageOperationException extends Exception{
    public StorageOperationException(String cause) {
        super(cause);
    }
}

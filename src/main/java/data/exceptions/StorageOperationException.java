package data.exceptions;

public class StorageOperationException extends Exception {

    public StorageOperationException() {
    }

    public StorageOperationException(String message) {
        super(message);
    }
}

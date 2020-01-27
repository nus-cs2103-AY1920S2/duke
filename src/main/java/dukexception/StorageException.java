package dukexception;

/**
 * Custom exception to handle exceptions related
 * to data storage.
 */
public class StorageException extends DukeException{

    public StorageException(String msg){
        super(msg);
    }
}

package duke.utils;

import java.io.IOException;

/**
 * Interface for storage object.
 */
public interface Storage {

    void createStorage() throws IOException;

    boolean checkFileExistence();

    String loadStorage() throws IOException;

    void storeData(String data) throws IOException;
}

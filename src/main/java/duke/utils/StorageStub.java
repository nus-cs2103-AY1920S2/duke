package duke.utils;

import java.io.IOException;

/**
 * Stub for file storage.
 */
public class StorageStub implements Storage {

    @Override
    public void createStorage() throws IOException {

    }

    @Override
    public boolean checkFileExistence() {
        return true;
    }

    @Override
    public String loadStorage() throws IOException {
        return null;
    }

    @Override
    public void storeData(String data) throws IOException {

    }

}

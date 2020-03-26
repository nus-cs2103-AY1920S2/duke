package duke.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The <code>Storage</code> class is a utility class to load data from a file, if any, and write
 * data to a file to store for future use.
 *
 * @author Zhu Yijie
 */
public class Storage {
    private String filePath;
    private String fileDirectory;
    
    public Storage(String fileName, String fileDirectory) {
        this.filePath = fileDirectory + fileName;
        this.fileDirectory = fileDirectory;
    }
    
    /**
     * Retrieves data from file.
     *
     * @return Data from file.
     * @throws IOException If failure in reading from file.
     */
    public String load() throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
        return new String(encoded, Charset.defaultCharset());
    }
    
    /**
     * Writes data to file.
     *
     * @param data Data from program.
     * @throws IOException If failure in writing to file.
     */
    public void save(String data) throws IOException {
        File file = new File(filePath);
        File directory = new File(fileDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
        FileWriter writer = new FileWriter(file, false);
        writer.write(data);
        writer.close();
    }
}

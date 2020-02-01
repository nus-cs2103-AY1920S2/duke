import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static final String DESTINATION_PATH = "C:\\tasks.txt";
    private Storage() {
    }

    public static Storage createSrorageFile() {
        return new Storage();
    }

    public void saveToFile(String input) {
        try {
            File file = new File(DESTINATION_PATH);
            FileWriter fw = new FileWriter(file);
            fw.write(input);
            fw.close();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }


    public String getDestinationPath() {
        return DESTINATION_PATH;
    }

}

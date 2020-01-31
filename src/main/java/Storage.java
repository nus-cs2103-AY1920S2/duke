import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path filePath;
    private File file;

    public Storage(String fileName) {
        // Load the file
        String currDir = System.getProperty("user.dir");
        this.filePath =  Paths.get(currDir, "list.txt");
        this.file = new File(filePath.toString());
        try {
            // create the file if it does not exist
            if (!file.exists()) {
                this.file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Path getFilePath() {
        return this.filePath;
    }

    public File getFile() {
        return this.file;
    }

    public ArrayList<String> loadArrayListStringFromFile() {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(this.filePath, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(lines);
    }


}

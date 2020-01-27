import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class DukeStorage {
    private static final String defaultPath = "data\\duke.txt";

    public final Path path;

    public DukeStorage(String filePath) {
        path = Paths.get(filePath);
    }

    public DukeStorage() {
        this(defaultPath);
    }
    public void save(DukeList dl) {
        ArrayList<Task> inputDL = dl.getListOfTasks();
        List<String> outputList = saveEncoder(inputDL);
        try {
            Path test = Files.write(path, outputList);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    private List<String> saveEncoder(ArrayList<Task> alT) {
        List<String> output = new ArrayList<>();
        for(Task a : alT) {
            output.add(a.toString());
        }
        return output;
    }
}

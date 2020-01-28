import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
        for(Task currTask : alT) {
            output.add(currTask.getSaveString());
        }
        return output;
    }

    public DukeList load() throws IOException {
        if (!Files.exists(path)) {
            return new DukeList();
        } else {
            DukeList output = new DukeList();
            try {
                List<String> input = Files.readAllLines(path);
                return loadDecoder(input, output);
            } catch (IOException ioe) {
                throw ioe;
            }
        }
    }

    private DukeList loadDecoder(List<String> savedList, DukeList dl) {
        for(String currSaveEntry : savedList) {
            String[] input = currSaveEntry.split("\\|");
            String command = input[0];
            String isDoneString = input[1];
            String taskDesc = input[2];
            if(command.equals("T")) {
                dl.loadAdd(new Todo(taskDesc, isDoneString));
            } else if(command.equals("E")) {
                LocalDate by = LocalDate.parse(input[3]);
                dl.loadAdd((new Event(taskDesc, by, isDoneString)));
            } else {
                LocalDate by = LocalDate.parse(input[3]);
                dl.loadAdd((new Deadline(taskDesc, by, isDoneString)));
            }
        }
        return dl;
    }
}

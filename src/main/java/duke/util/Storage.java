package duke.util;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    public String home = System.getProperty("user.dir");
    protected Path rootPath;
    protected Path dataPath;

    public Storage() throws IOException, DukeException {
        /*
        this.rootPath = Paths.get(home);
        this.dataPath = Paths.get(rootPath.toString(), "data", "duke.txt");
         */
        String home = System.getProperty("user.dir");
        this.dataPath = Paths.get(home, "duke.txt");
        if (!Files.exists(dataPath)) {
            File dataFile = new File(dataPath.toString());          // create new file
            if (!dataFile.createNewFile()) {
                throw new DukeException("File could not be created :|");
            }
        }
    }

    /**
     * Loads each line from .txt file
     * @return returns a list of tasks
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks =  new ArrayList<>();
        try {
            List<String> dataLines = Files.readAllLines(dataPath);
            for (String s : dataLines) {
                if (dataLines.isEmpty() || s.isBlank()) {
                    return tasks;
                }
                tasks.add(Parser.parseDataFromFile(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * saves a list of tasks in a .txt file
     * @param taskList to be saved in .txt file
     */
    public void save(ArrayList<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        for (Task t : taskList) {
            if (t instanceof Todo) {
                sb.append(((Todo) t).toSaveForm());
            } else if (t instanceof Deadline) {
                sb.append(((Deadline) t).toSaveForm());
            } else if (t instanceof Event) {
                sb.append(((Event) t).toSaveForm());
            }
        }
        try {
            FileWriter fw = new FileWriter(dataPath.toFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sb.toString());
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

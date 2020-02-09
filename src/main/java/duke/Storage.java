import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();

        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            tasks.add(databaseStringToTask(s.nextLine()));
        }

        return tasks;
    }

    private static Task databaseStringToTask(String s) {
        String[] split = s.split("\\|");
        Task newTask = new Todo("error in decoding database string");

        switch (split[0]) {
            case "T":
                newTask = new Todo(split[2]);
                break;
            case "D":
                newTask = new Deadline(split[2], split[3]);
                break;
            case "E":
                newTask = new Event(split[2], split[3]);
                break;
            default:
                System.err.println("default case hit in decoding database string to task");
        }

        // set completion status
        if (split[1].equals("1")) {
            newTask.markAsDone();
        }
        return newTask;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task t : tasks.getTasks()) {
            fw.write(t.toDatabaseString());
        }
        fw.close();
    }
}

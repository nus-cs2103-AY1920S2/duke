import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Loads and saves Duke data file.
 */
public class Storage {
    private final String path;

    /**
     * Construct a Storage with given file path.
     *
     * @param path
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads original data from the data file.
     *
     * @return A list of old tasks record.
     */
    public ArrayList<Task> load() {
        FileReader fr = null;
        try {
            fr = new FileReader(path);
        } catch (FileNotFoundException e) {
            System.out.println("    Cannot open data file in path: " + path);
        }
        BufferedReader br = new BufferedReader(fr);

        ArrayList<Task> tasks = new ArrayList<>();

        String line = null;
        while (true) {
            try {
                if ((line = br.readLine()) == null) {
                    break;
                }
            } catch (IOException e) {
                System.out.println("    Cannot read data!");
            }
            Task currTask = decodeTaskFromString(line);
            tasks.add(currTask);
        }

        return tasks;
    }

    private static Task decodeTaskFromString(String line) {
        String[] words = line.split("\\|");
        Task currTask;
        switch (words[0]) {
            case "T":
                currTask = new Todo(words[2]);
                break;
            case "D":
                currTask = new Deadline(words[2], LocalDate.parse(words[3]));
                break;
            case "E":
                currTask = new Event(words[2], LocalDate.parse(words[3]));
                break;
            default:
                return null;
        }
        if (Integer.valueOf(words[1]) == 1) {
            currTask.markAsDone();
        }
        return currTask;
    }

    /**
     * Saves new Task list data into the data file.
     *
     * @param tasks New task list.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            // Record task data.
            for (int i = 0; i < tasks.getTaskNumber(); i++) {
                bw.write(tasks.getTask(i).getData());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("    Cannot save data!");
        }
    }
}

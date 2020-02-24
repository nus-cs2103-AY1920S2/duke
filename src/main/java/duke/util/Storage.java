package duke.util;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads and saves Duke data file.
 */
public class Storage {
    private String path;

    /**
     * Construct a Storage with given file path.
     *
     * @param path The past of data file.
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
        File file = new File(this.path);

        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            //System.out.println("Cannot open data file!");
            return new ArrayList<>();
        }

        ArrayList<Task> tasks = new ArrayList<>();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            Task currTask = decodeTaskFromString(line);
            tasks.add(currTask);
        }

        return tasks;
    }

    private static Task decodeTaskFromString(String line) {
        String[] words = line.split("\\|");
        assert words.length >= 3: "Incomplete task record when saving data";
        Task currTask;
        switch (words[0]) {
            case "T":
                assert words.length >= 3: "Invalid Todo task record when saving data";
                currTask = words.length >= 4 ? new Todo(words[2], words[3]) : new Todo(words[2]);
                break;
            case "D":
                assert words.length >= 4: "Invalid Deadline task record when saving data";
                currTask = words.length >= 5 ? new Deadline(words[2], LocalDate.parse(words[4]), words[3]) :
                        new Deadline(words[2], LocalDate.parse(words[4]));
                break;
            case "E":
                assert words.length >= 4: "Invalid Event task record when saving data";
                currTask = words.length >= 5 ? new Event(words[2], LocalDate.parse(words[4]), words[3]) :
                        new Event(words[2], LocalDate.parse(words[4]));
                break;
            default:
                return null;
        }
        if (Integer.parseInt(words[1]) == 1) {
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

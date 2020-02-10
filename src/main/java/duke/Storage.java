package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates, loads and stores the program data of duke.
 */
public class Storage {
    File file;

    /**
     * Initialize the file with the filepath specified by user.
     *
     * @param filepath The location where the program data will be saved or loaded from.
     */
    public Storage(String filepath) {
        file = new File(new File(filepath).getAbsolutePath());
    }

    /**
     * Loads program data from the filepath specified by the user.
     * A new file will be created if no existing data is found.
     *
     * @return An ArrayList containing existing data, if any.
     * @throws DukeException Errors related to read/write operations will be thrown.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!file.createNewFile()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] task = line.split("\\|");
                    String taskType = task[0].trim();
                    boolean isDone = task[1].trim().equals("1");
                    if (taskType.equals("T")) {
                        Task todo = new Todo(task[2].trim());
                        if (isDone) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                    } else if (taskType.equals("D")) {
                        Task deadline = new Deadline(task[2].trim(),
                                LocalDate.parse(task[3].trim(), DateTimeFormatter.ofPattern("MMM d yyyy")));
                        if (isDone) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                    } else {
                        Task event = new Event(task[2].trim(),
                                LocalDate.parse(task[3].trim(), DateTimeFormatter.ofPattern("MMM d yyyy")));
                        if (isDone) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                    }
                }
                br.close();
            }
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Error creating or loading file.");
        }
        return tasks;
    }

    /**
     * Writes data to the end of the file.
     *
     * @param data Data to be written.
     * @throws DukeException Errors related to write operations will be thrown.
     */
    public void writeToFile(String data) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Error writing to file.");
        }
    }

    /**
     * Overloaded method which modifies the file content according to the user's request.
     *
     * @param command Specify the operation to be done on the file.
     * @param taskNumber Which task to perform the operation on.
     * @throws DukeException Errors related to read/write operations will be thrown.
     */
    public void writeToFile(String command, int taskNumber) throws DukeException {
        List<String> lines = new ArrayList<>();
        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if (i == taskNumber) {
                    if (command.equals("done")) {
                        line = line.substring(0, 4) + "1" + line.substring(5);
                    } else {
                        i++;
                        continue;
                    }
                }
                lines.add(line + "\n");
                i++;
            }
            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (String s : lines) {
                bw.write(s);
            }
            bw.close();
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Error writing to file.");
        }
    }
}

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {
    protected String filepath;
    protected DateTimeFormatter df = DateTimeFormatter.ofPattern("d MMM yyyy");

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads file specified in filepath and converts file content into a TaskList.
     * @return TaskList.
     * @throws FileNotFoundException
     */
    public TaskList getTaskFromMemory() throws FileNotFoundException {
        TaskList taskList = new TaskList();
        File f = new File(filepath);
        Scanner fs = new Scanner(f);
        while (fs.hasNextLine()) {
            String currentLine = fs.nextLine();
            String[] input = currentLine.split(" ", 2);
            char command = input[0].charAt(1);
            boolean isDone = (input[0].charAt(4) == 'O');

            if (command == 'T') {
                Task t = new Todo(input[1]);
                if (isDone) {
                    t.markAsDone();
                }
                taskList.add(t);
            } else if (command == 'D') {
                int byStart = input[1].indexOf(" (by:");
                String description = input[1].substring(0, byStart);
                String by = input[1].substring(byStart + 6, input[1].length() - 1);
                LocalDate byDate = LocalDate.parse(by, df);
                Task t = new Deadline(description, byDate);
                if (isDone) {
                    t.markAsDone();
                }
                taskList.add(t);
            } else if (command == 'E') {
                int atStart = input[1].indexOf(" (at:");
                String description = input[1].substring(0, atStart);
                String at = input[1].substring(atStart + 6, input[1].length() - 1);
                LocalDate atDate = LocalDate.parse(at, df);
                Task t = new Event(description, atDate);
                if (isDone) {
                    t.markAsDone();
                }
                taskList.add(t);
            }
        }
        return taskList;
    }

    /**
     * Overwrite target file specified in filepath with content of TaskList provided.
     * @param taskList User's TaskList.
     */
    public void writeTaskToMemory(TaskList taskList) {
        try {
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < taskList.size(); i++) {
                Task current = taskList.get(i);
                sb.append(current.toString() + "\n");
            }
            File f = new File(filepath);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(filepath);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            Ui.printWithBorder(e.getMessage());
        }
    }
}

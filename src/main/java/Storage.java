/*package duke;

import duke.Ui;
import duke.Task;
import duke.DukeException;
import duke.Todo;
import duke.Deadline;
import duke.Event;*/
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
    private String filePath;
    private Ui ui;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
    }

    private static ArrayList<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(" \\| ");
            if (data[0].equals("T")) {
                ToDo todo = new ToDo(data[2]);
                if (data[1].equals("1")) {
                    todo.setDone();
                }
                tasks.add(todo);
            } else if (data[0].equals("D")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                Deadline deadline = new Deadline(data[2], LocalDateTime.parse(data[3], formatter));
                if (data[1].equals("1")) {
                    deadline.setDone();
                }
                tasks.add(deadline);
            } else if (data[0].equals("E")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                Event event = new Event(data[2], LocalDateTime.parse(data[3], formatter));
                if (data[1].equals("1")) {
                    event.setDone();
                }
                tasks.add(event);
            }
        }
        return tasks;
    }

    private static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++)  {
            Task task = tasks.get(i);
            if (task instanceof ToDo) {
                String text = "T | " + (task.getStatus() ? "1" : "0") + " | " + task.getInstruction();
                fileWriter.write(text + System.lineSeparator());
            } else if (task instanceof Deadline) {
                String text = "D | " + (task.getStatus() ? "1" : "0") + " | " + task.getInstruction()
                        + " | " + ((Deadline) task).getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                fileWriter.write(text + System.lineSeparator());
            } else if (task instanceof Event) {
                String text = "E | " + (task.getStatus() ? "1" : "0") + " | " + task.getInstruction()
                        + " | " + ((Event) task).getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                fileWriter.write(text + System.lineSeparator());
            }
        }
        fileWriter.close();
    }

    private static void record(ArrayList<Task> tasks) {
        try {
            writeToFile(tasks);
        } catch (IOException e) {
            ui.showIOException(e);
        }
    }

}
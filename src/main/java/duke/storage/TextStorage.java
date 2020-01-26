package duke.storage;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.io.FileNotFoundException;

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.exceptions.DukeException;

/**
 * Stores data in raw text to a local file.
 */
public class TextStorage implements Storage {
    private String filePath;

    public TextStorage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws FileNotFoundException, DukeException {
        List<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(new File(filePath));
        while (s.hasNextLine()) {
            Task task;
            switch (s.nextLine()) {
            case "todo":
                task = new Todo(s.nextLine());
                break;
            case "deadline":
                task = new Deadline(s.nextLine(), LocalDateTime.parse(s.nextLine()));
                break;
            case "event":
                task = new Event(s.nextLine(), LocalDateTime.parse(s.nextLine()), LocalDateTime.parse(s.nextLine()));
                break;
            default:
                throw new DukeException("Save file corrupt!");
            }
            if (s.nextLine().equals("true")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        s.close();
        return tasks;
    }

    public void save(List<Task> tasks) throws IOException, DukeException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(toSaveable(task) + System.lineSeparator());
        }
        fw.close();
    }

    private String toSaveable(Task task) throws DukeException {
        if (task instanceof Todo) {
            return String.format("todo\n%s\n%s", task.getName(), task.getIsDone());
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.format("deadline\n%s\n%s\n%s", deadline.getName(), deadline.getDateTime(),
                    deadline.getIsDone());
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return String.format("event\n%s\n%s\n%s\n%s", event.getName(), event.getStart(), event.getEnd(),
                    event.getIsDone());
        }
        throw new DukeException("Unknown task type found??");
    }
}
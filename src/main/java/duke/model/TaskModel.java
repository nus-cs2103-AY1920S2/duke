package duke.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a task model that is backed with a flat-file database.
 */
public class TaskModel {
    private final Path storagePath;
    private List<Task> tasks; 

    /**
     * Creates a task model.
     * 
     * @param storagePath Path to the flat-file database
     */
    public TaskModel(Path storagePath) {
        this.storagePath = storagePath;
        try {
            tasks = load();
        } catch (IOException exception) {
            tasks = new ArrayList<>();
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Update tasks with the new list of tasks.
     * 
     * @param tasks The new list of tasks
     * @throws IOException Throws when there is some problem with the writing process
     */
    public void updateTasks(List<Task> tasks) throws IOException {
        if (this.tasks == tasks) {
            return;
        }
        this.tasks = tasks;
        save();
    }

    /**
     * Saves the tasks provided to the disk as a flat file.
     * 
     * @throws IOException Throws when there is some problem with the writing process
     */
    public void save() throws IOException {
        Files.write(
            storagePath,
            tasks.stream()
                    .map(this::serializeTask)
                    .collect(Collectors.toUnmodifiableList()),
            StandardCharsets.UTF_8
        );
    }

    private String serializeTask(Task task) {
        if (task instanceof Todo) {
            Todo todo = (Todo) task;
            return String.format("T,%s,%b", todo.getTitle(), todo.isDone());
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return String.format(
                    "E,%s,%b,%s",
                    event.getTitle(),
                    event.isDone(),
                    event.getDateAt().format(DateTimeFormatter.ISO_DATE)
            );
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.format(
                    "D,%s,%b,%s",
                    deadline.getTitle(),
                    deadline.isDone(),
                    deadline.getDateBy().format(DateTimeFormatter.ISO_DATE)
            );
        } else {
            throw new RuntimeException("Unknown task type");
        }
    }

    /**
     * Loads tasks from the disk (from a flat-file database).
     * 
     * @return Tasks loaded from the disk
     * @throws IOException Throws when there is some problem with the reading process
     */
    public List<Task> load() throws IOException {
        return Files.readAllLines(storagePath).stream()
                .map(this::deserializeTask)
                .collect(Collectors.toUnmodifiableList());
    } 

    private Task deserializeTask(String serialized) {
        String[] tokens = serialized.split(",");
        boolean isDone = Boolean.parseBoolean(tokens[2]);
        if (tokens[0].equals("T")) {
            return new Todo(tokens[1], isDone);
        } else if (tokens[0].equals("E")) {
            LocalDate dateAt = LocalDate.parse(tokens[3]);
            return new Event(tokens[1], isDone, dateAt);
        } else if (tokens[0].equals("D")) {
            LocalDate dateBy = LocalDate.parse(tokens[3]);
            return new Deadline(tokens[1], isDone, dateBy);
        } else {
            throw new RuntimeException("Unknown serialized task type");
        }
    }
}
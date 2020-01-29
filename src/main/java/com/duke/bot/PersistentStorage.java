package com.duke.bot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import com.duke.bot.task.Deadline;
import com.duke.bot.task.Event;
import com.duke.bot.task.Task;
import com.duke.bot.task.Todo;

public class PersistentStorage {
    private final Path storagePath;

    public PersistentStorage(Path storagePath) {
        this.storagePath = storagePath;
    }

    public void save(TaskList tasks) throws IOException {
        Files.write(
            storagePath,
            tasks.getUnderlyingList().stream().map(this::serializeTask).collect(Collectors.toList()),
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

    public TaskList load() throws IOException {
        return new TaskList(Files.readAllLines(storagePath).stream()
                .map(this::deserializeTask)
                .collect(Collectors.toUnmodifiableList()));
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
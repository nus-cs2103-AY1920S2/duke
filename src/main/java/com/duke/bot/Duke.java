package com.duke.bot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Duke {
    private final Scanner scanner;
    private final Path savePath;
    private List<Task> tasks;

    public Duke() {
        scanner = new Scanner(System.in);
        savePath = Path.of(System.getProperty("user.dir"), "data.txt"); 

        tasks = new ArrayList<>();
        if (Files.exists(savePath)) {
            loadTasks();
        }

        print(List.of("Hello! I'm Duke", "What can I do for you?"));
    }

    public void run() {
        boolean hasNext = true;
        do {
            try {
                hasNext = runOneCycle();
            } catch(DukeException exception) {
                print(List.of(exception.getMessage()));
            }
        } while (hasNext);
    }

    private boolean runOneCycle() throws DukeException {
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("bye")) {
            handleByeInput();
            return false;
        }

        if (input.equalsIgnoreCase("list")) {
            handleListInput(); 
        } else if (input.startsWith("done")) {
            handleDoneInput(input); 
        } else if (input.startsWith("delete")) {
            handleDeleteInput(input);
        } else if (input.startsWith("todo")) {
            handleTodoInput(input); 
        } else if (input.startsWith("event")) {
            handleEventInput(input); 
        } else if (input.startsWith("deadline")) {
            handleDeadlineInput(input);
        } else {
            throw new DukeException(String.format("☹ OOPS!!! '%s' is an invalid input.", input));
        }
        return true;
    }

    private void handleByeInput() {
        print(List.of("Bye. Hope to see you again soon!"));
    }
    
    private void handleListInput() {
        print(Stream.concat(
                Stream.of("Here are the tasks in your list:"),
                IntStream.range(0, tasks.size())
                        .boxed()
                        .map(index -> String.format("%d.%s", index + 1, tasks.get(index)))
        )
        .collect(Collectors.toUnmodifiableList()));
    }

    private void handleDoneInput(String input) throws DukeException {
        Pattern donePattern = Pattern.compile("^done\\s+(\\d+)$");
        Matcher doneMatcher = donePattern.matcher(input);
        if (doneMatcher.matches()) {
            int setDoneIndex = Integer.parseInt(doneMatcher.group(1)) - 1;
            if (setDoneIndex >= 0 && setDoneIndex < tasks.size()) {
                Task doneTask = tasks.get(setDoneIndex);
                doneTask.setDone(true);
                print(List.of(
                        "Nice! I've marked this task as done:",
                        "  " + doneTask
                ));
                saveTasks();
            } else {
                throw new DukeException("☹ OOPS!!! Invalid done index.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! Invalid done index.");
        }
    }

    private void handleDeleteInput(String input) throws DukeException {
        Pattern deletePattern = Pattern.compile("^delete\\s+(\\d+)$");
        Matcher deleteMatcher = deletePattern.matcher(input);
        if (deleteMatcher.matches()) {
            int deleteIndex = Integer.parseInt(deleteMatcher.group(1)) - 1;
            if (deleteIndex >= 0 && deleteIndex < tasks.size()) {
                Task deleteTask = tasks.get(deleteIndex);
                tasks.remove(deleteIndex);
                print(List.of(
                        "Noted. I've removed this task:",
                        "  " + deleteTask,
                        String.format("Now you have %d tasks in the list", tasks.size())
                ));
                saveTasks();
            } else {
                throw new DukeException("☹ OOPS!!! Invalid delete index.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! Invalid delete index.");
        }
    }

    private void handleTodoInput(String input) throws DukeException {
        Pattern todoPattern = Pattern.compile("^todo\\s+(.+)$");
        Matcher todoMatcher = todoPattern.matcher(input);
        if (todoMatcher.matches()) {
            Todo newTodo = new Todo(todoMatcher.group(1));
            tasks.add(newTodo);
            reportNewTask(newTodo);
            saveTasks();
        } else {
            throw new DukeException("☹ OOPS!!! Invalid todo.");
        }
    }

    private void handleEventInput(String input) throws DukeException {
        Pattern eventPattern = Pattern.compile("^event\\s+(.+)\\s+/at\\s+(.+)$");
        Matcher eventMatcher = eventPattern.matcher(input);
        if (eventMatcher.matches()) {
            Event newEvent = new Event(eventMatcher.group(1), eventMatcher.group(2));
            tasks.add(newEvent);
            reportNewTask(newEvent);
            saveTasks();
        } else {
            throw new DukeException("☹ OOPS!!! Invalid event.");
        }
    }

    private void handleDeadlineInput(String input) throws DukeException {
        Pattern deadlinePattern = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$");
        Matcher deadlineMatcher = deadlinePattern.matcher(input);
        if (deadlineMatcher.matches()) {
            Deadline newDeadline = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
            tasks.add(newDeadline);
            reportNewTask(newDeadline);
            saveTasks();
        } else {
            throw new DukeException("☹ OOPS!!! Invalid deadline.");
        }
    }

    private void saveTasks() {
        try {
            Files.write(
                    savePath,
                    tasks.stream().map(this::serializeTask).collect(Collectors.toList()),
                    StandardCharsets.UTF_8
            );
        } catch (IOException exception) {
            print(List.of("Failed to write to disk."));
        }
    }

    private String serializeTask(Task task) {
        if (task instanceof Todo) {
            Todo todo = (Todo) task;
            return String.format("T,%s,%b", todo.getTitle(), todo.isDone());
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return String.format("E,%s,%b,%s", event.getTitle(), event.isDone(), event.getDateAt());
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.format("D,%s,%b,%s", deadline.getTitle(), deadline.isDone(), deadline.getDateBy());
        } else {
            throw new RuntimeException("Unknown task type");
        }
    }

    private void loadTasks() {
        try {
            List<String> serializedTasks = Files.readAllLines(savePath);
            tasks = serializedTasks.stream()
                    .map(this::deserializeTask)
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            print(List.of("Failed to read from disk"));
        }
    }

    private Task deserializeTask(String serialized) {
        String[] tokens = serialized.split(",");
        boolean isDone = Boolean.parseBoolean(tokens[2]);
        if (tokens[0].equals("T")) {
            return new Todo(tokens[1], isDone);
        } else if (tokens[0].equals("E")) {
            return new Event(tokens[1], isDone, tokens[3]);
        } else if (tokens[0].equals("D")) {
            return new Deadline(tokens[1], isDone, tokens[3]);
        } else {
            throw new RuntimeException("Unknown serialized task type");
        }
    }

    private void reportNewTask(Task newTask) {
        print(List.of(
                "Got it. I've added this task:",
                "  " + newTask,
                String.format("Now you have %d tasks in the list.", tasks.size())
        ));
    }

    private void print(List<String> lines) {
        System.out.println("    ____________________________________________________________");
        lines.forEach(line -> System.out.println("     " + line));
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

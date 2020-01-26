package com.duke.bot;

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
    private List<Task> tasks;

    public Duke() {
        scanner = new Scanner(System.in);
        tasks = new ArrayList<>();
        print(List.of("Hello! I'm Duke", "What can I do for you?"));
    }

    public void start() {
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                print(Stream.concat(
                        Stream.of("Here are the tasks in your list:"),
                        IntStream.range(0, tasks.size())
                                .boxed()
                                .map(index -> String.format("%d.%s", index + 1, tasks.get(index)))
                )
                .collect(Collectors.toUnmodifiableList()));
            } else if (input.matches("done\\s+\\d+")) {
                int setDoneIndex = Integer.parseInt(input.split("\\s+")[1]) - 1;
                Task doneTask = tasks.get(setDoneIndex);
                doneTask.setDone(true);
                print(List.of(
                        "Nice! I've marked this task as done:",
                        "  " + doneTask
                ));
            } else if (input.startsWith("todo")) {
                Pattern todoPattern = Pattern.compile("^todo\\s+(.+)$");
                Matcher todoMatcher = todoPattern.matcher(input);
                if (todoMatcher.matches()) {
                    Todo newTodo = new Todo(todoMatcher.group(1));
                    tasks.add(newTodo);
                    reportNewTask(newTodo);
                } else {
                    print(List.of("Invalid todo! Try again."));
                }
            } else if (input.startsWith("event")) {
                Pattern eventPattern = Pattern.compile("^event\\s+(.+)\\s+/at\\s+(.+)$");
                Matcher eventMatcher = eventPattern.matcher(input);
                if (eventMatcher.matches()) {
                    Event newEvent = new Event(eventMatcher.group(1), eventMatcher.group(2));
                    tasks.add(newEvent);
                    reportNewTask(newEvent);
                } else {
                    print(List.of("Invalid event! Try again."));
                }
            } else if (input.startsWith("deadline")) {
                Pattern deadlinePattern = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$");
                Matcher deadlineMatcher = deadlinePattern.matcher(input);
                if (deadlineMatcher.matches()) {
                    Deadline newDeadline = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
                    tasks.add(newDeadline);
                    reportNewTask(newDeadline);
                } else {
                    print(List.of("Invalid deadline! Try again."));
                }
            } else {
                print(List.of(String.format("'%s' is an invalid input! Try again.", input)));
            }
            input = scanner.nextLine();
        }

        if (input.equalsIgnoreCase("bye")) {
            print(List.of("Bye. Hope to see you again soon!"));
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
        duke.start();
    }
}

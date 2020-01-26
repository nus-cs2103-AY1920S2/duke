package com.duke.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
                                .map(index -> String.format(
                                        "%d.[%s] %s", index + 1,
                                        tasks.get(index).isDone() ? "\u2713" : "\u2717",
                                        tasks.get(index).getName()
                                ))
                )
                .collect(Collectors.toUnmodifiableList()));
            } else if (input.matches("done \\d+")) {
                int setDoneIndex = Integer.parseInt(input.split("\\s+")[1]) - 1;
                tasks.get(setDoneIndex).setDone(true);
                print(List.of(
                        "Nice! I've marked this task as done:",
                        "  [\u2713] " + tasks.get(setDoneIndex).getName()
                ));
            } else {
                tasks.add(new Task(input));
                print(List.of("added: " + input));
            }
            input = scanner.nextLine();
        }

        if (input.equalsIgnoreCase("bye")) {
            print(List.of("Bye. Hope to see you again soon!"));
        }
    }

    public void print(List<String> lines) {
        System.out.println("    ____________________________________________________________");
        lines.forEach(line -> System.out.println("     " + line));
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}

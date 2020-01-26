package com.duke.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Duke {
    private final Scanner scanner = new Scanner(System.in);
    private List<String> items = new ArrayList<>();

    public Duke() {
        print(List.of("Hello! I'm Duke", "What can I do for you?"));
    }

    public void start() {
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                print(IntStream.range(0, items.size())
                        .boxed()
                        .map(index -> String.format("%d. %s", index + 1, items.get(index)))
                        .collect(Collectors.toUnmodifiableList()));
            } else {
                items.add(input);
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

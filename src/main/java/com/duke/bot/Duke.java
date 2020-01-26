package com.duke.bot;

import java.util.List;
import java.util.Scanner;

public class Duke {
    private final Scanner scanner = new Scanner(System.in);

    public Duke() {
        print(List.of("Hello! I'm Duke", "What can I do for you?"));
    }

    public void start() {
        String command = scanner.nextLine();
        while (!command.equalsIgnoreCase("bye")) {
            print(List.of(command));
            command = scanner.nextLine();
        }

        if (command.equalsIgnoreCase("bye")) {
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

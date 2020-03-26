package com.nus.duke;

import com.nus.duke.controller.TaskController;
import com.nus.duke.parser.Parser;
import com.nus.duke.ui.Greetings;
import java.util.Scanner;
import javafx.util.Pair;
import java.util.List;

public class Duke {
    private static void poll(String[] args) {
        Scanner scan = new Scanner(System.in);
        Boolean contLoop = true;
        TaskController controller = new TaskController();

        while (contLoop) {
            try {
            String input = scan.nextLine();
            Pair<String, String> parsedInput = Parser.tokenize(input);
            String taskName = parsedInput.getValue();

            switch (parsedInput.getKey()) {
            case "list":
                List<String> tasks = controller.getAllTasks();
                Greetings.prettyPrint(tasks);
                break;

            case "find":
                tasks = controller.filterTasks(taskName);
                Greetings.prettyPrint(String.format("Found the following tasks based on filter"));
                Greetings.prettyPrint(tasks);
                break;

            case "mark":
                controller.setMark(taskName, true);
                Greetings.prettyPrint(String.format("Marked task %s", taskName));
                break;

            case "unmark":
                controller.setMark(taskName, false);
                Greetings.prettyPrint(String.format("Unmarked task %s", taskName));
                break;

            case "todo":
                controller.newTodo(taskName);
                Greetings.prettyPrint(String.format("Marked task as todo: %s", taskName));
                break;

            case "deadline":
                controller.newDeadline(taskName);
                Greetings.prettyPrint(String.format("Marked task as deadline: %s", taskName));
                break;

            case "event":
                controller.newEvent(taskName);
                Greetings.prettyPrint(String.format("Marked task as event: %s", taskName));
                break;

            case "delete":
                controller.removeTask(taskName);
                Greetings.prettyPrint(String.format("Deleted task: %s", taskName));
                break;

            case "save":
                controller.persist();
                Greetings.prettyPrint(String.format("Tasks saved to disk"));
                break;

            case "quit":
                Greetings.tearDown();
                contLoop = false;
                break;

            default:
                Greetings.handleUndefined();
            }
            } catch (Exception ex) {
                Greetings.prettyPrint(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Greetings.init();
        poll(args);
    }
}

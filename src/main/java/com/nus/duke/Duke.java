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

        while(contLoop){
            String input = scan.nextLine();
            Pair<String, String> parsedInput = Parser.tokenize(input);
            String taskName = parsedInput.getValue();

            switch(parsedInput.getKey()) {
            case "list":
                List<String> tasks = controller.getAllTasks();
                Greetings.prettyPrint(tasks);
                break;

            case "mark":
                if (controller.checkTask(taskName)) {
                    controller.setMark(taskName, true);
                    Greetings.prettyPrint(String.format("Marked task %s", taskName));
                } else {
                    Greetings.prettyPrint(String.format("Task does not exist", taskName));
                }
                break;

            case "unmark":
                if (controller.checkTask(taskName)) {
                    controller.setMark(taskName, false);
                    Greetings.prettyPrint(String.format("Unmarked task %s", taskName));
                } else {
                    Greetings.prettyPrint(String.format("Task does not exist", taskName));
                }
                break;

            case "todo":
                if (!controller.checkTask(taskName)) {
                    controller.createNewTask(taskName);
                }
                controller.asTodo(taskName);
                Greetings.prettyPrint(String.format("Marked task as todo: %s", taskName));
                break;

            case "deadline":
                if (!controller.checkTask(taskName)) {
                    controller.createNewTask(taskName);
                }
                controller.asDeadline(taskName);
                Greetings.prettyPrint(String.format("Marked task as deadline: %s", taskName));
                break;

            case "event":
                if (!controller.checkTask(taskName)) {
                    controller.createNewTask(taskName);
                }
                controller.asEvent(taskName);
                Greetings.prettyPrint(String.format("Marked task as event: %s", taskName));
                break;

            case "delete":
                break;

            case "quit":
                Greetings.tearDown();
                contLoop = false;
                break;
            default:
                Greetings.handleUndefined();
            }
        }
    }

    public static void main(String[] args) {
        Greetings.init();
        poll(args);
    }
}

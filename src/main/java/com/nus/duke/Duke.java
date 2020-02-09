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

            case "add":
                controller.createNewTask(taskName);
                Greetings.prettyPrint("Added: " + taskName);
                break;

            case "mark":
                if (controller.checkTask(taskName)) {
                    controller.markTask(taskName);
                    Greetings.prettyPrint(String.format("Marked task %s", taskName));
                } else {
                    Greetings.prettyPrint(String.format("Task does not exist", taskName));
                }
                break;

            case "unmark":
                if (controller.checkTask(taskName)) {
                    controller.unmarkTask(taskName);
                    Greetings.prettyPrint(String.format("Unmarked task %s", taskName));
                } else {
                    Greetings.prettyPrint(String.format("Task does not exist", taskName));
                }
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

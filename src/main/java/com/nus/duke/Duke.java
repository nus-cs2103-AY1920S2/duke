package com.nus.duke;

import com.nus.duke.controller.TaskController;
import com.nus.duke.parser.Parser;
import com.nus.duke.ui.Greetings;
import java.util.Scanner;
import javafx.util.Pair;

public class Duke {
    private static void poll(String[] args) {
        Scanner scan = new Scanner(System.in);
        Boolean contLoop = true;
        TaskController controller = new TaskController();

        while(contLoop){
            String input = scan.nextLine();
            Pair<String, String> parsedInput = Parser.tokenize(input);

            switch(parsedInput.getKey()) {
            case "list":
                controller.listAllTasks();
                break;

            case "add":
                controller.createNewTask(parsedInput.getValue());
                break;

            case "mark":
                Greetings.prettyPrint(parsedInput.getValue());
                break;

            case "delete":
                break;

            case "quit":
                Greetings.prettyPrint(parsedInput.getValue());
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

package duke.main;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Duke Setup
        boolean dukeRunning = true;
        List<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        //Try to read form saved data file and restore index, if not create a list to save later
        try {
            taskList = Storage.dataRead();
        } catch (DukeException e) {
            UI.print(e.toString());
        }

        //Welcome Text
        UI.welcome();

        //Main Program now in Switch, might need to turn cases into separate methods soon
        while (dukeRunning) {
            String input = sc.nextLine();
            dukeRunning = Parser.parseCommand(input, taskList);
        }
    }
}

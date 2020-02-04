package duke;

import duke.commands.Command;
import duke.utilities.Parser;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.exceptions.*;

import java.util.Scanner;

/**
 * The Main class used to run the application.
 * Creates the Ui, Storage and TaskList objects.
 * Catches DukeExceptions and prints the error messages.
 * Specifies the path to tasks.txt.
 * Terminates when execute method of Command returns false.
 */
public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("../resources/tasks.txt");
        TaskList taskList = new TaskList(storage.load());
        ui.start();
        Scanner sc = new Scanner(System.in);
        Boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = sc.nextLine();
                Command cmd = Parser.parseInput(userInput);
                isRunning = cmd.execute(storage, taskList, ui);
                ui.promptMsg();
            } catch (DukeException e) {
                ui.errorMsg(e);
                ui.promptMsg();
            }
        }
        sc.close();
    }
}
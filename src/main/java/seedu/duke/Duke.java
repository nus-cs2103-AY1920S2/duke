package seedu.duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for Duke.
     *
     * @param filePath path of the file being opened
     * @throws IOException if an input or output exception occurred
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList(null, null);
        }
    }

    private void runDuke() throws InvalidDateException, InvalidTaskInputException, InvalidCommandException,
            IOException, EmptyDescriptionException, TaskIndexOutOfBoundsException {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputs = input.split(" ", 2);
            String command = inputs[0];
            if (command.equalsIgnoreCase("bye")) {
                sayBye();
                break;
            } else {
                ui.handleCommands(inputs, taskList);
            }
        }
    }

    private void sayBye() {
        System.out.println("Stop procrastinating. See you!");
    }

    /**
     * Main method to run Duke.
     *
     * @param args args
     * @throws IOException if an input or output exception occurred
     * @throws InvalidTaskInputException if an invalid task command is input
     * @throws InvalidDateException if a date is input in a wrong format
     * @throws EmptyDescriptionException if the description of a task is empty
     * @throws TaskIndexOutOfBoundsException if the index of a task being marked as done or being deleted is invalid
     * @throws InvalidCommandException if the command input is not todo, deadline, event, list, delete, or done
     */
    public static void main(String[] args) throws IOException, InvalidTaskInputException, InvalidDateException,
            EmptyDescriptionException, TaskIndexOutOfBoundsException, InvalidCommandException {
        Duke duke;
        duke = new Duke("data/duke.txt");
        duke.runDuke();
    }
}

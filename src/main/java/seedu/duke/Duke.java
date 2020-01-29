package seedu.duke;

import java.io.*;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String filePath) throws IOException, InvalidTaskInputException, InvalidDateException {
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

    public static void main(String[] args) throws IOException, InvalidTaskInputException, InvalidDateException,
            EmptyDescriptionException, TaskIndexOutOfBoundsException, InvalidCommandException {
        Duke duke;
        duke = new Duke("data/duke.txt");
        duke.runDuke();
    }
}

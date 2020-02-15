package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.Parser;
import duke.command.ReminderCommand;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidTimeFormatException;
import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class for duke.
 * Hold Storage, TaskList and Ui object for application
 * Runs the application until the exit command is called.
 */
public class Duke {
    private static final String WELCOME_MESSAGE = "Hello! My name is Megumin, user of the finest magic"
            + " crimson demons possess.\nWhat can I explo- I mean do for you?\nUse command 'help'"
            + " to see what I can do!\n";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private boolean hasInitError = false;

    /**
     * Constructs Duke with the default save directory.
     */
    public Duke() {
        String workingDir = System.getProperty("user.dir");
        Path savePath = Paths.get(workingDir, "data", "duke.txt");
        storage = new Storage(savePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.addMessage(" Sorry, I could not read the save file.\n"
                    + "Please fix before relaunching.\n");
            hasInitError = true;
        } catch (InvalidDateFormatException e) {
            ui.addMessage(e.getMessage() + "(Save file date formatting error)\n"
                    + "Please fix before relaunching.\n");
            hasInitError = true;
        } catch (InvalidTimeFormatException e) {
            ui.addMessage(e.getMessage() + "(Save file time formatting error)\n"
                    + "Please fix before relaunching.\n");
            hasInitError = true;
        }
    }

    /**
     * Main program loop.
     * Runs the program until the exit command is called.
     */
    public void runUntilExit() {
        //Ensure that program does not continue when there is initialization error to protect save file.
        if (hasInitError) {
            ui.printBufferMessage();
            return;
        }

        ui.printMessage(WELCOME_MESSAGE);
        ui.printMessage(getReminder());
        boolean isRunning = true;
        while (isRunning) {
            try {
                Command command = Parser.parseCommand(ui.getUserInput());
                if (command instanceof ExitCommand) {
                    isRunning = false;
                }
                command.execute(tasks, ui, storage);
                ui.printBufferMessage();
            } catch (InvalidCommandException e) {
                ui.printException(e);
            }
        }
    }

    /**
     * Gets the program's response based on the user's input.
     *
     * @param input The user's input.
     * @return The program's response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            command.execute(tasks, ui, storage);
            return getUiMessage();
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the welcome message of the program.
     *
     * @return The welcome message of the program.
     */
    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Gets the reminder for task expiring in the next 7 days.
     *
     * @return reminder containing tasks expiring in the next 7 days.
     */
    public String getReminder() {
        //use the reminder command execution to reuse code
        ReminderCommand reminderCommand = new ReminderCommand("all");
        reminderCommand.execute(tasks, ui, storage);
        return getUiMessage();
    }

    /**
     * Gets whether there is error during initialization.
     *
     * @return true if there is initialization error, false otherwise.
     */
    public boolean getHasInitError() {
        return hasInitError;
    }

    /**
     * Gets the message in the ui buffer and clears the buffer.
     *
     * @return message in the ui buffer.
     */
    public String getUiMessage() {
        String message = ui.getMessageBuffer();
        ui.clearBuffer();
        return message;
    }
}

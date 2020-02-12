package dukeproj;

import dukeproj.command.Command;
import dukeproj.data.Schedule;
import dukeproj.data.TaskList;
import dukeproj.enums.CommandType;
import dukeproj.enums.SayType;
import dukeproj.exception.BadDateException;
import dukeproj.exception.BadDescriptionException;
import dukeproj.exception.DukeDescriptionException;
import dukeproj.exception.InvalidCommandException;

import javafx.application.Application;

import java.io.File;

/**
 * Represents the main working class of DukeProject.
 */
public class Duke {
    /** Object that handles user interface and communicating with user. */
    private Ui ui;
    /** Parser to read commands. */
    private Parser parser;
    /** Storage to read and store Duke into a file. */
    private Storage storage;
    /** List of tasks in Duke. */
    private TaskList taskList;
    /** Calender of tasks stored according to their dates. */
    private Schedule schedule;

    /**
     * Generates a command as a response to a user input String from the GUI.
     *
     * @param input input provided by user from GUI.
     * @return Command that is parsed from the input.
     */
    public Command getCommandResponse(String input) throws InvalidCommandException {
        String[] inputs = input.split(" ", 2);
        CommandType commandType = Parser.commandParser(inputs[0]);
        if (inputs.length < 2) {
            return parser.getCommand(commandType, "");
        } else {
            return parser.getCommand(commandType, inputs[1]);
        }
    }

    /**
     * Generates Duke's response to a command.
     *
     * @param command The command provided.
     * @return Duke's response.
     */
    public String getResponse(Command command) {
        try {
            return command.execute(ui, taskList, storage, schedule);
        } catch (DukeDescriptionException e) {
            return ui.say(SayType.EMPTY_DESCRIPTION);
        } catch (BadDateException e) {
            return ui.say(SayType.BAD_DATE);
        } catch (BadDescriptionException e) {
            return ui.say(SayType.BAD_DESCRIPTION) + e.getMessage();
        }
    }

    /**
     * Returns the User Interface of Duke.
     *
     * @return User Interface.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Constructs a Duke object with a filepath to store the task list.
     */
    public Duke() {
        ui = new Ui();
        schedule = new Schedule();
        storage = new Storage("." + File.separator + "data" + File.separator + "Task.txt");
        taskList = new TaskList(storage.printFileIntoList(schedule));
        parser = new Parser();
    }

    /**
     * Runs the Duke Project. If "text" is input within the CLI, this method will run the CLI version of Duke.
     * Else, as default, it will run the GUI version of Duke.
     *
     * @param args Inputs by user on the CLI.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

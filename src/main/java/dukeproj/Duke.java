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
import java.util.Scanner;

/**
 * Represents the main working class of DukeProject.
 */
public class Duke {
    /** Object that handles user interface and communicating with user. */
    private Ui ui;
    /** Primary I/O object used. */
    private Scanner sc;
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
     * Generates DukeProject. This method will run the main interface of the DukeProject.
     * It will read commands given by the system using a scanner and store results in a task list before writing it
     * into storage.
     */
    public void run() {
        System.out.println(ui.getIntroduction());
        while (sc.hasNext()) {
            String next = sc.next();
            if (next.equals("bye")) {
                break;
            } else {
                try {
                    ui.printLineBreak();
                    CommandType commandType = Parser.commandParser(next);
                    parser.readCommand(commandType);
                } catch (InvalidCommandException e) {
                    System.out.println("Sorry I do not know what that means!");
                    sc.nextLine();
                } catch (DukeDescriptionException e) {
                    System.out.println("OOPS! You forgot to include a description!");
                } catch (BadDescriptionException e) {
                    System.out.println("OOPS! " + e.getMessage());
                } catch (BadDateException e) {
                    System.out.println("Sorry I don't recognise this date format!\n"
                            + "Please make sure the format is: dd mm yy");
                } finally {
                    ui.printLineBreak();
                }
            }
        }
        System.out.println(ui.getExit());
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
     *
     * @param filepath Filepath of the Duke storage.
     */
    public Duke(String filepath, boolean isGui) {
        ui = new Ui();
        schedule = new Schedule();
        storage = new Storage(filepath);
        taskList = new TaskList(storage.printFileIntoList(schedule));
        if (isGui) {
            parser = new Parser();
        } else {
            sc = new Scanner(System.in);
            parser = new Parser(taskList, schedule, storage, sc);
        }
    }

    /**
     * Runs the Duke Project. If "text" is input within the CLI, this method will run the CLI version of Duke.
     * Else, as default, it will run the GUI version of Duke.
     *
     * @param args Inputs by user on the CLI.
     */
    public static void main(String[] args) {
        Application.launch(GuiApp.class, args);
    }
}

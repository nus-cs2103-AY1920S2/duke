package dukeproj;

import dukeproj.data.Calender;
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

    /**
     * Generates a response from a user input String from the GUI.
     *
     * @param input input provided by user from GUI.
     * @return response by Duke.
     */
    public String getResponse(String input) {
        String[] inputs = input.split(" ", 2);
        try {
            CommandType commandType = Parser.commandParser(inputs[0]);
            if (inputs.length < 2) {
                return parser.getCommandResponse(commandType, "");
            } else {
                return parser.getCommandResponse(commandType, inputs[1]);
            }
        } catch (InvalidCommandException e) {
            return ui.say(SayType.INVALID_COMMAND);
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
            }
            else {
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
     * @return User Interface.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Constructs a Duke object with a filepath to store the task list.
     * @param filepath Filepath of the Duke storage.
     */
    public Duke(String filepath, boolean isGui) {
        ui = new Ui();
        Calender calender = new Calender();
        Storage storage = new Storage(filepath);
        TaskList taskList = new TaskList(storage.printFileIntoList(calender));
        if (isGui) {
            parser = new Parser(ui, taskList, calender, storage);
        } else {
            sc = new Scanner(System.in);
            parser = new Parser(taskList, calender, storage, sc);
        }
    }

    public static void main(String[] args) {
        if (args.length > 0 && args[0].toUpperCase().equals("text")) {
            new Duke("." + File.separator + "data" + File.separator + "Task.txt", false).run();
        } else {
            Application.launch(GuiApp.class, args);
        }
    }
}

package dukeproj;

import dukeproj.data.Calender;
import dukeproj.data.TaskList;
import dukeproj.enums.Command;
import dukeproj.exception.BadDateException;
import dukeproj.exception.BadDescriptionException;
import dukeproj.exception.DukeDescriptionException;
import dukeproj.exception.InvalidCommandException;

import javafx.application.Application;

import java.util.Scanner;
import java.io.File;

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
     * Generates DukeProject. This method will run the main interface of the DukeProject.
     * It will read commands given by the system using a scanner and store results in a task list before writing it
     * into storage.
     */
    public void run() {
        ui.getIntroduction();
        while (sc.hasNext()) {
            String next = sc.next();
            if (next.equals("bye")) {
                break;
            }
            else {
                try {
                    ui.printLineBreak();
                    Command command = Parser.commandParser(next);
                    parser.readCommand(command);
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
        ui.exit();
    }

    /**
     * Constructs a Duke object with a filepath to store the task list.
     * @param filepath Filepath of the Duke storage.
     */
    public Duke(String filepath) {
        ui = new Ui();
        Calender calender = new Calender();
        Storage storage = new Storage(filepath);
        TaskList taskList = new TaskList(storage.printFileIntoList(calender));
        sc = new Scanner(System.in);
        parser = new Parser(taskList, calender, storage, sc);
    }

    public static void main(String[] args) {
        Application.launch(Ui.class, args);
        //new Duke("." + File.separator + "data" +
                //File.separator + "Task.txt").run();
    }
}

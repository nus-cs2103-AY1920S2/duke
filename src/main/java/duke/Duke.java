package duke;

import duke.data.Calender;
import duke.data.TaskList;
import duke.enums.Command;
import duke.exception.BadDateException;
import duke.exception.BadDescriptionException;
import duke.exception.DukeDescriptionException;
import duke.exception.InvalidCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;

/**
 * Represents the main working class of DukeProject.
 */
public class Duke {
    /** Primary data structure to store the tasks. */
    private TaskList taskList;
    /** Storage to read/write task list from/into files. */
    private Storage storage;
    /** Data structure to store tasks aligned by dates.*/
    private Calender calender;
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
                    ui.lineBreak();
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
                    System.out.println("Sorry I don't recognise this date format!\n" +
                            "Please make sure the format is: dd mm yy");
                } finally {
                    ui.lineBreak();
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
        calender = new Calender();
        storage = new Storage(filepath);
        taskList = new TaskList(storage.printFileIntoList(calender));
        sc = new Scanner(System.in);
        parser = new Parser(taskList, calender, storage, sc);
    }

    public static void main(String[] args) {
        new Duke("." + File.separator + "data" +
                File.separator + "Task.txt").run();
    }
}

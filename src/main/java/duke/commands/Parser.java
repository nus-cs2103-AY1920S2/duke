package duke.commands;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * deals with making sense of the user command.
 */
public class Parser {

    /**
     * the formatter used to parse user input.
     */
    public static final DateTimeFormatter PARSER = DateTimeFormatter
            .ofPattern("d-M-yyyy HHmm");
    /**
     * the formatter used for the file data format.
     */
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("MMM d yyyy ha");


    /**
     * scans user input.
     */
    private Scanner sc = new Scanner(System.in);
    /**
     * creates a new Ui object to access functions.
     */
    private Ui ui = new Ui();
    /**
     * the Tasklist.
     */
    private TaskList taskList;

    /**
     * creates a new Parser object and gets Tasklist from DUke.
     */
    public Parser() {
        this.taskList = Duke.taskList;
    }

    /**
     * scans user input until a bye command is reached. parses each line
     * entered in by the user
     */
    public void parse() {
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    taskList.list();

                } else if (command.startsWith("done")) {
                    ui.checkCommand(command, "done", taskList.size());
                    taskList.done(Integer.valueOf(command.split(" ")[1]) - 1);

                } else if (command.startsWith("delete")) {
                    ui.checkCommand(command, "delete", taskList.size());
                    taskList.delete(Integer.valueOf(command.split(" ")[1]) - 1);
                } else {
                    String[] arr = command.split("/");
                    String[] description = (arr[0].split(" ", 2));

                    if (command.startsWith("todo")) {
                        ui.checkDescription(description, "todo");
                        taskList.add(new ToDo(description[1]), "print");

                    } else if (command.startsWith("deadline")) {
                        ui.checkDescription(description, "deadline");
                        ui.checkTime(arr, "deadline");
                        taskList.add(new Deadline(description[1],
                                arr[1].split(" ", 2)[1], PARSER), "print");

                    } else if (command.startsWith("event")) {
                        ui.checkDescription(description, "event");
                        ui.checkTime(arr, "event");
                        taskList.add(new Event(description[1],
                                arr[1].split(" ", 2)[1], PARSER), "print");

                    } else if (command.startsWith("find")) {
                        taskList.find(description[1]);

                    } else {
                        throw new DukeException("I'm sorry, but I don't know "
                                + "what that means :-(");
                    }

                }
            } catch (DukeException e) {
                ui.dukePrint("☹ OOPS!!! " + e.getMessage() + "\n");
            } finally {
                command = sc.nextLine();
            }
        }
        ui.dukePrint("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}

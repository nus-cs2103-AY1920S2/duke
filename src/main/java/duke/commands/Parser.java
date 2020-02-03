package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.time.format.DateTimeFormatter;

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
     * creates a new Ui object to access functions.
     */
    private Ui ui = new Ui();
    /**
     * the Tasklist.
     */
    private TaskList taskList;

    /**
     * creates a new Parser object and gets Tasklist from Duke.
     * @param taskList the TaskList
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * scans user input until a bye command is reached. parses each line
     * entered in by the user
     * @param command user command
     * @return Duke's response to user command
     */
    public String parse(String command) throws DukeException {
        if (command.equals("list")) {
            return taskList.list();

        } else if (command.startsWith("done")) {
            ui.checkCommand(command, "done", taskList.size());
            return taskList.done(Integer.valueOf(command.split(" ")[1]) - 1);

        } else if (command.startsWith("delete")) {
            ui.checkCommand(command, "delete", taskList.size());
            return taskList.delete(Integer.valueOf(command.split(" ")[1]) - 1);
        } else {
            String[] arr = command.split("/");
            String[] description = (arr[0].split(" ", 2));

            if (command.startsWith("todo")) {
                ui.checkDescription(description, "todo");
                return taskList.add(new ToDo(description[1]), "print");

            } else if (command.startsWith("deadline")) {
                ui.checkDescription(description, "deadline");
                ui.checkTime(arr, "deadline");
                return taskList.add(new Deadline(description[1],
                        arr[1].split(" ", 2)[1], PARSER), "print");

            } else if (command.startsWith("event")) {
                ui.checkDescription(description, "event");
                ui.checkTime(arr, "event");
                return taskList.add(new Event(description[1],
                        arr[1].split(" ", 2)[1], PARSER), "print");

            } else if (command.startsWith("find")) {
                return taskList.find(description[1]);

            } else {
                throw new DukeException("I'm sorry, but I don't know "
                        + "what that means :-(");
            }

        }
    }
}

package duke.command;

import duke.task.TaskList;
import duke.DukeException;

import java.util.Arrays;

/**
 * Acts as the parser for Duke program commands.
 * Makes sense of user commands and helps to execute it in Duke.
 *
 * @author Dargo
 */
public class Parser {

    private TaskList listOfTasks;

    /**
     * Identifies user input to pinpoint specific command given.
     * Executes the command in the TaskList object.
     *
     * @param listOfTasks TaskList of current Duke program
     */
    public Parser(TaskList listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Executes the command on TaskList based on user input given.
     *
     * @param inputCommand String command given by user.
     */
    public String executeCommand(String inputCommand) {

        String[] commandSplit = inputCommand.split(" ");
        String basicCommand = commandSplit[0];
        if (basicCommand.equals("list")) {

            // List command lists out all tasks
            return this.listOfTasks.list();

        } else if (basicCommand.equals("done")) {

            // Checks the task as 'Done'
            return this.listOfTasks.done(commandSplit[1]);

        } else if (basicCommand.equals("todo") || basicCommand.equals("deadline") || basicCommand.equals("event")) {

            String type = commandSplit[0];
            try {
                // Test string to see if exception should be thrown
                if (commandSplit.length <= 1) {
                    throw new DukeException(type);
                }

                // Adds the task into the list of tasks
                return this.listOfTasks.add(type, inputCommand);

            } catch (DukeException dukeException) {

                return dukeException.toString();

            } catch (Exception e) {

                return e.toString();

            }
        } else if (basicCommand.equals("delete")) {

            // Deletes the task(s)
            return this.listOfTasks.delete(commandSplit[1]);

        } else if (basicCommand.equals("find")) {

            // Finds task with keywords.
            return this.listOfTasks.find(commandSplit[1]);

        } else if (basicCommand.equals("priority")) {

            // Assigns priority to task.
            return this.listOfTasks.priority(commandSplit[1], commandSplit[2]);
        } else if (basicCommand.equals("clear")) {

            // Clears the list.
            return this.listOfTasks.clearAll();
        }

        assert(!basicCommand.equals("list"));
        assert(!basicCommand.equals("done"));
        assert(!basicCommand.equals("todo"));
        assert(!basicCommand.equals("deadline"));
        assert(!basicCommand.equals("event"));
        assert(!basicCommand.equals("delete"));
        assert(!basicCommand.equals("find"));

        return (new DukeException("error").toString());
    }
}

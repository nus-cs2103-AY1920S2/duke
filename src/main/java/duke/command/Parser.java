package duke.command;

import duke.task.TaskList;
import duke.DukeException;

public class Parser {

    private TaskList listOfTasks;

    public Parser(TaskList listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void executeCommand(String inputCommand) {

        String[] commandSplit = inputCommand.split(" ");
        String basicCommand = commandSplit[0];
        if (basicCommand.equals("list")) {

            // List command lists out all tasks
            this.listOfTasks.list();

        } else if (basicCommand.equals("done")) {

            // Checks the task as 'Done'
            this.listOfTasks.done(commandSplit[1]);

        } else if (basicCommand.equals("todo") || basicCommand.equals("deadline") || basicCommand.equals("event")) {

            String type = commandSplit[0];
            try {
                // Test string to see if exception should be thrown
                if (commandSplit.length <= 1) {
                    throw new DukeException(type);
                }

                // Adds the task into the list of tasks
                this.listOfTasks.add(type, inputCommand);

            } catch (DukeException dukeException) {
                System.out.println(dukeException);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (basicCommand.equals("delete")) {

            // Deletes the task
            this.listOfTasks.delete(commandSplit[1]);

        } else {
            System.out.println(new DukeException("error"));
        }
    }
}

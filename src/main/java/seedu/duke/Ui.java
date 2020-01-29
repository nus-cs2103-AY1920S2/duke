package seedu.duke;

import java.io.IOException;

/**
 * Deals with interactions with the user.
 *
 * <p>Contains methods to greet, show file loading error, and handle user commands.
 *
 */
public class Ui {
    Parser parser;
    public static final String LIST = "list";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DELETE = "delete";
    public static final String DONE = "done";

    public Ui() {
        parser = new Parser();
    }

    public void greet() {
        System.out.println("Hi there, I'm Dodo!\nHow may I help you today?");
    }

    public void showLoadingError() {
        System.out.println("Loading error. Try again!");
    }

    /**
     * Adds, changes, and deletes the content of the list according to the user's command input.
     *
     * @param inputs the user's line input converted into a String array
     * @param taskList the taskList object that will be modified
     * @throws EmptyDescriptionException if the description of a task is empty
     * @throws InvalidTaskInputException if an invalid task command is input
     * @throws InvalidCommandException if the command inputted is not todo, deadline, event, list, delete, or done
     * @throws TaskIndexOutOfBoundsException if the index of a task being marked as done or being deleted is invalid
     * @throws IOException if an input or output exception occurred
     * @throws InvalidDateException if a date is input in a wrong format
     */
    public void handleCommands(String[] inputs, TaskList taskList) throws EmptyDescriptionException,
            InvalidTaskInputException, InvalidCommandException, TaskIndexOutOfBoundsException,
            IOException, InvalidDateException {
        String command = parser.processCommand(inputs[0].trim(), inputs);
        if (command.equals(TODO)) {
            String desc = inputs[1];
            taskList.addTodo(desc, "N");
        } else if (command.equals(DEADLINE)) {
            String desc = inputs[1];
            taskList.addDeadline(desc, "N");
        } else if (command.equals(EVENT)) {
            String desc = inputs[1];
            taskList.addEvent(desc, "N");
        } else if (command.equals(LIST)) {
            taskList.printList();
        } else if (command.equals(DONE)) {
            int index = Integer.parseInt(inputs[1]);
            taskList.markTaskAsDone(index);
        } else if (command.equals(DELETE)) {
            int index = Integer.parseInt(inputs[1]);
            taskList.deleteTask(index);
        } else {
            throw new InvalidCommandException();
        }
    }

}

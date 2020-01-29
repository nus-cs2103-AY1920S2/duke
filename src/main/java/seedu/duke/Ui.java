package seedu.duke;

import java.io.IOException;

/**
 * Deals with interactions with the user.
 *
 * <p>Contains methods to greet, show file loading error, and handle user commands.
 */
public class Ui {
    Parser parser;
    public static final String LIST = "list";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DELETE = "delete";
    public static final String DONE = "done";
    public static final String FIND = "find";

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
     */
    public void handleCommands(String[] inputs, TaskList taskList) {
        String command = parser.processCommand(inputs[0].trim());
        try {
            if (command.equals(TODO)) {
                if (inputs.length == 1) {
                    throw new EmptyDescriptionException();
                }
                String desc = inputs[1];
                taskList.addTodo(desc, "N");
            } else if (command.equals(DEADLINE)) {
                if (inputs.length == 1) {
                    throw new EmptyDescriptionException();
                }
                String desc = inputs[1];
                taskList.addDeadline(desc, "N");
            } else if (command.equals(EVENT)) {
                if (inputs.length == 1) {
                    throw new EmptyDescriptionException();
                }
                String desc = inputs[1];
                taskList.addEvent(desc, "N");
            } else if (command.equals(LIST)) {
                taskList.printList();
            } else if (command.equals(DONE)) {
                if (inputs.length == 1) {
                    throw new EmptyDescriptionException();
                } else if (!isNumeric(inputs[1])) {
                    throw new InvalidTaskInputException();
                }
                int index = Integer.parseInt(inputs[1]);
                if (index < 1 || index > TaskList.tasks.size()) {
                    throw new TaskIndexOutOfBoundsException();
                }
                taskList.markTaskAsDone(index);
            } else if (command.equals(DELETE)) {
                if (inputs.length == 1) {
                    throw new EmptyDescriptionException();
                } else if (!isNumeric(inputs[1])) {
                    throw new InvalidTaskInputException();
                }
                int index = Integer.parseInt(inputs[1]);
                if (index < 1 || index > TaskList.tasks.size()) {
                    throw new TaskIndexOutOfBoundsException();
                }
                taskList.deleteTask(index);
            } else if (command.equals(FIND)) {
                String desc = inputs[1];
                taskList.findTask(desc);
            } else {
                throw new InvalidCommandException();
            }
        } catch (DukeException | IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Checks if a string can be converted to an integer.
     * @param strNum the string to be checked
     * @return true if the string can be converted to an integer
     */
    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int intNum = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}

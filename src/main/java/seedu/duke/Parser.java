package seedu.duke;

import java.io.IOException;

public class Parser {
    private boolean hasNextCommand = true;

    public Parser() {

    }

    protected boolean hasNextCommand() {
        return hasNextCommand;
    }

    /**
     * Displays, modifies, and finds the content of the list according to the user's command input.
     */
    public void handleCommands(String[] inputs, TaskList taskList) {
        String command = inputs[0].trim();
        try {
            if (command.equals("list")) {
                taskList.printList();
            } else if (command.equals("bye")) {
                hasNextCommand = false;
            } else if (command.equals("todo")) {
                if (inputs.length == 1) {
                    throw new EmptyDescriptionException();
                }
                String desc = inputs[1];
                taskList.addTodo(desc, "N");
            } else if (command.equals("deadline")) {
                if (inputs.length == 1) {
                    throw new EmptyDescriptionException();
                }
                String desc = inputs[1];
                taskList.addDeadline(desc, "N");
            } else if (command.equals("event")) {
                if (inputs.length == 1) {
                    throw new EmptyDescriptionException();
                }
                String desc = inputs[1];
                taskList.addEvent(desc, "N");
            } else if (command.equals("done")) {
                if (inputs.length == 1) {
                    throw new EmptyDescriptionException();
                }
                if (!isNumeric(inputs[1])) {
                    throw new InvalidTaskInputException();
                }
                int index = Integer.parseInt(inputs[1]);
                if (index < 1 || index > taskList.getTasks().size()) {
                    throw new TaskIndexOutOfBoundsException();
                }
                taskList.markTaskAsDone(index);
            } else if (command.equals("delete")) {
                if (inputs.length == 1) {
                    throw new EmptyDescriptionException();
                }
                if (!isNumeric(inputs[1])) {
                    throw new InvalidTaskInputException();
                }
                int index = Integer.parseInt(inputs[1]);
                if (index < 1 || index > taskList.getTasks().size()) {
                    throw new TaskIndexOutOfBoundsException();
                }
                taskList.deleteTask(index);
            } else if (command.equals("find")) {
                if (inputs.length == 1) {
                    throw new EmptyDescriptionException();
                }
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
     * @param strNum The string to be checked.
     * @return true if the string can be converted to an integer.
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

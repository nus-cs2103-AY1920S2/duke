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
     * Adds, changes, and deletes the content of the list according to the user's command input.
     */
    public void handleCommands(String[] inputs, TaskList taskList) {
        String command = inputs[0].trim();
        try {
            if (command.equals("list")) {
                taskList.printList();
            } else if (command.equals("bye")) {
                hasNextCommand = false;
                sayBye();
            } else {
                if (inputs.length == 1) {
                    throw new EmptyDescriptionException();
                } else if (command.equals("todo")) {
                    String desc = inputs[1];
                    taskList.addTodo(desc, "N");
                } else if (command.equals("deadline")) {
                    String desc = inputs[1];
                    taskList.addDeadline(desc, "N");
                } else if (command.equals("event")) {
                    String desc = inputs[1];
                    taskList.addEvent(desc, "N");
                } else if (command.equals("done")) {
                    if (!isNumeric(inputs[1])) {
                        throw new InvalidTaskInputException();
                    }
                    int index = Integer.parseInt(inputs[1]);
                    if (index < 1 || index > taskList.getTasks().size()) {
                        throw new TaskIndexOutOfBoundsException();
                    }
                    taskList.markTaskAsDone(index);
                } else if (command.equals("delete")) {
                    if (!isNumeric(inputs[1])) {
                        throw new InvalidTaskInputException();
                    }
                    int index = Integer.parseInt(inputs[1]);
                    if (index < 1 || index > taskList.getTasks().size()) {
                        throw new TaskIndexOutOfBoundsException();
                    }
                    taskList.deleteTask(index);
                } else if (command.equals("find")) {
                    String desc = inputs[1];
                    taskList.findTask(desc);
                } else {
                    throw new InvalidCommandException();
                }
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

    private void sayBye() {
        System.out.println("Stop procrastinating. See you!");
    }
}

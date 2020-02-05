package parser;

import exception.InvalidFormatException;
import exception.InvalidInstructionException;
import tasks.TaskList;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parse commands and decide on how to handle them accordingly.
     * @param text String of text describing our command.
     * @param tasks The existing list of tasks.
     */
    public String handleTaskCommand(String text, TaskList tasks) {
        String[] textArray = text.split(" ");
        String instruction = textArray[0];
        String response = "";
        try {
            if (instruction.equals("list")) {
                return tasks.getTasksAsString();
            } else if (instruction.equals("done")) {
                return tasks.setDone(textArray[1]);
            } else if (instruction.equals("delete")) {
                return tasks.deleteTask(textArray[1]);
            } else if (instruction.equals("find")) {
                tasks.getListOfMatch(textArray[1]).getTasksAsString();
            } else if ((instruction.equals("todo")
                    || instruction.equals("deadline")
                    || instruction.equals("event"))) {
                tasks.addNewTask(textArray);
                return "OK. Added your task.";
            } else {
                throw new InvalidInstructionException("You have entered invalid input.");
            }
        } catch (InvalidInstructionException e) {
            return e.getMessage();
        } catch (InvalidFormatException e) {
            return e.getMessage();
        }
        return response;
    }
}

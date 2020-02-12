package parser;

import exception.InvalidFormatException;
import exception.InvalidInstructionException;
import tasks.TaskList;
import ui.Ui;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parse commands and decide on how to handle them accordingly.
     * @param text String representing the command.
     * @param tasks TaskList containing existing tasks.
     * @return String representing response.
     */
    public String handleTaskCommand(String text, TaskList tasks, Ui ui) {
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
                return tasks.addNewTask(textArray);
            } else if (instruction.equals("help")) {
                return ui.getHelpMessage();
            } else {
                throw new InvalidInstructionException("You have entered invalid input."
                        + "\nEnter help to all all possible commands.");
            }
        } catch (InvalidInstructionException e) {
            return e.getMessage();
        } catch (InvalidFormatException e) {
            return e.getMessage();
        }
        assert !response.equals("") : "you should have a proper response by now";
        return response;
    }
}

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
    public void handleTaskCommand(String text, TaskList tasks) {
        String[] textArray = text.split(" ");
        String instruction = textArray[0];
        try {
            if (instruction.equals("list")) {
                tasks.printTaskList();
            } else if (instruction.equals("done")) {
                tasks.setDone(textArray[1]);
            } else if (instruction.equals("delete")) {
                tasks.deleteTask(textArray[1]);
            } else if ((instruction.equals("todo")
                    || instruction.equals("deadline")
                    || instruction.equals("event"))) {
                tasks.addNewTask(textArray);
            } else {
                throw new InvalidInstructionException("You have entered invalid input.");
            }
        } catch (InvalidInstructionException e) {
            System.out.println(e.getMessage());
        } catch (InvalidFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}

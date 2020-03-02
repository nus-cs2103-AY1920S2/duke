package parser;

import exception.InvalidFormatException;
import exception.InvalidInstructionException;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.Stack;

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

    private Stack<String> commands = new Stack<>();

    /**
     * Handles commands represented as text and returns a string representing feedback.
     * @param text String representing command.
     * @param tasks TaskList that we act on given certain commands.
     * @param ui Ui that we use to retrieve replies.
     * @return String message in response to command.
     */
    public String handleTaskCommand(String text, TaskList tasks, Ui ui) {
        String[] textArray = text.split(" ");
        String instruction = textArray[0];
        String response = "";
        try {
            if (instruction.equals("list")) {
                response =  tasks.getTasksAsString();
            } else if (instruction.equals("undo")) {
                if (commands.empty()) {
                    throw new InvalidInstructionException("Nothing to undo");
                }
                response = handleUndo(commands.pop(), tasks);
            } else if (instruction.equals("done")) {
                commands.push(text);
                response = tasks.setDone(textArray[1]);
            } else if (instruction.equals("delete")) {
                commands.push(text);
                response = tasks.deleteTask(textArray[1]);
            } else if (instruction.equals("find")) {
                if (textArray.length <= 1) {
                    throw new InvalidFormatException("Find nothing? Get nothing");
                }
                response = tasks.getListOfMatch(textArray[1]).getTasksAsString();
            } else if ((instruction.equals("todo")
                    || instruction.equals("deadline")
                    || instruction.equals("event"))) {
                commands.push(text);
                response = tasks.addNewTask(textArray);
            } else if (instruction.equals("help")) {
                response =  ui.getHelpMessage();
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

    private String handleUndo(String text, TaskList tasks) {
        String[] textArray = text.split(" ");
        String instruction = textArray[0];
        switch (instruction) {
        case "done":
            return tasks.setNotDone(textArray[1]);
        case "delete":
            return tasks.undoDelete();
        default:
            return tasks.deleteTask(String.valueOf(tasks.getTaskCount()));
        }
    }
}

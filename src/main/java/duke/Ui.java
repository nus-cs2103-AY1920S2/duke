package duke;

import duke.tasks.Task;
import duke.exceptions.*;

//import java.io.IOException;
import java.util.ArrayList;
//import java.io.BufferedReader;
//import java.io.FileReader;

/**
 * The UI class for Duke.
 * Currently customised to Squirtle.
 * Returns custom messages for each action taken by the application.
 **/
public class Ui {
    public String start() {
        return this.promptMsg();
    }

    /**
     * Prompts the user for next command.
     * @return prompt string
     */
    public String promptMsg() {
        return "\nWhat will SQUIRTLE do?";
    }

    /**
     * Exit message.
     * @return exit string
     */
    public String exitMsg() {
        return "SQUIRTLE shall return to pokeball!";
    }

    /**
     * Finds tasks that contain the keyword given by the user. If no task found, prints a separate message.
     * @param lst ArrayList of all tasks filtered by the TaskList that contain the keyword.
     * @param keyword String passed by the user
     * @return tasks found string
     */
    public String findMsg(ArrayList<Task> lst, String keyword) {
        if (lst.size() == 0) { // no task containing the keyword found
            return "Uh oh! SQUIRTLE could not find anything with " + keyword + "!";
        } else {
            return "SQUIRTLE discovered " + lst.size() + " thing(s) containing " + keyword + ": \n" + this.printList(lst);
        }
    }

    /**
     * Lists all the tasks currently in the TaskList. If no tasks in the TaskList, prints a separate message.
     * @param lst ArrayList of all tasks currently in the TaskList.
     * @return list of tasks
     */
    public String listMsg(ArrayList<Task> lst) {
        if (lst.size() == 0) {
            return "SQUIRTLE has nothing to do ~~";
        } else {
            return "SQUIRTLE has to attack: \n" + this.printList(lst);
        }
    }

    /**
     * A helper method for listMsg() that prints each given task in a separate line.
     * @param lst ArrayList of all tasks currently in the TaskList.
     * @return list of tasks
     */
    public String printList(ArrayList<Task> lst) {
        String[] taskStrings = new String[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            taskStrings[i] = "\t" + (i + 1) + ". " + lst.get(i);
        }
        return String.join("\n", taskStrings);
    }

    /**
     * Returns a string to guide the user in updating tasks. Updating has 2 stages:
     * 1. When the user is selecting which task they wish to update, and
     * 2. The user selects what part of the task to update, and provides the desired change.
     * @param updateStage current update stage
     * @param task task the user wishes to update, or the newly updated task
     * @return a message depending on the current updating stage
     */
    public String updateMsg(int updateStage, Task task) {
        switch (updateStage) {
            case 1:
                return "SQUIRTLE is changing: " + task.toString() + "\nWhat shall Squirtle change?\n('des' for description, 'date' for date)";
            case 2:
                return "SQUIRTLE! Changed to: " + task.toString();
            default:
                assert false;
                return "";
        }
    }

    /**
     * Returns a message when the user is adding a task to the TaskList.
     * @param task the task the user just created
     * @param lstSize number of tasks so far
     * @return task message
     */
    public String taskMsg(Task task, int lstSize) {
        return "SQUIRTLE is adding: " + task.toString() + "!" + "\nSQUIRTLE now has "
                + lstSize + " thing(s) to do!";
    }

    /**
     * Returns a message when the user is deleting a task
     * @param task the task being deleted
     * @return delete message
     */
    public String deleteMsg(Task task) {
        return "SQUIRTLE ate: " + task.toString();
    }

    /**
     * Returns a message when the user indicates that they are done with a task.
     * @param task the task to mark as done
     * @return done message
     */
    public String doneMsg(Task task) {
        return "SQUIRTLE used water gun on: \n\t" + task.toString() + "!\n\nIt is super effective!";
    }

    /**
     * Returns a list of all available commands for duke. This method is called when the user types in 'help'.
     * @return a list of all available commands for the user.
     */
    public String helpMsg() {
        return "Here is what SQUIRTLE can do!" +
                this.blankLine() +
                "create new task:" +
                "\n\ttodo <description>" +
                "\n\tevent <description> /at <date>" +
                "\n\tdeadline <description> /by <date>" +
                   this.blankLine() +
                "update task:\n\tupdate <task number>" +
                   this.blankLine() +
                "find task:\n\tfind <keyword>" +
                   this.blankLine() +
                "delete task:\n\tdelete <task number>" +
                   this.blankLine() +
                "mark task as done:\n\tdone <task number>" +
                   this.blankLine() +
                "list all tasks:\n\tlist" +
                   this.blankLine() +
                "type help to ask SQUIRTLE what SQUIRTLE can do again!";
    }



    /**
     * Called in the main Duke class, when any DukeException is thrown. Returns customised messages based on the type of DukeError the DukeException contains.
     * @param e the DukeException being passed
     * @return customised error message
     */
    public String errorMsg(DukeException e) { // error responses
        switch (e.error) {
            case NUMBER: // error in accessing list, no such task exists
                return "SQUIRTLE cannot find task!";
            case INSUFFICIENT:
                return "SQUIRTLE needs more info!";
            case COMMAND:
                return "SQUIRTLE doesn't understand!";
            case DATEFORMAT:
                return "SQUIRTLE doesn't understand this date!\n\tFormat: d/M/YYYY or YYYY-MM-dd";
            case KEYWORDS:
                return "SQUIRTLE is confused! Give SQUIRTLE one keyword!";
            case FILEPARSE:
                return "SQUIRTLE cannot read your file!";
            case TASKPARSE:
                return "SQUIRTLE cannot write on your file!";
            case UPDATE:
                return "SQUIRTLE cannot understand your update! Check your update item!";
            default:
                assert false: "No exception that is not a duke exception should be caught in the main Duke class";
                return "An error not included in DukeExceptions was thrown";
        }
    }


    private String blankLine() {
        return "\n\n";
    }

}

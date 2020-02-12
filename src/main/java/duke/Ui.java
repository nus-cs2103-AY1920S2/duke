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

    public String promptMsg() {
        return "\nWhat will SQUIRTLE do?";
    }

    public String exitMsg() {
        return "SQUIRTLE shall return to pokeball!";
    }

    public String findMsg(ArrayList<Task> lst, String keyword) {
        if (lst.size() == 0) { // no task containing the keyword found
            return "Uh oh! SQUIRTLE could not find anything with " + keyword + "!";
        } else {
            return "SQUIRTLE discovered " + lst.size() + " thing(s) containing " + keyword + ": \n" + this.printList(lst);
        }
    }

    public String listMsg(ArrayList<Task> lst) {
        if (lst.size() == 0) {
            return "SQUIRTLE has nothing to do ~~";
        } else {
            return "SQUIRTLE has to attack: \n" + this.printList(lst);
        }
    }

    public String printList(ArrayList<Task> lst) {
        String[] taskStrings = new String[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            taskStrings[i] = "\t" + (i + 1) + ". " + lst.get(i);
        }
        return String.join("\n", taskStrings);
    }

    public String updateMsg(int updateStage, Task task) {
        switch (updateStage) {
            case 1:
                return "SQUIRTLE is changing: " + task.toString() + "\nWhat shall Squirtle change?";
            case 2:
                return "SQUIRTLE! Changed to: " + task.toString();
            default:
                assert false;
                return "";
        }
    }

    public String taskMsg(Task task, int lstSize) {
        return "SQUIRTLE is adding: " + task.toString() + "!" + "\nSQUIRTLE now has "
                + lstSize + " thing(s) to do!";
    }

    public String deleteMsg(Task task) {
        return "SQUIRTLE ate: " + task.toString();
    }

    public String doneMsg(Task task) {
        return "SQUIRTLE used water gun on: \n\t" + task.toString() + "!\n\nIt is super effective!";
    }

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

    public String lineBreak() {
        String lineBreak = "\n------------------------------\n";
        return lineBreak;
    }

    public String blankLine() {
        return "\n\n";
    }

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
                return "Error in error msg portion of Ui!";
        }
    }

    public String welcomeMsg() {
        return "SQUIRTLE! I'M HERE TO HELP! SQUIRTLE!";
    }

}

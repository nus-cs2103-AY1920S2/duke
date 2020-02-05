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
        return this.welcomeMsg() + this.promptMsg();
    }

    public String promptMsg() {
        this.lineBreak();
        return "What will SQUIRTLE do?";
    }

    public String exitMsg() {
        return this.defaultMsg("SQUIRTLE shall retreat!!");
    }

    public String findMsg(ArrayList<Task> lst, String keyword) {
        if (lst.size() == 0) { // no task containing the keyword found
            return this.defaultMsg("Uh oh! SQUIRTLE could not find anything with " + keyword + "!!");
        } else {
            return this.defaultMsg("SQUIRTLE discovered " + lst.size() + " thing(s) containing " + keyword + ": ") + this.printList(lst);
        }
    }

    public String listMsg(ArrayList<Task> lst) {
        return this.defaultMsg("SQUIRTLE has to attack: ") + this.printList(lst);
    }

    public String printList(ArrayList<Task> lst) {
        String[] taskStrings = new String[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            taskStrings[i] = "\t" + (i + 1) + ". " + lst.get(i);
        }
        return String.join("\n", taskStrings);
    }

    public String taskMsg(Task task, int lstSize) {
        return this.defaultMsg("adding: " + task.toString() + "\nSQUIRTLE now has "
                + lstSize + " thing(s) to do!!");
    }

    public String deleteMsg(Task task) {
        return this.defaultMsg("SQUIRTLE will forget: " + task.toString() + " ~~");
    }

    public String doneMsg(Task task) {
        return this.defaultMsg("SQUIRTLE used water gun on: " + task.toString() + "!\n\tIt is super effective!!");
    }

    public String lineBreak() {
        String lineBreak = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        return lineBreak;
    }

    public String errorMsg(DukeException e) { // error responses
        switch (e.error) {
            case NUMBER: // error in accessing list, no such task exists
                return "SQUIRTLE cannot find task!!";
            case INSUFFICIENT:
                return "SQUIRTLE needs more info!!";
            case COMMAND:
                return "SQUIRTLE doesn't understand!!";
            case DATEFORMAT:
                return "SQUIRTLE doesn't understand this date!!";
            case KEYWORDS:
                return "SQUIRTLE is confused!! Give SQUIRTLE one keyword!!";
            default:
                return "Error in error msg portion of Ui!";
        }
    }

    public String welcomeMsg() {
        return " SQUIRTLE!! I'M HERE TO HELP!! SQUIRTLE!!";
    }

    public String defaultMsg(String message) {
        return " SQUIRTLE!!" + "\t" + message;
    }


}

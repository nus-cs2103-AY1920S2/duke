/**
 * Ui deals with user inputs and outputs
 */

package duke.ui;

import duke.tasks.Task;
import duke.tasklist.TaskList;


import java.util.Scanner;
import java.util.List;

public class Ui {

    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static String showWelcome() {
        return "Hello I'm Duke." + "\n"
                + "What can I do for you?";
    }

    public String showLine() {
        return "____________________________________________________________";
    }

    public String readCommand() {
        String line = sc.nextLine();
        return line;
    }

    public String showLoadingError() {
        return "no data to load, new list is empty.";
    }

    public String showError(String message) {
        return message;
    }

    public String printList(List<Task> list) {
        String output = "";
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Task t = list.get(i);
                output += (i+1) + "." + t.toString() + "\n";
            }
        }
        return output;
    }

    public String printDone(TaskList tasks, int doneTask) {
        String output = "";
        output += "Nice! I've marked this task as done:" + "\n";
        Task t = tasks.getList().get(doneTask);
        output += t.toString();
        return output;
    }

    public String printDelete(TaskList tasks, int deleteTask) {
        String output = "";
        List<Task> list = tasks.getList();
        output += "Noted. I've removed this task:" + "\n";
        Task t = list.get(deleteTask);
        output += t.toString() + "\n";
        output += "Now you have " + (list.size() - 1) + " tasks in the list.";
        return output;
    }

    public String printAddTask(TaskList tasks) {
        String output = "";
        List<Task> list = tasks.getList();
        Task t = list.get(list.size() - 1);
        output += "Got it. I've added this task:" + "\n";
        output += t.toString() + "\n";
        output += "Now you have " + list.size() + " tasks in the list.";
        return output;
    }
}
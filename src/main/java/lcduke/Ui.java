package lcduke;

import java.io.IOException;
import java.util.Scanner;

/** Ths creates an Ui object.
 */
public class Ui {
    private String userInput = "startProcess";
    private Parser newInput;
    private static String command;

    /** This is the constructor to create the Ui Object.
     */
    protected Ui() {
        //this is the constructor of ui
    }

    private String init() {
        return "     Hello! I'm Duke\n" + "     What can I do for you?\n";
    }

    protected String inputProcess(String userInput, Storage storage, TaskList tasks) {
        this.userInput = userInput;
        newInput = new Parser(this.userInput);
        String response;
        String fileSaveErrorMessage = "error in input process";

        if (this.userInput.contains(" ")) {
            command = this.userInput.substring(0, userInput.indexOf(" "));
        } else {
            command = this.userInput;
        }

        if (newInput.getIsProblem()) {
            Parser.isProblem = false;
            response = Parser.errorMessage;

        } else {
            switch (command) {
            case "list":
                response = this.list();
                break;
            case "hi":
                response = this.init();
                break;
            case "done":
                response = this.done(this.userInput);
                fileSaveErrorMessage = this.toSave(storage);
                break;
            case "delete":
                tasks.delete(this.userInput);
                response = this.delete();
                fileSaveErrorMessage = this.toSave(storage);
                break;
            case "find":
                response = tasks.find(this.userInput);
                break;
            case "todo":
                response = tasks.toDo(this.userInput);
                fileSaveErrorMessage = this.toSave(storage);
                break;
            case "deadline":
                response = tasks.deadline(this.userInput);
                fileSaveErrorMessage = this.toSave(storage);
                break;
            case "reminders":
                response = this.reminders();
                break;
            //default is event
            case "bye":
                response = this.bye();
                assert response == "     Bye. Hope to see you again soon!\n"
                        : "ui should have correct bye message\n";
                break;
            default:
                response = tasks.event(this.userInput);
                fileSaveErrorMessage = this.toSave(storage);
            }
        }

        if (fileSaveErrorMessage != "saved successfully!" && fileSaveErrorMessage != "error in input process") {
            response = fileSaveErrorMessage;
        }

        return response;
    }

    private String toSave(Storage storage) {
        String response;
        try {
            storage.save();
            response = "saved successfully!";
        } catch (IOException e) {
            response = e.getMessage();
        }
        return response;
    }

    /** This is to input tasks from user.
     *
     * @return description of task from the user.
     */
    private String input() {
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }

    /** This outputs the response to bye request.
     */
    private String bye() {
        return "     Bye. Hope to see you again soon!\n";
    }

    /** This outputs the response to delete request.
     */
    private String delete() {
        return "     This task is deleted\"\n";
    }

    /** This is to output all tasks from the task list.
     *
     */
    private String list() {
        String response;
        response = "     Here are the tasks in your list:\n";
        int i = 0;
        int j = 1;
        while (i < TaskList.totalTasksCount) {
            response = response + "     " + j + "." + TaskList.totalTasks[i].toString() + "\n";
            i++;
            j++;
        }
        return response;
    }

    /** This is to mark a task is done.
     *
     */
    private String done(String userInput) {
        String response;
        int taskNo = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));
        response = "     Nice! I've marked this task as done: \n";
        TaskList.totalTasks[taskNo - 1].markAsDone(); // to mark as done; -1 as since count in totalTasks starts from 0
        response = response + "       " + TaskList.totalTasks[taskNo - 1].getStatusIcon() + " "
                + TaskList.totalTasks[taskNo - 1].getDescription() + "\n";
        return response;
    }

    private String reminders() {
        String response;
        response = "     Here are the reminders of your tasks:\n";
        int i = 0;
        int j = 1;
        while (i < TaskList.totalTasksCount) {
            if (!TaskList.totalTasks[i].isDone) {
                response = response + "     " + j + "." + TaskList.totalTasks[i].toString() + "\n";
                j++;
            }
            i++;
        }
        return response;
    }
}

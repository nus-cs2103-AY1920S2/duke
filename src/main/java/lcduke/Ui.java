package lcduke;

import java.io.IOException;
import java.util.Scanner;

/** Ths creates an Ui object.
 */

public class Ui {
    String userInput = "startProcess";
    Parser newInput;

    /** This is the constructor to create the Ui Object.
     */
    public Ui() {
        //this is the constructor of ui
    }

    public String init() {
        return "     Hello! I'm Duke\n" + "     What can I do for you?\n";
    }

    public String inputProcess(String userInput, Storage storage, TaskList tasks) {
        this.userInput = userInput;
        newInput = new Parser(this.userInput);
        String response;
        if(newInput.getIsProblem()) {
            Parser.isProblem = false;
            response = Parser.errorMessage;

        } else {
            if (this.userInput.equals("list")) {
                response = this.list();
            } else if (this.userInput.contains("hi")) {
                response = this.init();
            } else if (this.userInput.contains("done")) {
                response = this.done(this.userInput);
                try {
                    storage.save();
                } catch (IOException e) {
                    response = "Cannot write file";
                }
            } else if (this.userInput.contains("delete")) {
                tasks.delete(this.userInput);
                response = "This task is deleted";
                try {
                    storage.save();
                } catch (IOException e) {
                    response = "Cannot write file";
                }
            }else if (this.userInput.contains("find")){
                response = tasks.find(this.userInput);
            } else if (this.userInput.contains("todo")) {
                response = tasks.toDo(this.userInput);
                //TaskList.totalTasks[TaskList.totalTasksCount -1].printInit();
                try {
                    storage.save();
                } catch (IOException e) {
                    response = "Cannot write file";
                }
            } else if (this.userInput.contains("deadline")) {
                response = tasks.deadline(this.userInput);
                //TaskList.totalTasks[TaskList.totalTasksCount - 1].printInit();
                try {
                    storage.save();
                } catch (IOException e) {
                    response = "Cannot write file";
                }
            } else if (this.userInput.contains("reminders")) {
                response = this.reminders();
            } else {
                response = tasks.event(this.userInput);
                try {
                    storage.save();
                } catch (IOException e) {
                    response = "Cannot write file";
                }
            }
        }
        return response;
    }

    /** This is to input tasks from user.
     *
     * @return description of task from the user.
     */
    public String input(){
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }

    /** This outputs the response to bye request.
     */
    public String bye(){
        return "     Bye. Hope to see you again soon!\n";
    }

    /** This is to output all tasks from the task list.
     *
     */
    private String list(){
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
    private String done(String userInput){
        String response;
        int taskNo = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));
        response = "     Nice! I've marked this task as done: \n";
        TaskList.totalTasks[taskNo - 1].markAsDone(); // to mark as done; -1 as since count in totalTasks starts from 0
        response = response + "       " + TaskList.totalTasks[taskNo - 1].getStatusIcon() + " "
                + TaskList.totalTasks[taskNo - 1].getDescription() + "\n";
        return response;
    }

    private String reminders(){
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

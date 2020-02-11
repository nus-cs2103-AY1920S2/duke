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
    public Ui() { }

    public String init() {
        String response = "    ____________________________________________________________"
                + "     Hello! I'm Duke"
                + "     What can I do for you?"
                + "    ____________________________________________________________\n";
        return response;
    }

    public String inputProcess(String userInput, Storage storage, TaskList tasks) {
        String response;
        this.userInput = userInput;
        newInput = new Parser(this.userInput);
        if(newInput.getIsProblem()) {
            Parser.isProblem = false;
            response = "there is Problem with user's Input";

        } else {
            if (this.userInput.equals("list")) {
                response = this.list();
            } else if (this.userInput.contains("done")) {
                response = this.done(this.userInput);
                try {
                    storage.save();
                } catch (IOException e) {
                    System.out.println("Cannot write file");
                }
            } else if (this.userInput.contains("delete")) {
                tasks.delete(this.userInput);
                response = "This task is deleted";
                try {
                    storage.save();
                } catch (IOException e) {
                    System.out.println("Cannot write file");
                }
            }else if (this.userInput.contains("find")){
                response = tasks.find(this.userInput);
            } else if (this.userInput.contains("todo")) {
                response = tasks.toDo(this.userInput);
                //TaskList.totalTasks[TaskList.totalTasksCount -1].printInit();
                try {
                    storage.save();
                } catch (IOException e) {
                    System.out.println("Cannot write file");
                }
            } else if (this.userInput.contains("deadline")) {
                response = tasks.deadline(this.userInput);
                //TaskList.totalTasks[TaskList.totalTasksCount - 1].printInit();
                try {
                    storage.save();
                } catch (IOException e) {
                    System.out.println("Cannot write file");
                }
            } else {
                response = tasks.event(this.userInput);

                try {
                    storage.save();
                } catch (IOException e) {
                    System.out.println("Cannot write file");
                }
            }
        }
        return response;
    }

    public void showLoadingError(){

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
        String response = "    ____________________________________________________________\n"
        + "     Bye. Hope to see you again soon!\n"
        + "    ____________________________________________________________\n";
        return response;
    }

    /** This is to output all tasks from the task list.
     *
     */
    public String list(){
        String response;
        response = "    ____________________________________________________________\n"
                +"     Here are the tasks in your list:\n";
        int i = 0;
        int j = 1;
        while (i < TaskList.totalTasksCount) {
            response = response + "     " + j + "." + TaskList.totalTasks[i].toString() + "\n";
            i++;
            j++;
        }
        response = response + "    ____________________________________________________________\n";
        return response;
    }


    /** This is to mark a task is done.
     *
     */
    public String done(String userInput){
        String response;
        int taskNo = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));
        response = "    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done: \n";
        TaskList.totalTasks[taskNo - 1].markAsDone(); // to mark as done; -1 as since count in totalTasks starts from 0
        response = response + "       " + TaskList.totalTasks[taskNo - 1].getStatusIcon() + " "
                + TaskList.totalTasks[taskNo - 1].getDescription() + "\n";
        response = response + "    ____________________________________________________________\n";
        return response;
    }
}

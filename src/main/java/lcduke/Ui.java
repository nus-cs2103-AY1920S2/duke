package lcduke;

import java.util.Scanner;

/** Ths creates an Ui object.
 */

public class Ui {

    /** This is the constructor to create the Ui Object.
     */
    public Ui() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
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
    public static void bye(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /** This is to output all tasks from the task list.
     *
     */
    public static void list(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        int i = 0;
        int j = 1;
        while (i < TaskList.totalTasksCount) {
            System.out.println("     " + j + "." + TaskList.totalTasks[i].toString());
            i++;
            j++;
        }
        System.out.println("    ____________________________________________________________\n");
    }


    /** This is to mark a task is done.
     *
     */
    public static void done(String userInput){
        int taskNo = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        TaskList.totalTasks[taskNo - 1].markAsDone(); // to mark as done; -1 as since count in totalTasks starts from 0
        System.out.println("       " + TaskList.totalTasks[taskNo - 1].getStatusIcon() + " " + TaskList.totalTasks[taskNo - 1].getDescription());
        System.out.println("    ____________________________________________________________");
    }
}

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
        String inputData = myObj.nextLine();
        return inputData;
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
     * @param tasks the task list.
     */
    public static void list(TaskList tasks){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        int i = 0;
        int j = 1;
        while (i < tasks.totalTasksCount) {
            System.out.println("     " + j + "." + tasks.totalTasks[i].toString());
            i++;
            j++;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /** This is to mark a task is done.
     *
     * @param tasks the task list.
     */
    public static void done(TaskList tasks){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        int i = 0;
        while (i < tasks.totalTasksCount) {
            if (tasks.totalTasks[i].getDone()) {
                System.out.println("       " + tasks.totalTasks[i].getStatusIcon() + " " + tasks.totalTasks[i].getDescription());
            }
            i++;
        }
        System.out.println("    ____________________________________________________________");
    }
}

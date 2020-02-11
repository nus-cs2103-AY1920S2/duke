package duke;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


public class TaskList {


    /***
     * print the list of current tasks
     */


    public static String printList() {

        String output= "";
        if (Duke.commandList.size() > 0) {
            output += "Here are the tasks in your list:\n";
            for (int i = 0; i < Duke.commandList.size(); i++) {
                int a = i + 1;
                output+= a + ". " + Duke.commandList.get(i);
            }


            if (reminder().size() > 0) {
                output += "You have "+reminder().size() + " task(s) that due today: \n";
                for (Task task : reminder()) {
                    output += task;
                }
            }
        } else {
            output = "☹ OOPS!!! I'm sorry, I can't find any task in your list\n";
        }

        return output;
    }

    public static ArrayList<Task> reminder() {
        ArrayList<Task> taskDueToday = new ArrayList<>();
        if (Duke.commandList.size() > 0) {
            for (Task task : Duke.commandList) {
                if (task instanceof Deadline || task instanceof Event) {
                    if (isToday(task.getTime())) {
                        taskDueToday.add(task);
                    }
                }
            }
        }

        return taskDueToday;

    }


    public static boolean isToday(LocalDate date) {
        LocalDate today = LocalDate.now();
        return today.isEqual(date);
    }

    /***
     * Mark a task as done
     * @param task
     */
    public static String doneTask(Task task) {
        task.setDone();
        String output = "Nice! I've marked this task as done: \n";
        return output+task;
    }

    /***
     * Remove a task from the current list
     * @param task
     */

    public static String deleteTask(Task task) {
        Duke.commandList.remove(task);
        String output = "Noted. I've removed this task: \n";
        output += task;
        output += "Now you have "+ Duke.commandList.size() + " tasks in the list.\n";
        return output;
    }

    /***
     * Add a task into the list
     * @param task
     */

    public static String addTask(Task task) {
        Duke.commandList.add(task);
        String output = "Got it. I've added this task: \n";
        output += task;
        output += "Now you have " + Duke.commandList.size() + " tasks in the list.\n";
        return output;
    }
}

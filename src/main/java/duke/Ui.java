package duke;

import duke.task.Task;

import java.util.List;

/**
 * duke.Ui class has specific methods that deal with interactions with the user.
 */
public class Ui {
    /**
     * sets a horizontal line.
     */
    public StringBuilder horizontalLine = new StringBuilder("   ").append("˜".repeat(70)).append("\n");

    /**
     * adds horizontal line around the message and print it out.
     * @param s the message intended to wrap up.
     */
    public String typeSetting(String s) {
        return (horizontalLine + s + "\n" + horizontalLine);
    }

    /**
     * shows greeting messages to the user and reminds the user what tasks are there in the list.
     * @param taskList the list of Task that stores in the hard disk.
     */
    public String greet(TaskList taskList) {
        //welcome message and showing the list to the user
        String s = typeSetting("    Hello, I'm Bob. 👶 👶 👶\n    " +
                "What can I do for you? 😃\n");
        return s + printCurrentList(taskList);
    }

    /**
     * shows exiting message.
     */

    public String bye() {
        return typeSetting("    Are you sure you want to leave me alone? 🥺 (y/n)\n");
    }

    /**
     * shows a specific message when a Task in the list has been marked as done.
     * @param num the index of the Task which has been marked.
     * @param taskDone the Task which we are are going to mark as done.
     */
    public String doneMessage(int num, Task taskDone) {
        return typeSetting("    👍 Nice! I've marked this task as done: " + num
                + "\n" + "      " + taskDone);
    }

    /**
     * shows a specific message when a Task in the list has been deleted from the list.
     * @param num the index of the Task being deleted.
     * @param t the Task being deleted.
     * @param index the number of tasks in the list after deleting.
     */
    public String deleteMessage(int num, Task t, int index) {
        return typeSetting("    👌 Noted. I've removed this task: " + num
                + "\n" + "      " + t + "\n" +
                "    Now you have " + index + " tasks in the list.");
    }

    /**
     * shows a specific message when a Task is newly added into the duke.TaskList.
     * @param t the Task being added.
     * @param index the number of Tasks in the list after adding.
     */
    public String addMessage(Task t, int index) {
        return typeSetting("    🟢 Got it. I've added this task: \n      " +
                t + "\n" +
                "    Now you have " + index + " tasks in the list.");
    }

    public String tagMessage(Task t) {
        return typeSetting("    🟢 Got it. I've tagged this task as you wish: \n      " + t);
    }

    /**
     * gets a list which listing all the tasks recorded.
     * @param taskList the duke.TaskList which contains all the Tasks recorded.
     */
    public String printCurrentList(TaskList taskList) {
        List<Task> list = taskList.getTasks();
        int index = list.size();
        StringBuilder li = new StringBuilder("    📜 Here are the tasks in your list:\n");
        for (int i = 0; i < index; i++) {
            li.append("         ").append(i + 1).append(": ").append(list.get(i)).append("\n");
        }
        return typeSetting(li.toString());
    }

    /**
     * an slightly altered version of gettingList. (exclusively used in findTask method in duke.TaskList class)
     * @param taskList the duke.TaskList we are dealing with.
     */
    public String printMatchingTasks(TaskList taskList) {
        List<Task> list = taskList.getTasks();
        int index = list.size();
        StringBuilder li = new StringBuilder("    📜 Here are the matching tasks in your list:\n");
        for (int i = 0; i < index; i++) {
            li.append("         ").append(i + 1).append(": ").append(list.get(i)).append("\n");
        }
        return typeSetting(li.toString());
    }
}

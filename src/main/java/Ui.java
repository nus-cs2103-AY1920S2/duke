import Task.Task;

import java.util.List;

/**
 * Ui class has specific methods that deal with interactions with the user.
 */
public class Ui {
    /**
     * sets a horizontal line.
     */
    public StringBuilder horizontalLine = new StringBuilder("   ").append("\u02DC".repeat(80)).append("\n");

    /**
     * adds horizontal line around the message and print it out.
     * @param s the message intended to wrap up.
     */
    public void typeSetting(String s) {
        System.out.println(horizontalLine + s + "\n" + horizontalLine);
    }

    public void greet(TaskList taskList) {
        //welcome message and showing the list to the user
        typeSetting("    Hello, I'm Bob. \uD83D\uDC76 \uD83D\uDC76 \uD83D\uDC76\n    " +
                "What can I do for you? \uD83D\uDE03\n");
        gettingList(taskList);
    }

    public void bye() {
        typeSetting("    Are you sure you want to leave me alone? \uD83E\uDD7A (y/n)\n");
    }

    public void doneMessage(int num, List<Task> list) {
        typeSetting("    \uD83D\uDC4D Nice! I've marked this task as done: " + num
                + "\n" + "      " + list.get(num - 1));
    }

    public void deleteMessage(int num, Task t, int index) {
        typeSetting("    \uD83D\uDC4C Noted. I've removed this task: " + num
                + "\n" + "      " + t + "\n" +
                "    Now you have " + index + " tasks in the list.");
    }

    public void addMessage(Task t, int index) {
        typeSetting("    \uD83D\uDFE2 Got it. I've added this task: \n      " +
                t + "\n" +
                "    Now you have " + index + " tasks in the list.");
    }

    //get a list which listing all the tasks recorded
    public void gettingList(TaskList taskList) {
        List<Task> list = taskList.getTasks();
        int index = list.size();
        StringBuilder li = new StringBuilder("    \uD83D\uDCDC Here are the tasks in your list:\n");
        for (int i = 0; i < index; i++) {
            li.append("         ").append(i + 1).append(": ").append(list.get(i)).append("\n");
        }
        typeSetting(li.toString());
    }
}

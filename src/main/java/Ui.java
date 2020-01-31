/**
 * Deals with interactions with the user
 */
public class Ui {
    TaskList taskList = new TaskList();
    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void deleteMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("        Noted. I've removed this task:");
        System.out.print("        ");
        task.taskSummary();
        System.out.println("        Now you have " + Task.totalTasks + " " + (Task.totalTasks == 1? "task" : "tasks") + " in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void addMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("        Got it. I've added this task:");
        System.out.print("            ");
        task.taskSummary();
        System.out.println("        Now you have " + Task.totalTasks + " " + (Task.totalTasks == 1? "task" : "tasks") + " in the list.");
        System.out.println("    ____________________________________________________________");

    }
    public void doneMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("        Nice! I've marked this task as done: ");
        System.out.print("        ");
        task.taskSummary();
        System.out.println("    ____________________________________________________________");
    }

    public void reply(String string) {
        System.out.println("    ____________________________________________________________");
        System.out.println("        " + string);
        System.out.println("    ____________________________________________________________");
    }

    public void printList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("        Here are the tasks in your list:");
        int count = 1;
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            System.out.print("        " + count + ".");
            (taskList.getTask(i)).taskSummary();
            count++;
        }
        System.out.println("    ____________________________________________________________");
    }

    public void showError(String msg) {
        System.out.println(msg);
    }
}

package duke;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ui {
    public static final String LF = "\n";
    private static final String WELCOME_MSG = "Hello! I'm duke.Duke" + LF + "What can I do for you?" + LF;
    private static final String BYE_MSG = "Bye, hope to see you again soon!" + LF;

    private BufferedReader br;

    public Ui() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void printWelcomeMsg() {
        System.out.println(WELCOME_MSG);
    }

    public void printByeMsg() {
        System.out.println(BYE_MSG);
    }

    public String readCmd() throws IOException {
        return br.readLine().trim();
    }

    public void printLine(String line) {
        System.out.println(line);
    }

    public void printLine() {
        System.out.println();
    }

    public void printAddedTask(Task t, TaskList taskList) {
        this.printLine("Got it! I've added this task:" + Ui.LF + "    " + t + Ui.LF
                + "Now, you have " + taskList.getNumTasks() + " item(s) in your list." + Ui.LF);
    }

    public void printTaskList(TaskList taskList) {
        this.printLine("Here are your task(s):");

        int len = taskList.getNumTasks();

        for (int i = 0; i < len; ++i) {
            Task t = taskList.getTask(i);
            this.printLine("    " + (i + 1) + ". " + t);
        }

        this.printLine();
    }

    public void printTaskMarkedDone(Task t) {
        this.printLine("Nice! I've marked this task as done:" + Ui.LF + "    " + t + Ui.LF);
    }

    public void printTaskDeleted(Task t, TaskList taskList) {
        this.printLine("Noted! I've removed this task:" + Ui.LF + "    " + t + Ui.LF
                + "Now, you have " + taskList.getNumTasks() + " item(s) in your list." + Ui.LF);
    }
}

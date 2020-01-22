package duke;

import task.Task;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Duke {
    private Scanner sc;
    private ArrayList<Task> tasks;
    public static final String separator = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may duke serve the master today?\n");
        Duke bot = new Duke();
        bot.start();
    }

    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new ArrayList<>();
    }

    public void start() {
        while (true) {
            String input = sc.nextLine();
            echo(dispatch(input));
            if (input.equals("bye"))
                break;
        }
    }

    private String dispatch(String input) {
        switch (input) {
        case "list":
            StringBuilder sb = new StringBuilder();
            int tasksLength = this.tasks.size();
            for (int i = 0; i < tasksLength - 1; i++) {
                sb.append(String.format("%d. %s\n", i + 1, this.tasks.get(i)));
            }
            sb.append(String.format("%d. %s", tasksLength, this.tasks.get(tasksLength - 1)));
            return sb.toString();
        case "bye":
            return "Bye. Hope to see you again soon!";
        default:
            if (Pattern.matches("^done\\s+\\d$", input)) {
                int doneInd = Integer.parseInt((input.split(" "))[1]) - 1;
                Task currTask = this.tasks.get(doneInd);
                currTask.setDone();
                return String.format("Nice! I've marked this task as done:\n  %s", currTask);
            } else {
                this.tasks.add(new Task(input));
                return String.format("added: %s", input);
            }
        }
    }

    private void echo(String toEcho) {
        System.out.println(Duke.separator);
        PrintWriter printWriter = new PrintWriter(System.out, true);
        printWriter.println(toEcho);
        System.out.println(Duke.separator);
    }

}

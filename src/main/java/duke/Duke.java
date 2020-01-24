package duke;

import task.Task;
import exception.DukeException;
import parser.Parser;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Scanner sc;
    private ArrayList<Task> tasks;
    public static final String separator =
            "____________________________________________________________";

    private final int indent1 = 4;
    private final int indent2 = 5;
    private final int indent3 = 7;

    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new ArrayList<>();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may duke serve the master today?\n");
        Duke bot = new Duke();
        bot.start();
    }

    public static void out(String in, int indent) {
        System.out.println(" ".repeat(indent) + in);
    }

    private int taskSize() {
        return this.tasks.size();
    }

    public void start() {
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println(input);
            Duke.out(Duke.separator, indent1);
            try {
                dispatch(input);
            } catch (DukeException err) {
                Duke.out(err.getMessage(), indent2);
            }
            Duke.out(Duke.separator, indent1);
            System.out.println();
            if (input.equals("bye")) {
                this.sc.close();
                break;
            }
        }
    }

    private void dispatch(String input) throws DukeException {
        switch (input.trim()) {
            case "list":
                int tasksLength = taskSize();
                Duke.out("Here are the tasks in your list:", indent2);
                for (int i = 0; i < tasksLength; i++) {
                    Duke.out(String.format("%d.%s", i + 1, this.tasks.get(i)), indent2);
                }
                return;
            case "bye":
                Duke.out("Bye. Hope to see you again soon!", indent2);
                return;
            default:
                // as long as done/delete inside
                if (Parser.isDoneDelete(input)) {
                    if (this.tasks.isEmpty()) {
                        throw new DukeException("Task list is empty!");
                    }
                    String[] splitInput = input.split(" ");
                    int taskIndex = Integer.parseInt(splitInput[splitInput.length - 1]) - 1;
                    if (taskIndex >= taskSize()) {
                        Duke.out(String.format(
                                "Please choose an index that is between 1 and %d (inclusive)",
                                taskSize()), indent2);
                        return;
                    }
                    if (input.contains("done")) {
                        Task currTask = this.tasks.get(taskIndex);
                        currTask.setDone();
                        Duke.out("Nice! I've marked this task as done:", indent2);
                        Duke.out(currTask.toString(), indent3);
                        return;
                    } else {
                        Task removedTask = this.tasks.remove(taskIndex);
                        Duke.out("Noted. I've removed this task:", indent2);
                        Duke.out(removedTask.toString(), indent3);
                        Duke.out(String.format("Now you have %d tasks in the list.", taskSize()),
                                indent2);
                        return;
                    }
                } else {
                    Task newTask = Task.newTask(input);
                    this.tasks.add(newTask);
                    Duke.out("Got it. I've added this task: ", indent2);
                    Duke.out(newTask.toString(), indent3);
                    Duke.out(String.format("Now you have %d %s in the list.", taskSize(),
                            taskSize() > 1 ? "tasks" : "task"), indent2);
                    return;
                }
        }
    }
}

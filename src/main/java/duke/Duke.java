package duke;

import task.Task;
import storage.Storage;
import exception.DukeException;
import parser.Parser;
import java.util.Scanner;
import java.util.ArrayList;

import java.nio.file.Paths;

public class Duke {
    private Scanner sc;
    private TaskList taskList;
    public static final String separator =
            "____________________________________________________________";
    private static Storage storage = new Storage(Paths.get("storage", "file.txt"));

    private final int indent1 = 4;
    private final int indent2 = 5;
    private final int indent3 = 7;

    public Duke() {
        this.sc = new Scanner(System.in);
        this.taskList = new TaskList(Duke.storage.getTasksFromStorage());
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
                String[] output = this.taskList.allTasks();
                Duke.out("Here are the tasks in your list:", indent2);
                for (String s : output)
                    Duke.out(s, indent2);
                return;
            case "bye":
                Duke.out("Bye. Hope to see you again soon!", indent2);
                return;
            default:
                // as long as done/delete inside
                if (Parser.isDoneDelete(input)) {
                    if (this.taskList.isEmpty()) {
                        throw new DukeException("Task list is empty!");
                    }
                    // Shift to parser to check for length
                    String[] splitInput = input.split(" ");
                    int taskIndex = Integer.parseInt(splitInput[splitInput.length - 1]) - 1;
                    if (taskIndex >= this.taskList.size()) {
                        throw new DukeException(String.format(
                                "Please choose an index that is between 1 and %d (inclusive)",
                                this.taskList.size()));
                    }
                    if (input.contains("done")) {
                        this.taskList.markDone(taskIndex);
                        Duke.out("Nice! I've marked this task as done:", indent2);
                        Duke.out(this.taskList.getTask(taskIndex), indent3);
                    } else {
                        Task removedTask = this.taskList.popTask(taskIndex);
                        Duke.out("Noted. I've removed this task:", indent2);
                        Duke.out(removedTask.toString(), indent3);
                        Duke.out(String.format("Now you have %d tasks in the list.",
                                this.taskList.size()), indent2);
                    }
                } else {
                    Task newTask = this.taskList.addTask(input);
                    Duke.out("Got it. I've added this task: ", indent2);
                    Duke.out(newTask.toString(), indent3);
                    Duke.out(String.format("Now you have %d %s in the list.", this.taskList.size(),
                            this.taskList.size() > 1 ? "tasks" : "task"), indent2);
                }
                Duke.storage.update(this.taskList.getAllTask());
        }
    }
}

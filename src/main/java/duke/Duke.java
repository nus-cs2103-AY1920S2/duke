package duke;

import duke.Ui.Ui;
import duke.command.Command;
import duke.command.PriorityCommand;
import duke.parser.Parser;
import duke.storage.TaskStorage;
import duke.task.Task;
import exception.IllegalTextException;

import java.io.FileNotFoundException;

public class Duke {
    // attributes
    private TaskStorage taskStorage;
    private Ui ui;

    public Duke() {
        try {
            this.ui = new Ui();
            this.taskStorage = new TaskStorage("./data/Storage.txt");

            assert this.ui != null : "ui instance in duke is null";
            assert this.taskStorage != null : "taskStorage instance in duke is null";

        } catch (FileNotFoundException e) {
            System.out.println("./data/Storage.txt");
            System.out.println("File not found");
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.ui, this.taskStorage);
        } catch (Exception e) {
            return this.ui.showErrorMessage(e);
        }

    }

    // Carry out Add, List, Done commands if entered by user
    // Terminates when user gives exit signal
    /*
    private void runDuke() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String nextInput = scanner.nextLine();
            // Validation check
            if (nextInput.equals("bye")) {
                stopDuke();
                break;
            }
            // Handle List command if any
            if (nextInput.equals("list")) {
                handleCommandList();
                continue;
            }

            // Check and Handle Done command
            // Possible source of error
            String[] commandArgs = nextInput.split(" ");

            if (commandArgs[0].equals("done")) {
                // duke.task.Todo: Add exception handling
                handleCommandDone(Integer.parseInt(commandArgs[1]));
                continue;
            }

            if (commandArgs[0].equals("delete")) {
                // duke.task.Todo: Add exception handling
                handleCommandDelete(Integer.parseInt(commandArgs[1]));
                continue;
            }

            // Handle Add Command
            Task t = null;
            try {
                t = TaskDispatch.dispatchTaskFromInput(commandArgs);
            } catch (IllegalArgumentException e1){
                System.out.println("    " + e1.getMessage());
                continue;
            } catch (IllegalCommandException e2) {
                System.out.println("    " + e2.getMessage());
                continue;
            }

            handleCommandAdd(t, nextInput);
        }
    }*/

    // Print a closing message before stopping duke.Duke
    private static String stopDuke() {
        String closingMessage = "Bye. Hope to see you again soon!";
        return "    " + closingMessage;
    }

    // duke.task.Todo: abstract away the following logic from main via new interface for command handlers

    private void handleCommandList() {
        System.out.println("    Here are the tasks in your list:");
        int counter = 1;
        for (Task task : this.taskStorage.getTaskList()) {
            System.out.println("    " + counter + ". " + task);
            counter++;
        }
    }

    private void handleCommandAdd(Task newTask) {
        this.taskStorage.addToTaskList(newTask);
        System.out.println("    Got it. I've added this task:\n"
                + "      " + newTask);
        System.out.println("\n    Now you have " + this.taskStorage.getTaskList().size() + " tasks in the list.");
    }

    private void handleCommandDone(int taskNumber) {
        this.taskStorage.markTaskAsDone(taskNumber);
        System.out.println("Nice! I've marked this task as done:\n"
                + "    " + this.taskStorage.getTaskList().get(taskNumber - 1));
    }

    private void handleCommandDelete(int taskNumber) {
        System.out.println("    Noted. I've removed this task:\n"
                + "    " + this.taskStorage.getTaskList().get(taskNumber - 1));
        this.taskStorage.deleteFromTaskList(taskNumber);
        System.out.println("    Now you have " + this.taskStorage.getTaskList().size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        //new Duke().runDuke();
    }
}

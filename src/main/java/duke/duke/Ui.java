package duke;

import java.util.Scanner;

/**
 * Displays textual cues to user.
 */
public class Ui {
    private String output;

    Ui() {
        this.output = "";
    }

    /**
     * Read user input.
     * @return String of user input
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    /**
     * Formats interface with lines.
     * @return String of lines
     */
    public String showLine() {
        output = "_______";
        return output;
    }

    /**
     * Formats interface with blanklines.
     * @return newline
     */
    public String blankLine() {
        output = "/n";
        return output;
    }

    /**
     * Display loading error to user.
     * @return String of loading error
     */
    public String showLoadingError() {
        output = "Unable to load";
        return output;
    }

    /**
     * Display error specified to user.
     * @param err from Exception thrown
     * @return String of error specified
     */
    public String showError(String err) {
        return err;
    }

    /**
     * Display greeting to user.
     * @return String of greeting
     */
    public String greet() {
        //System.out.println("Hi2");
        output = "Hey! I'm Duke! \n" + "What can I help you with?";
        //System.out.println("Hi2");
        return output;
    }

    /**
     * Display add command.
     * @param task Task from Duke
     * @param tasks TaskList from Duke
     * @return String for add command
     */
    public String taskAdd(Task task, TasksNum tasks) {
        //System.out.println("Hi3");
        output =  "Got it. I've added this task: \n" + task + "\n" + "Now you have "
                + tasks.getNum() + " tasks in the list.\n";
        return output;
    }

    /**
     * Display done command.
     * @param task Task from Duke
     * @param tasks TaskList from Duke
     * @return String for done command
     */
    public String taskDone(Task task, TasksNum tasks) {
        output = "Nice! I've marked this task as done: \n" + task + "\n" + "Now you have "
                + tasks.getNum() + " tasks in the list.\n";
        return output;
    }

    /**
     * Display delete command.
     * @param task Task from Duke
     * @param tasks TaskList from Duke
     * @return String for delete command
     */
    public String taskDelete(Task task, TasksNum tasks) {
        output = "Noted, I've removed this task: \n" + task + "\n" + "Now you have "
                + tasks.getNum() + " tasks in the list.\n";
        return output;
    }

    /**
     * Display farewell to user.
     * @return String for bye command
     */
    public String saybye() {
        output = "Bye! Hope to see you again soon! \n";
        return output;
    }

    /**
     * Display invalid command error message to user.
     * @return String for invalid command message
     */
    public String errormsg() {
        output = "Oops but that is not a valid command :-(";
        return output;
    }

    public String findTasks(TaskList tasks) {
        System.out.print(showLine());
        System.out.print("Here are the matching tasks in your list:");
        output = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            output += ((i + 1) + ". " + tasks.getTask(i) + '\n');
        }
        return output;
    }

}
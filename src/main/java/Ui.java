import java.util.Scanner;

/**
 * Ui class handles the bot's response to the user's input.
 */
public class Ui {
    /**
     * Function called when there is error loading the file.
     */
    public void showLoadingError() {
        System.out.println("There is an error in loading the file.");
    }

    /**
     * Displays welcome message at the start of the program.
     */
    public String welcome() {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/
        String greetings = "Konnichiwassup! Got things I can do for ya?";
        return greetings;
    }

    /**
     * Reads the command that the user input.
     * @return Command that the user typed.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints marking task as done.
     * @param size Size of tasks.
     * @param taskNum Task number.
     */
    public String printMarkDone(int size, int taskNum, String task) {
        String msg = "Got it. I've marked task #" + taskNum + ": 「" + task + "」 as done." + "\n"
                + "You currently have " + size + " task(s) in your list.";
        return msg;
    }

    /**
     * Prints the task that is being added into tasks list.
     * @param size Size of tasks.
     * @param task Specific task.
     */
    public String printTaskAdded(int size, String task) {
        String msg = "Got it. I've added 「" + task + "」 to your task(s)." + "\n"
                + "You currently have " + size + " task(s) in your list.";
        return msg;
    }

    /**
     * Prints the task that is being removed from the tasks list.
     * @param size Size of tasks.
     * @param taskNum Task number.
     * @param task Specific task.
     */
    public String printTaskRemoved(int size, int taskNum, String task) {
        String msg = "Got it. I've removed task #" + taskNum + ": 「" + task + "」." + "\n"
                + "You currently have " + size + " task(s) in your list.";
        return msg;
    }

    /**
     * Prints the task according to the keyword.
     * @param list List of matching tasks.
     * @param keyword Keyword entered by the user.
     */
    public String printMatchingTask(String list, String keyword) {
        if (list.equals("")) {
            return "We have found nothing related to your search.";
        } else {
            String msg = "Here are some matching tasks in your list: 「" +
                    keyword + "」" + "\n" + list;
            return msg;
        }
    }

    /**
     * Prints goodbye message to the user.
     */
    public String printGoodbye() {
        return "さらbye. Hope to see you again soon! ( ﾟ▽ﾟ)/";
    }

}
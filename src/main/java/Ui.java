import java.util.Scanner;

/**
 * Ui class handles the bot's response to the user's input.
 */
public class Ui {
    public Ui() {

    }

    /**
     * Function called when there is error loading the file.
     */
    public void showLoadingError() {
        System.out.println("There is an error in loading the file.");
    }

    /**
     * Displays welcome message at the start of the program.
     */
    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Greetings from\n" + logo);
        System.out.println("Is that anything that I can do for you?");
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
     * @param t List of tasks.
     * @param num Task number.
     */
    public void printMarkDone(TaskList t, int num) {
        System.out.println("Got it. I've marked task #" + num + ": 「" + t.getDukeList().get(num - 1) + "」 as done.");
        System.out.println("You currently have " + t.getSize() + " task(s) in your list.");
    }

    /**
     * Prints the task that is being added into tasks list.
     * @param tList List of tasks.
     * @param t Specific task.
     */
    public void printTaskAdded(TaskList tList, Task t) {
        System.out.println("Got it. I've added 「" + t + "」 to your task(s).");
        System.out.println("You currently have " + tList.getSize() + " task(s) in your list.");

    }

    /**
     * Prints the task that is being removed from the tasks list.
     * @param t List of tasks.
     * @param num Number in the list to be removed.
     */
    public void printTaskRemoved(TaskList t, int num) {
        System.out.println("Got it. I've removed task #" + num + ": 「" + t.getDukeList().get(num - 1) + "」.");
        System.out.println("You currently have " + (t.getSize()-1) + " task(s) in your list.");
    }

    /**
     * Prints the task according to the keyword.
     * @param list List of matching tasks.
     * @param keyword Keyword entered by the user.
     */
    public void printMatchingTask(String list, String keyword) {
        if (list.equals("")) {
            System.out.println("We have found nothing related to your search.");
        } else {
            System.out.println("Here are some matching tasks in your list: 「" + keyword + "」");
            System.out.println(list);
        }
    }

    /**
     * Prints goodbye message to the user.
     */
    public void goodbye() {
        System.out.println("さらbye. Hope to see you again soon! ( ﾟ▽ﾟ)/");
    }

}
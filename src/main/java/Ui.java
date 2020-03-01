/**
 * CS2103 Individual Project.
 * The Ui class encapsulates all the methods dealing with user interactions. It takes in user input
 * and determines the corresponding action. It contains a boolean to determine if the user wants to end
 * the chat bot operations.
 * @author Chiang Shun De
 */
public class Ui {

    private Storage storage;
    public TaskList tasks;
    private boolean isFinished;
    private String effect;

    /**
     * Constructor method to create an instance of Ui.
     */
    public Ui() {
        this.isFinished = false;
        this.effect = "************************************************************";
    }

    /**
     * Method to print out the greeting messages to welcome the user to the chat bot.
     * @param effect The design of the line.
     */
    public void greet(String effect) {
        String logo = " ____        _        \n"
                   + "|  _ \\ _   _| | _____ \n"
                   + "| | | | | | | |/ / _ \\\n"
                   + "| |_| | |_| |   <  __/\n"
                   + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(effect +"\nHi! I'm\n"+logo);
        System.out.println("What can I do for you?\n"+effect);
    }

    /**
     * This method takes in the user input and perform the required actions based on user input.
     * @param userInput String containing user input
     * @param tasks The TaskList containing the ArrayList of tasks.
     * @return A new TaskList from the actions performed based on user input.
     */
    public TaskList handleInput(String userInput, TaskList tasks) {
        Parser parser = new Parser(userInput);
        String command = parser.getCommand();
        String description = parser.getDescription();
        if (command.equals("list")) {
            System.out.println(this.effect);
            if (tasks.getSize() == 0) {
                System.out.println("😊 You have 0 tasks in your list! 😊");
            } else {
                System.out.println("Here are the tasks in your list:");
                tasks.printlist();
            }
            System.out.println(this.effect);
        } else if (command.equals("done") && ((Integer.parseInt(description)) <= tasks.getSize())) {
            System.out.println(this.effect + "\nNice! I've marked this task as done:");
            tasks = tasks.markDone(Integer.parseInt(description) - 1);
            System.out.println(tasks.get(Integer.parseInt(description) - 1));
            System.out.println(this.effect);
        } else if (command.equals("delete") && ((Integer.parseInt(description)) <= tasks.getSize())) {
            System.out.println(this.effect + "\nNoted. I've removed this task:");
            System.out.println(tasks.get(Integer.parseInt(description) - 1));
            tasks = tasks.deleteTask(Integer.parseInt(description) - 1);
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
            System.out.println(this.effect);
        } else if (command.equals("massdelete")) {
            if (description.equals("all")){
                System.out.println(this.effect + "\nNoted. I've removed all tasks!");
            } else {
                System.out.println(this.effect + "\nNoted. I've removed these tasks with the keyword: " + description);
            }
            tasks = tasks.massDelete(description);
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
            System.out.println(this.effect);
        } else if (command.equals("massdone")) {
            if (description.equals("all")){
                System.out.println(this.effect + "\nNoted. I've marked all tasks as done!");
            } else {
                System.out.println(this.effect + "\nNoted. I've marked done these tasks with the keyword: " + description);
            }
            tasks = tasks.massDone(description);
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
            System.out.println(this.effect);
        } else if (command.equals("bye")) {
            this.isFinished = true;
        } else if (((command.equals("todo")) || (command.equals("deadline")) || (command.equals("event"))) && (!description.isBlank())) {
            tasks = tasks.addTask(command, description);
            System.out.println(this.effect + "\nGot it! I've added this task:\n" + tasks.get(tasks.getSize() - 1));
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
            System.out.println(this.effect);
        } else if (command.equals("find")) {
            System.out.println(this.effect + "\nHere are the matching tasks in your list:");
            tasks.searchTask(description);
            System.out.println(this.effect);
        }
        return tasks;
    }

    /**
     * To bid farewell to the user before the chat bot closes.
     */
    public void farewell() {
        System.out.println("\n" + "Bye! :) Hope to see you again soon!\n");
    }

    public void showLoadingError(DukeException e) {
        e.printStackTrace();
        System.out.println("☹ OOPS!!! LOADING ERROR...");
    }

    /**
     * Checks if the user has keyed in "bye" to end chat bot operations.
     * @return true if there are still actions to be inputted, false if the user has keyed in "bye".
     */
    public boolean checkFinished() {
        return this.isFinished;
    }
}

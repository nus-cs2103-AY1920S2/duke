public class Ui {

    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    String welcomeMessage = "Hello from\n" + logo + "\n I am at your service\n";
    //    System.out.println("Hello from\n" + logo + "\n I am at your service\n");
    String botReplyLine = "----------------------------------------------";

    /**
     * Welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + logo + "\n I am at your service\n" + botReplyLine);
    }

    /**
     * Goodbye message.
     */
    public void goodbyeMessage() {
        System.out.println(botReplyLine + "\n Duke: I'll say goodnight now \n" + botReplyLine);
    }

    /**
     * Shows ---------------------.
     */
    public void showLine() {
        System.out.println(botReplyLine);
    }

    /**
     * Message when TaskList is empty.
     */
    public void emptyList() {
        System.out.println("You currently do not have anything in your list");
        showLine();
    }

    /**
     * Message when Task is done.
     */
    public void doneTask() {
        showLine();
        System.out.println("Consider it done.");

    }

    /**
     * Message when invalid task number specified.
     */
    public void invalidTask() {
        showLine();
        System.out.println("Invalid task number");

    }

    /**
     * Message when task is deleted.
     */
    public void deletedTask() {
        showLine();
        System.out.println("Consider it deleted.");
    }

    /**
     * Message when task number is not specified.
     *
     * @return Message of missing task number.
     */
    public String missingTaskNumber() {
        return botReplyLine + "\n Missing task number \n" + botReplyLine;
    }

    /**
     * Message when command is incomplete.
     *
     * @param str todo, deadline or event.
     * @return Message of incomplete command.
     */
    public String incompleteCommand(String str) {
        return botReplyLine + "\n" + str + " not complete \n" + botReplyLine;
    }

    /**
     * Message when command is invalid.
     *
     * @return Message of invalid command.
     */
    public String invalidCommand() {
        return botReplyLine + " \n Sorry I do not understand. \n" + botReplyLine;
    }

    /**
     * Message when task is added.
     */
    public void addedCommand() {
        showLine();
        System.out.println("Duke: added your command. \n");
    }

    /**
     * Message to show number of tasks in TaskList
     *
     * @param listSize number of tasks in TaskList
     */
    public void taskInList(int listSize) {
        System.out.println("You have " + listSize + " task(s) in your list.");
        showLine();
    }


}

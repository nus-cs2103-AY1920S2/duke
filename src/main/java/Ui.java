public class Ui {

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static String welcomeMessage = "Hello from\n" + logo + "\n I am at your service\n";
    //    System.out.println("Hello from\n" + logo + "\n I am at your service\n");
    static String botReplyLine = "----------------------------------------------";

    /**
     * Welcome message.
     */
    public static void showWelcome() {
        System.out.println("Hello from\n" + logo + "\n I am at your service\n" + botReplyLine);
    }

    /**
     * Goodbye message.
     */
    public static void goodbyeMessage() {
        System.out.println(botReplyLine + "\n Duke: I'll say goodnight now \n" + botReplyLine);
    }

    /**
     * Shows ---------------------.
     */
    public static void showLine() {
        System.out.println(botReplyLine);
    }

    /**
     * Message when TaskList is empty.
     */
    public static void emptyList() {
        showLine();
        System.out.println("You currently do not have anything in your list");
        showLine();
    }

    /**
     * Done task command.
     *
     * @param taskList   Current TaskList
     * @param taskNumber Specified task number in TaskList
     */
    public static void doneTask(TaskList taskList, int taskNumber) {
        showLine();
        System.out.println("Consider it done.");
        taskList.getTask(taskNumber).doneTask();
        System.out.println(taskList.getTask(taskNumber).toString());
        showLine();
    }

    /**
     * Message when invalid task number specified.
     */
    public static void invalidTask() {
        showLine();
        System.out.println("Invalid task number.");
        showLine();

    }

    /**
     * Deletes task command.
     *
     * @param taskList   Current TaskList.
     * @param taskNumber Task number to be deleted from Task List.
     */
    public void deletedTask(TaskList taskList, int taskNumber) {
        showLine();
        System.out.println("Consider it deleted.");
        System.out.println(taskList.getTask(taskNumber).toString());
        taskList.deleteTask(taskNumber);
        taskInList(taskList.getTaskListSize());
    }

    /**
     * Message when task number is not specified.
     *
     * @return Message of missing task number.
     */
    public static String missingTaskNumber() {
        return botReplyLine + "\n Missing task number \n" + botReplyLine;
    }

    /**
     * Message when command is incomplete.
     *
     * @param str todo, deadline or event.
     * @return Message of incomplete command.
     */
    public static String incompleteCommand(String str) {
        return botReplyLine + "\n" + str + " not complete \n" + botReplyLine;
    }

    /**
     * Message when command is invalid.
     *
     * @return Message of invalid command.
     */
    public static String invalidCommand() {
        return botReplyLine + " \n Sorry I do not understand. \n" + botReplyLine;
    }

    /**
     * Message when task is added.
     */
    public static void addedCommand() {
        showLine();
        System.out.println("Duke: added your command. \n");

    }

    /**
     * Message to show number of tasks in TaskList.
     *
     * @param listSize number of tasks in TaskList
     */
    public static void taskInList(int listSize) {
        System.out.println("You have " + listSize + " task(s) in your list.");
        showLine();
    }

    /**
     * Message to show when list command activated.
     *
     * @param taskList TaskList provided.
     * @param sb       StringBuilder object.
     */
    public static void listCommand(TaskList taskList, StringBuilder sb) {
        showLine();
        for (int i = 0; i < taskList.getTaskListSize(); i++) {
            System.out.println(i + 1 + ". " + taskList.getTask(i).toString());
            sb.append(taskList.getTask(i).saveToList() + "\n");
        }
        showLine();
    }


}

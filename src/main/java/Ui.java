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
     *
     * @return Message of welcome.
     */
    public static String showWelcome() {
        System.out.println("Hello from\n" + logo + "\n I am at your service\n" + botReplyLine);
        return ("Hello! I am at your service");
    }

    /**
     * Goodbye message.
     *
     * @return Message of goodbye.
     */
    public static String goodbyeMessage() {
        System.out.println(botReplyLine + "\n Duke: I'll say goodnight now \n" + botReplyLine);
        return ("I'll say goodnight now");
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
    public static String emptyList() {
        showLine();
        System.out.println("You currently do not have anything in your list");
        showLine();
        return ("You currently do not have anything in your list");
    }

    /**
     * Done task command.
     *
     * @param taskList   Current TaskList
     * @param taskNumber Specified task number in TaskList
     */
    public static String doneTask(TaskList taskList, int taskNumber) {
        showLine();
        System.out.println("Consider it done.");
        taskList.getTask(taskNumber).doneTask();
        System.out.println(taskList.getTask(taskNumber).toString());
        showLine();
        return ("Consider it done.\n" + taskList.getTask(taskNumber).toString());
    }

    /**
     * Message when invalid task number specified.
     */
    public static String invalidTask() {
        showLine();
        System.out.println("Invalid task number.");
        showLine();
        return ("Invalid task number");
    }

    /**
     * Deletes task command.
     *
     * @param taskList   Current TaskList.
     * @param taskNumber Task number to be deleted from Task List.
     */
    public static String deletedTask(TaskList taskList, int taskNumber) {
        showLine();
        System.out.println("Consider it deleted.");
        System.out.println(taskList.getTask(taskNumber).toString());
        String taskToBeDeleted = taskList.getTask(taskNumber).toString();
        taskList.deleteTask(taskNumber);
        String tasksInList = taskInList(taskList.getTaskListSize());
        return ("Consider it deleted. \n" + taskToBeDeleted + "\n" + tasksInList);
    }

    /**
     * Message when command is incomplete.
     *
     * @param str todo, deadline or event.
     * @return Message of incomplete command.
     */
    public static String incompleteCommand(String str) {
        if (str.isEmpty()) {
            return "Missing task number.";
        } else {
            return str + " not complete";
        }
    }


    /**
     * Message when command is invalid.
     *
     * @return Message of invalid command.
     */
    public static String invalidCommand() {
        return "Sorry I do not understand.";
    }

    /**
     * Message when task is added.
     */
    public static String addedCommand(int listSize) {
        showLine();
        System.out.println("Duke: added your command. \n");
        return "Ooooh yeah! Can do! " + taskInList(listSize);
    }

    /**
     * Message to show number of tasks in TaskList.
     *
     * @param listSize number of tasks in TaskList
     * @return Message of number of tasks in list.
     */
    public static String taskInList(int listSize) {
        System.out.println("You have " + listSize + " task(s) in your list.");
        showLine();
        return "You have " + listSize + " task(s) in your list.";
    }

    /**
     * Message to show when list command activated.
     *
     * @param taskList TaskList provided.
     * @param sb       StringBuilder object.
     * @return Message of list command.
     */
    public static String listCommand(TaskList taskList, StringBuilder sb) {
        StringBuilder botReplySb = new StringBuilder();
        showLine();
        for (int i = 0; i < taskList.getTaskListSize(); i++) {
            botReplySb.append(i + 1 + ". " + taskList.getTask(i).toString() + "\n");
            System.out.println(i + 1 + ". " + taskList.getTask(i).toString());
            sb.append(taskList.getTask(i).saveToList() + "\n");
        }
        showLine();
        return botReplySb.toString().strip();
    }

    /**
     * Help command method.
     *
     * @return Message of help command.
     */
    public static String helpCommand() {
        return "Oooooh seems like you need help.\n"
                + "Here are some commands you can use: \n\n"
                + "list \n--> to display your current list. \n\n"
                + "todo <task name> \n--> adds a new todo to your list. \n\n"
                + "event <event name> /at <date format yyyy-mm--dd> \n--> adds a new event to your list.\n\n"
                + "deadline <deadline description> /by <date format yyyy--mm-dd> \n" 
                + "--> adds a new deadline to your list.\n\n"
                + "done <task number> \n--> marks a todo || event || deadline as done.\n\n"
                + "delete <task number> \n--> deletes a todo || event || deadline from the list. \n\n"
                + "find <keyword> \n--> searches the list for tasks that contains the keyword specified. \n\n"
                + "help \n--> to summon this guide again.";
    }
}

public class Ui {

    /**
     * Prints start up message to user
     */
    public static void sayHi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    Hello! I'm Duke\n" + "  What can I do for you?");
    }

    /**
     * Greeting message user on app start up
     * @return String representing greeting
     */
    public static String greet() {
        return "I'm Mr. Meeseeks! Look at me!";
    }

    /**
     * Prints termination message to user
     */
    public static String sayBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out formatted tasks in list form
     * @param taskArrList an ArrayList representing the stored tasks
     */
//    public static void showTasks(TaskList taskArrList) {
//        for(Task t : taskArrList) {
//
//        }
//    }

    public static String noExistingSaveFile() {
        return ("WUBBA LUBBA DUB DUB");
    }

}

/**
 * Represents the UI that interacts with user.
 * Contains greetings, decorations.
 */
public class UI {

    // all the Strings to be printed for Chatbot
    private String decoration = "\t************************"; //decoration for the response
    private String greetings =  " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Function to print out greeting messages and manual for user.
     */
    public void greetUser() {
        System.out.println(this.greetings);
        System.out.println("Hello! I am Duke, and I am pleased to serve you!");
        System.out.println("Here are the following commands (case sensitive):");
        System.out.println("1) list: List out all the tasks added");
        System.out.println("2) done x: Set the task number (x) to done");
        System.out.println("3) todo [taskname]: Add a To-Do task with taskname!");
        System.out.println("4) event [taskname] /at [datetime]: Add an event with a taskname together with the date/time!");
        System.out.println("5) deadline [taskname] /by [date time] (DD-MM-YYYY HH:mm format eg: 31-12-2020 23:59): Add an deadline with a taskname together with the date/time!");
    }

    /**
     * Function to print any message using some decorations.
     * @param message message to be printed.
     */
    public void prettyPrinting(String message) {
        System.out.println(this.decoration);
        System.out.println("\t" + message);
        System.out.println(this.decoration);
    }

}

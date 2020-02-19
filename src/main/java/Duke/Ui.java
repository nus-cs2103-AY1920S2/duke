package Duke;

public class Ui {
    String logo = "DUKE!\n";
    Parser parser;
    TaskList taskList;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.parser = new Parser();
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
        parser.setTaskList(taskList);
    }

    /**
     * Scans for user input indefinitely and terminates when user inputs "bye".
     */
    /*
    public void awaitUserInput() {
        String in = sc.nextLine();
        while (!in.equals("bye")) {
            parser.getInput(in);
            in = sc.nextLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
    }
    */

    /**
     * String to be printed by Duke on Duke's startup.
     */
    String startupString() {
        return "Hello from " + logo;
    }

    /**
     * String to be printed by Duke on Duke's exit.
     */
    String exitString() {
        return "Bye!";
    }

    /** Parses user input for Duke GUI.
     * @param input user input in GUI
     */
    String parseInput(String input) {
        return parser.getOutputString(input);
    }
}

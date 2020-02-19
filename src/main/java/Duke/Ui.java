package Duke;

public class Ui {
    String logo = "THE GRANDEST DUKE!\n";
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
     * String to be printed by Duke on Duke's startup.
     */
    String startupString() {
        String helpString = parser.getOutputString("help");
        return "Welcome, welcome to... " + logo + "\n" + helpString;
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

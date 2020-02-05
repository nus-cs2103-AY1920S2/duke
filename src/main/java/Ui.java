import java.util.Scanner;

public class Ui {
    String logo = "DUKE!\n";
    Scanner sc;
    Parser parser;
    TaskList taskList;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
        this.parser = new Parser();
        System.out.println("Hello from " + logo);
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
        parser.setTaskList(taskList);
    }

    /**
     * Scans for user input indefinitely and terminates when user inputs "bye".
     */
    public void awaitUserInput() {
        String in = sc.nextLine();
        while (!in.equals("bye")) {
            parser.getInput(in);
            in = sc.nextLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /**
     * Parses user input for Duke GUI.
     * @param string user input in GUI
     */
    public void parseInput(String string) {
        parser.getInput(string);
    }
}

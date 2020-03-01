/**
 * CS2103 Individual Project.
 * The Parser class takes in the user input and makes sense of it, splitting it up into
 * commands and descriptions.
 * @author Chiang Shun De
 */

public class Parser {
    private String userInput;
    private String command;
    private String description;

    /**
     * Constructor method for Parser class
     * It takes in the user input and splits it up into commands and descriptions.
     */
    public Parser (String userInput) {
        this.userInput = userInput;
        this.command = userInput.split(" ")[0];
        if (userInput.split(" ").length > 1) {
            this.description = userInput.split(" ", 2)[1];
        } else {
            this.description = "";
        }
    }

    /**
     * A getter method to obtain the command.
     * @return A string representation of the command.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * To verify that the user input is valid.
     * @param tasks The TaskList class object containing the current Arraylist of tasks.
     */
    public void verifyCommand(TaskList tasks) {
        CommandChecker commandChecker = new CommandChecker();
        try{
            commandChecker.checkCommand(userInput, tasks.getTaskArray());
        } catch (DukeException e)  {
            System.err.println(e);
        }
    }

    /**
     * A getter method to obtain the description of the command.
     * @return A string representation of the description.
     */
    public String getDescription() {
        return this.description;
    }

}

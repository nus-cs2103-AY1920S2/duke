
/**
 * CS2103 Individual Project.
 * Parser class is to decipher and break down the user's input.
 * @author Wei Cheng
 */

public class Parser {
    String command;
    String description = "";

    /**
     * constructor for the Parser class.
     * @param userInput string representation of the user input
     */

    public Parser(String[] userInput) {
        this.command = userInput[0];
        if (userInput.length > 1) {
            this.description = userInput[1];
        }
    }

    /**
     * To obtain the command of the user.
     * @return command given by user.
     */
    public String getCommand() {

        return command;
    }

    /**
     * To obtain the description of the task.
     * @return description of the user input.
     */

    public String getDescription() {
        return description;
    }
}


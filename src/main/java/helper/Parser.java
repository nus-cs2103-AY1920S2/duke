package helper;

import exception.IncorrectInputException;

/**
 * takes in user's command and parser it.
 */

public class Parser {
    private Ui uiHelper;

    /**
     * Constructor of Ui class
     *
     * @param uiHelper
     */
    public Parser(Ui uiHelper) {
        this.uiHelper = uiHelper;
    }

    /**
     * This method will parser user input into command and task
     * Example of command : event,todo,deadline,done...
     * Example of task : read book, project meeting
     *
     * @param userInput string enterd by user
     * @return Command object
     */


    public Parser(){

    }
    public Command parse(String userInput) {

        try {
            assert !userInput.equalsIgnoreCase("") : "Please Enter a Command and Task !!";
            if (!userInput.equalsIgnoreCase("list") && !userInput.equalsIgnoreCase("bye")) {
                uiHelper.checkIsTaskEmpty(userInput);
            }
            if (userInput.equals("bye")) {
                return new Command("bye");
            } else if (userInput.equals("list")) {
                return new Command("list");
            } else if (userInput.split(" ")[0].equals("todo")) {
                return new Command("todo", userInput.replace("todo ", ""));
            } else if (userInput.split(" ")[0].equals("deadline")) {
                return new Command("deadline", userInput.replace("deadline ", ""));
            } else if (userInput.split(" ")[0].equals("event")) {
                return new Command("event", userInput.replace("event ", ""));
            } else if (userInput.split(" ")[0].equals("delete")) {
                return new Command("delete", userInput.replace("delete ", ""));
            } else if (userInput.split(" ")[0].equals("done")) {
                return new Command("done", userInput.replace("done ", ""));
            } else if (userInput.split(" ")[0].equals("find")) {
                return new Command("find", userInput.replace("find ", ""));
            } else if (userInput.split(" ")[0].equals("tag")) {
                return new Command("tag", userInput.replace("tag ", ""));
            } else {
                return new Command("");
            }
        } catch (IncorrectInputException e) {
            e.printStackTrace();
            return new Command("");
        }
    }
}



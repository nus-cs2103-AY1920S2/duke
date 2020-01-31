package duke;// To understand the user's command.
// After reading from duke.Duke.java
// Then pass it to the corresponding classes to handle it

import duke.command.*;

/**
 * The type Parser .
 */
public class Parser  {

    /**
     * Parse command form the user and pass it to the various command classes.
     *
     * @param fullCommand the full command
     * @param userInput  the user input
     * @return the associated command
     * @throws DukeException the duke exception
     */
    static Command parse(String fullCommand, String userInput) throws DukeException{
         if(fullCommand.contains("Add")) {
            return new AddCommand(userInput);
         } else if (fullCommand.contains("Bye")) {
             return new ByeCommand(userInput);
         } else if (fullCommand.contains("Delete")) {
             return new DeleteCommand(userInput);
         } else if (fullCommand.contains("List")) {
             return new ListCommand(userInput);
         } else if (fullCommand.contains("Done")) {
           return new DoneCommand(userInput);
         } else if (fullCommand.contains("Find")) {
             return new FindCommand(userInput);
         }
         else {
             throw new DukeException("Please input a correct command");
         }
    }

}

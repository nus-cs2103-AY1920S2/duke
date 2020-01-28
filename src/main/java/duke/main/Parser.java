package duke.main;

import duke.command.*;

public class Parser{

    /**
     * Returns a different Command according to input by user.
     * Different commands get executed differently.
     * 
     * @param input   Input from user to be parsed as a Command
     * @return User Command
     */
    public Command parse(String input){
        String[] inputArr = input.split(" ");
        if (input.equals("bye")){    
            return new ByeCommand();
        } else if (inputArr[0].equals("list")){
            return new ListCommand(inputArr);
        } else if (inputArr[0].equals("done")){
            return new DoneCommand(inputArr);
        } else if (inputArr[0].equals("find")) {
            return new FindCommand(inputArr);
        } else if (inputArr[0].equals("delete")){
            return new DeleteCommand(inputArr);
        } else if (inputArr[0].equals("todo")){
            return new CreateTodoCommand(inputArr);            
        } else if (inputArr[0].equals("event")){
            return new CreateEventCommand(inputArr);         
        } else if (inputArr[0].equals("deadline")){
            return new CreateDeadlineCommand(inputArr);
        } else {
            return new InvalidCommand();
        }
    } 
}
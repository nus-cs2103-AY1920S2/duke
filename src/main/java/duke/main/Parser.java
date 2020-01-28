package duke.main;

import duke.command.*;

public class Parser{
    public Command parse(String input){
        String[] inputArr = input.split(" ");
        if (input.equals("bye")){    
            return new ByeCommand();
        } else if (inputArr[0].equals("list")){
            return new ListCommand(inputArr);
        } else if (inputArr[0].equals("done")){
            return new DoneCommand(inputArr);
        } else if (inputArr[0].equals("delete")){
            return new DeleteCommand(inputArr);
        } else if (inputArr[0].equals("todo")){
            return new CreateTodoCommand(inputArr);            
        } else if (inputArr[0].equals("event")){
            System.out.println("heree");
            return new CreateEventCommand(inputArr);         
        } else if (inputArr[0].equals("deadline")){
            return new CreateDeadlineCommand(inputArr);
        } else {
            return new InvalidCommand();
        }
    } 
}
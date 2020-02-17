package commands;

import Duke.UI;
import exceptions.DukeException;
import java.util.HashMap;

/**
 * Commands.CommandHub class is for creation and initialisation of all commands.
 * Inclusive of setting up command specifics
 * ie. setting up task to be removed
 * "Parser"
 *
 */
public class CommandHub {
    private static HashMap<String,UserCommand> commandList = new HashMap();

    /**
     * Instantiates a new Commands.Command hub.
     */
    CommandHub(){
    };

    /**
     * Initialises the basic Commands.
     */
    public static void initCreator(){
        commandList.put("null",null);
        commandList.put("bye",new ByeCommand());
        commandList.put("list",new ListCommand());
    }
    /**
     * Parses the Commands.UserCommand
     *
     * @param userInput the user input
     * @return String Array parsedCommand
     */
    private static String[] parseString(String userInput){
        userInput.toLowerCase();
        String[] parsedCommand = userInput.split(" ", 2);
        return parsedCommand;
    }

    /**
     * Fetch user command.
     *
     * @param userInput the user input
     * @return the user command
     * @throws DukeException the duke exception
     */
    public static UserCommand fetchCommand(String userInput) throws DukeException {
        String[] parsedCommand = parseString(userInput);
        String ID = parsedCommand[0];
        String desc = "null";
        if(parsedCommand.length>1) {//check if exist second line
            desc = parsedCommand[1];
        }

        if(commandList.containsKey(ID)) { //gets generic commands
            return commandList.get(ID);
        } else if(ID.contains("done")){ //done command
            if(desc.equals("null")||desc.equals("")){
                throw new DukeException(UI.getReply("doneFieldEmpty"));
            }
            return new DoneCommand(Integer.parseInt(desc));
        } else if(ID.contains("todo")||ID.contains("deadline")||ID.contains("event")){
            if(desc.equals("null")){
                throw new DukeException("BAKA! Your not suppose to leave "+ ID +" field empty!");
            }
            return new AddCommand(ID, desc);
        } else if(ID.contains("delete")){
            if(desc.equals("null")||desc.equals("")){
                throw new DukeException(UI.getReply("deleteFieldEmpty"));
            }
            return new DeleteCommand(Integer.parseInt(desc));
        } else if(ID.contains("find")){
            return new FindCommand(desc);
        } else{
            throw new DukeException(UI.getReply("wrongCommand"));
        }
    }
}

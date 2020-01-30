package core;

import command.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dukexception.DukeException;


/**
 * Parser the input from user to Command that can execute.
 */
public class Parser {

    /**
     * Parses the user input to recognisable command.
     * @param userInput input from user.
     * @return command to be executed.
     * @throws DukeException user input is incomplete or invalid.
     */
    public Command parse(String userInput) throws DukeException {

        if(userInput.contains(" ")){
            String keyword=userInput.substring(0,userInput.indexOf(" "));
            String information=userInput.substring(userInput.indexOf(" ")+1);

            switch (keyword){
            case "done":
                return parseDone(information);
            case "delete":
                return parseDelete(information);
            case "todo":
                return parseTodo(information);
            case "deadline":
                return parseDeadline(information);
            case "event":
                return parseEvent(information);
            case "find":
                return parseFind(information);
            }
        }else{
            switch (userInput){
            case "list":
                return parseList();
            case "bye":
                return parseBye();
            case "reset":
                return parseReset();
            case "done": case "delete": case "find":
            case "todo": case "deadline": case "event":
                    throw new DukeException(ErrorMessage.LACK_DESCRIPTION.toString());
            }
        }
        throw new DukeException(ErrorMessage.UNKNOWN_INPUT.toString());
    }


    private Command parseBye(){
        return new ExitCommand();
    }

    private Command parseList(){
        return new ListCommand();
    }

    private Command parseReset(){
        return new ResetCommand();
    }

    private Command parseDone(String information) throws DukeException {
        try {
            int index = Integer.parseInt(information) - 1;
            return new DoneCommand(index);
        }catch (NumberFormatException e){
            throw new DukeException(ErrorMessage.LACK_NUMBER.toString());
        }
    }

    private Command parseDelete(String information) throws DukeException {
        try {
            int index = Integer.parseInt(information) - 1;
            return new DeleteCommand(index);
        }catch (NumberFormatException e){
            throw new DukeException(ErrorMessage.LACK_NUMBER.toString());
        }
    }

    private Command parseFind(String information) throws DukeException{
        if(information.trim().length()==0) {
            throw new DukeException(ErrorMessage.LACK_DESCRIPTION.toString());
        }
        return new FindCommand(information);
    }

    private Command parseTodo(String information) throws DukeException {
        if(information.trim().length()==0) {
            throw new DukeException(ErrorMessage.LACK_DESCRIPTION.toString());
        }
        return new AddToDoCommand(information);
    }

    private Command parseDeadline(String information) throws DukeException {
        String BY = " /by ";
        if(!information.contains(BY)){
            throw new DukeException(ErrorMessage.LACK_INPUT.toString()+ BY);
        }

        String description=information.substring(0,information.indexOf(BY));
        if(description.trim().length()==0) {
            throw new DukeException(ErrorMessage.LACK_DESCRIPTION.toString());
        }

        String time=information.substring(information.indexOf(BY)+5);
        if(time.trim().length()==0) {
            throw new DukeException(ErrorMessage.LACK_TIME.toString());
        }

        return new AddDeadlineCommand(description, parseDate(time));
    }

    private Command parseEvent(String information) throws DukeException {
        String AT = " /at ";
        if(!information.contains(AT)){
            throw new DukeException(ErrorMessage.LACK_INPUT.toString()+ AT);
        }

        String description=information.substring(0,information.indexOf(AT));
        if(description.trim().length()==0) {
            throw new DukeException(ErrorMessage.LACK_DESCRIPTION.toString());
        }

        String time=information.substring(information.indexOf(AT)+5);
        if(time.trim().length()==0) {
            throw new DukeException(ErrorMessage.LACK_TIME.toString());
        }
        return new AddEventCommand(description, parseDate(time));
    }

    private LocalDate parseDate(String date) throws DukeException{
        for(DateTimeUtil format: DateTimeUtil.values()){
            try{
                return LocalDate.parse(date, DateTimeFormatter.ofPattern(format.toString()));
            }catch (DateTimeParseException ignored){}
        }
        throw new DukeException(ErrorMessage.UNRECOGNISE_TIME.toString());
    }
}

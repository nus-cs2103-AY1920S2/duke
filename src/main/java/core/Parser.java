package core;

import command.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dukexception.DukeException;
import dukexception.SingletonException;

public class Parser {

    private static Parser parser=null;

    private Parser(){}

    public static Parser getInstance() throws SingletonException {
        if(parser==null){
            parser=new Parser();
            return parser;
        }
        throw new SingletonException(ErrorMessage.SINGLETON.ofType("parser"));
    }

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
            }
        }else{
            switch (userInput){
                case "list":
                    return parseList();
                case "bye":
                    return parseBye();
                case "reset":
                    return parseReset();
                case "done": case "delete":
                case "todo": case "deadline": case "event":
                    throw new DukeException(ErrorMessage.LACK_DESCRIPTION.toString());
            }
        }
        throw new DukeException(ErrorMessage.UNKNOWN_INPUT.toString());
    }


    private Command parseBye(){
        return new Command_Exit();
    }

    private Command parseList(){
        return new Command_List();
    }

    private Command parseReset(){
        return new Command_Reset();
    }

    private Command parseDone(String information) throws DukeException {
        try {
            int index = Integer.parseInt(information) - 1;
            return new Command_Done(index);
        }catch (NumberFormatException e){
            throw new DukeException(ErrorMessage.LACK_NUMBER.toString());
        }
    }

    private Command parseDelete(String information) throws DukeException {
        try {
            int index = Integer.parseInt(information) - 1;
            return new Command_Delete(index);
        }catch (NumberFormatException e){
            throw new DukeException(ErrorMessage.LACK_NUMBER.toString());
        }
    }

    private Command parseTodo(String information) throws DukeException {
        if(information.trim().length()==0) {
            throw new DukeException(ErrorMessage.LACK_DESCRIPTION.toString());
        }
        return new Command_AddToDo(information);
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

        return new Command_AddDeadline(description,parseDateTime(time));
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
        return new Command_AddEvent(description,parseDateTime(time));
    }

    private LocalDateTime parseDateTime(String dateTime) throws DukeException{
        for(DateTimeUtil format: DateTimeUtil.values()){
            try{
                return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format.toString()));
            }catch (DateTimeParseException ignored){}
        }
        throw new DukeException(ErrorMessage.UNRECOGNISE_TIME.toString());
    }
}

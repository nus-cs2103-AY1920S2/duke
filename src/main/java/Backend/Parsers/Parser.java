package Backend.Parsers;

import java.io.IOException;
import java.util.Optional;

import Backend.Exceptions.DukeException;
import Backend.Objects.Task.Task;
import Backend.Objects.Task.Todo;
import Backend.Objects.Task.Event;
import Backend.Objects.Task.Deadline;

public class Parser {

    private String input;
    /**
     * Backend.Utils.Utils.Parser
     * Each input starts with a COMMAND, followed by the CONTENT and optionally, the DATE
     */
    public Parser( String input ){
        this.input = input;
    }

    /**
     * Gets command, first word of input string
     * @return command
     */
    public String parseCommand(){
        return input.split(" ")[0].trim();
    }

    /**
     * Gets content, removes command and everything after slash
     * @return content
     */
    public String parseContent(){

        String[] splitArray = input.split("/");

        if( splitArray.length < 2 ){
            return input.substring(parseCommand().length() + 1).trim();
        } else {
            return input.substring( parseCommand().length() + 1 ).split("/")[0].trim();
        }

    }

    /**
     * Gets dateString
     * @return dateString
     */
    public String parseDateString(){

        String[] splitArray =  input.split("/");

        if (splitArray.length < 2){
            return "";
        } else {
            return splitArray[1].split(" ")[1].trim();
        }

    }

    /**
     * if content is integer, casts content to integer
     * @return content as integer
     */
    public Optional<Integer> parseContentAsInt(){

        Integer contentAsInt;

        try {
            contentAsInt = Integer.parseInt(String.valueOf(parseContent()));
            return Optional.of(contentAsInt);
        } catch( NumberFormatException e ){
            return Optional.empty();
        }

    }

    /**
     * parses line to derive task arguments
     * @param line line to be parsed
     * @param index index of task
     * @return task
     */
    public static Task formatTaskString( String line, int index) throws DukeException {

        try {
            //get type
            char taskChar = line.charAt(4);
            char doneChar = line.charAt(7);

            String subString = line.substring(9);

            String dateString = null;

            if( subString.split(":").length > 1 ){
                dateString = subString.split(":")[1].substring(0, subString.split(":")[1].length() - 1).trim();
            }

            Task task;

            switch (taskChar){
                case 'T':
                    String content = subString.trim();
                    task = new Todo(content);
                    break;
                case 'D':
                    content = subString.split("\\(")[0].trim();
                    task =  new Deadline(content, new DateParser(dateString));
                    break;
                case 'E':
                    content = subString.split("\\(")[0].trim();
                    task =  new Event(content, new DateParser(dateString));
                    break;
                default:
                    throw new DukeException( new IOException() );
            }

            task.indexTask( index );

            if( doneChar == 'Y' ){
                task.markAsDone();
            }

            return task;

        } catch( StringIndexOutOfBoundsException e ){
            throw new DukeException(e);
        }

    }

}

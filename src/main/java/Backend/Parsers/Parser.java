package Backend.Parsers;

import java.io.IOException;
import java.util.Optional;

import Backend.Constants.TaskChars;
import Backend.Exceptions.DukeException;
import Backend.Objects.Task.Task;
import Backend.Objects.Task.Todo;
import Backend.Objects.Task.Event;
import Backend.Objects.Task.Deadline;

public class Parser {

    private String input;

    public Parser( String input ){
        this.input = input;
    }

    /**
     * Gets a command word based on a given input string. The command is the first word of the string
     * @return a string representing a command
     */
    public String parseCommand(){
        return input.split(" ")[0].trim();
    }

    /**
     * Gets the content of a given input string. The content is everything in the string apart from
     * the command and the date string (if present)
     * @return a string representing the content of the input string
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
     * Gets the date as inputted by the user. The only valid format for this date string is YYYY-MM-DD.
     * @return a date string in the format YYYY-MM-DD
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
     * Gets the content of an input string and, if the content consists of only integers, converts the string to an Optional Integer.
     * This methods handles commands that deal with task indices.
     * @return an integer representing a task index
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
     * Parses a saved task string to retrieve the content, type of task and date string.
     * Also creates a task object from the values parsed.
     * @param line a line representing a saved task
     * @param index the index of the line that is parsed
     * @return a task
     */
    public static Task parseSavedTask( String line, int index) throws DukeException {

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
                case TaskChars.TODO_CHAR:
                    String content = subString.trim();
                    task = new Todo(content);
                    break;
                case TaskChars.DEADLINE_CHAR:
                    content = subString.split("\\(")[0].trim();
                    task =  new Deadline(content, new DateParser(dateString));
                    break;
                case TaskChars.EVENT_CHAR:
                    content = subString.split("\\(")[0].trim();
                    task =  new Event(content, new DateParser(dateString));
                    break;
                default:
                    throw new DukeException( new IOException() );
            }

            task.indexTask( index );

            if( doneChar == TaskChars.DONE_CHAR ){
                task.markAsDone();
            }

            return task;

        } catch( StringIndexOutOfBoundsException e ){
            throw new DukeException(e);
        }

    }

}

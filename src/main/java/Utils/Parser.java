package Utils;

import java.util.Optional;

import Task.Task;
import Task.Todo;
import Task.Event;
import Task.Deadline;
import Task.DukeDate;

public class Parser {

    private String input;
    /**
     * Utils.Parser
     * Each input starts with a COMMAND, followed by the CONTENT and optionally, the DATE
     */
    public Parser( String input ){
        this.input = input;
    }

    /**
     * Gets command, first word of input string
     * @return command
     */
    public String getCommand(){
        return input.split(" ")[0].trim();
    }

    /**
     * Gets content, removes command and everything after slash
     * @return content
     */
    public String getContent(){

        String[] splitArray = input.split("/");

        if( splitArray.length < 2 ){
            return input.substring(getCommand().length() + 1).trim();
        } else {
            return input.substring( getCommand().length() + 1 ).split("/")[0].trim();
        }

    }

    /**
     * Gets dateString
     * @return dateString
     */
    public String getDateString(){

        String[] splitArray =  input.split("/");

        if (splitArray.length < 2){
            return "";
        } else {
            return splitArray[1].split(" ")[1].trim();
        }

    }

    public Optional<Integer> getContentAsInt(){

        Integer contentAsInt;

        try {
            contentAsInt = Integer.parseInt(String.valueOf(getContent()));
            return Optional.of(contentAsInt);
        } catch( NumberFormatException e ){
            return Optional.empty();
        }

    }

    public static Task parseTask(String line, int index){

        //get type
        char taskChar = line.charAt(3);
        char doneChar = line.charAt(6);

        String subString = line.substring(8);

        String dateString = null;

        if( subString.split(":").length > 1 ){
            dateString = subString.split(":")[1].substring(0, subString.split(":")[1].length() - 1).trim();
        }

        Task task;

        switch (taskChar){
            case 'T':
                String content = subString.trim();
                task = new Todo(content, index);
                break;
            case 'D':
                content = subString.split("\\(")[0].trim();
                task =  new Deadline(content, index, new DukeDate(dateString));
                break;
            default:
                content = subString.split("\\(")[0].trim();
                task =  new Event(content, index, new DukeDate(dateString));
        }

        if( doneChar == 'Y' ){
            task.markAsDone();
        }

        return task;
    }

}

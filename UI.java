/**
 * Represents a user input
 * @author kerwei
 * @version 1.0
 */
public class UI {

    String input;

    public UI(String input) {
        this.input = input;
    }

    /**
     * Splits the user input and and stores it in an array to be used
     * @param input User input
     * @return Array of task details
     */
    public static String[] handleInput(String input){
        String[] splitInput = input.split(" ", 2);
        String taskType;
        String taskDescription = "";
        String dateTime = "date or time not specific";
        taskType = splitInput[0];

        if (taskType.equals("list") || taskType.equals("undo")){
            //do nothing
        } else if(splitInput.length > 1){
            if (taskType.equals("deadline")) {
                String[] taskDetails = splitInput[1].split("by ");
                taskDescription = taskDetails[0];
                dateTime = taskDetails[1];
            } else if (taskType.equals("event")) {
                String[] taskDetails = splitInput[1].split("at ");
                taskDescription = taskDetails[0];
                dateTime = taskDetails[1];
            } else {
                taskDescription = splitInput[1];
            }
        }

        String[] taskInfo = {taskType, taskDescription, dateTime};
        return taskInfo;
    }
}

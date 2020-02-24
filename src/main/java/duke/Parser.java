package duke;

/**
 * Logical brain to support processing information.
 */
public class Parser {

    // Storage methods

    /**
     * Determines if a task is done or not from a line read in from the file.
     *
     * @param line line read in from a source file.
     * @return String representing completion ("V") or incomplete ("X").
     */
    public String determineTaskDoneStatusFromFileLine(String line) {
        int indexOfFirstSquareBracket = line.indexOf("[");
        String doneStatus = String.valueOf(line.charAt(indexOfFirstSquareBracket + 1));
        assert (doneStatus.equals("V") || doneStatus.equals("X")) : "Done status can only be X or V";
        return doneStatus;
    }

    /**
     * Determines the type of Task, either a To-Do, Event, or DeadLine.
     *
     * @param line line read in from source file.
     * @return String representing the type of Task, either a To-Do, Event, or DeadLine.
     */
    public String determineTaskTypeFromFileLine(String line) {
        int indexOfFirstSquareBracket = line.indexOf("[");
        String taskType = String.valueOf(line.charAt(indexOfFirstSquareBracket + 5));
        return taskType;
    }

    /**
     * Determines the Task Details like date limits or time of happening.
     *
     * @param line line read in from source file.
     * @return String with all the details like date/time in raw form excluding the index, task status, and task type.
     */
    public String determineTaskDetailsFromFileLine(String line) {
        int indexOfFirstSquareBracket = line.indexOf("[");
        String details = line.substring(indexOfFirstSquareBracket + 8); // from unnecessary info at the front of line.
        return details;
    }

    /**
     * Checks if the String obtained from lastSavedTasks.txt represents a completed Task.
     *
     * @param s Either a V or X.
     * @return whether a Task is completed.
     */
    public boolean isDone(String s) {
        return (s.equals("V"));
    }

    // TaskList methods

    /**
     * Gives String representing the command type.
     *
     * @param command String representing all the words typed by the user.
     * @return String representing the keywords of a command without the command type.
     */
    public String findKeywordFromString(String command) {
        // Split String command to find the intention of the user (first word)
        String[] commandWords = command.split("\\s");
        return commandWords[0];
    }

    /**
     * Checks if this is capable of being a valid done command based on the criteria of length being more than 1.
     *
     * @param command String array representing each word in the String.
     * @return whether this can be a possible done command.
     */
    public boolean satisfiesMinimumDoneCommandLength(String[] command) {
        return (command.length > 1);
    }

    /**
     * Checks if this is an actual done command.
     *
     * @param commandWords array representing each word in the String.
     * @param taskListSize current size of the TaskList.
     * @return whether this is a correct done command.
     */
    public boolean determineIfValidDoneCommand(String[] commandWords, int taskListSize) {
        int taskNumber = Integer.parseInt(commandWords[1]);
        return (taskNumber <= taskListSize);
    }

}

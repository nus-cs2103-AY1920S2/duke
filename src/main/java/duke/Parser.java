package duke;

public class Parser {

    // Storage methods
    public String determineTaskDoneStatusFromFileLine(String line) {
        int indexOfFirstSquareBracket = line.indexOf("[");
        String doneStatus = String.valueOf(line.charAt(indexOfFirstSquareBracket + 1));
        return doneStatus;
    }

    public String determineTaskTypeFromFileLine(String line) {
        int indexOfFirstSquareBracket = line.indexOf("[");
        String taskType = String.valueOf(line.charAt(indexOfFirstSquareBracket + 5));
        return taskType;
    }

    public String determineTaskDetailsFromFileLine(String line) {
        int indexOfFirstSquareBracket = line.indexOf("[");
        String details = line.substring(indexOfFirstSquareBracket + 8);
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
    public boolean satisfiesMinimumDoneCommandLength(String[] command) {
        return (command.length > 1);
    }

    public boolean determineIfValidDoneCommand(String[] commandWords, int taskListSize) {
        return(Integer.parseInt(commandWords[1]) <= taskListSize);
    }

}

package dukeclasses;

/**
 * This class handles the parsing of strings.
 */
public class Parser {

    public Parser(){
    }

    /**
     * Handles the "done ..." inputs.
     *
     * @param textEntered user's input
     * @return indexOfTask done
     */
    public int handleDoneCommands(String textEntered) throws DukeException {

        String[] temporary = textEntered.split(" ");
        int indexOfTaskDone;
        try {
            indexOfTaskDone = Integer.parseInt(temporary[1]);
            return indexOfTaskDone;
        } catch (Exception ex) {
            throw new DukeException("done must be followed by a number");
        }

    }

    /**
     * Handles "delete .." inputs.
     *
     * @param textEntered user's input
     * @return index of task to be deleted
     */
    public int handleDeleteCommands(String textEntered) {
        String[] temporary = textEntered.split(" ");
        int indexOfTaskToDelete = Integer.parseInt(temporary[1]);
        return indexOfTaskToDelete;
    }

    /**
     * Handles "find .." inputs.
     *
     * @param textEntered user's input
     * @return a String[]  to be passed into TaskManager.
     */

    public String[] handleFindCommands(String textEntered) {

        return textEntered.split(" ");
    }

    /**
     * Handles C-Priority addition.
     *
     * @param textEntered chooses which index of task to be set as high priority.
     * @return index
     */

    public int handleHighPriorityCommands(String textEntered)  {

        String[] temporary = textEntered.split(" ");
        int indexOfTaskDone = Integer.parseInt(temporary[1]);
        return indexOfTaskDone;


    }

}

package duke;


public class Parser {

    /**
     * Directs input command from System.in to the parse method.
     *
     * @param tasks TaskList that stores all the current tasks
     * @param ui UI for interaction with the user
     * @return boolean. true indicates that the user has terminated the program (by typing "bye")
     * @throws DukeException for invalid user commands
     */
    public static boolean parseNextCmd(TaskList tasks, Ui ui) throws DukeException {
        return parse(tasks, ui, ui.getUserCmd());
    }


    /**
     * Directs input command from the GUI to the parse method.
     *
     * @param tasks TaskList that stores all the current tasks
     * @param ui UI for interaction with the user
     * @param userCmd String input from GUI
     * @return boolean. true indicates that the user has terminated the program (by typing "bye")
     * @throws DukeException for invalid user commands
     */
    public static boolean parseNextCmd(TaskList tasks, Ui ui, String userCmd) throws DukeException {
        return parse(tasks, ui, userCmd);
    }


    /**
     * Parses the command the user enters and execute it.
     *
     * @param tasks TaskList that stores all the current tasks
     * @param ui UI for interaction with the user
     * @return boolean. true indicates that the user has terminated the program (by typing "bye")
     * @throws DukeException for invalid user commands
     */
    private static boolean parse(TaskList tasks, Ui ui, String userCmd) throws DukeException {

        String[] cmdString = userCmd
                .strip()
                .split(" ", 2);
        DukeCommand cmdType;
        String cmdDetails;

        try {
            cmdType = DukeCommand.valueOf(cmdString[0].strip().toUpperCase());
        } catch (Exception e) {
            throw new DukeException("\tMortal, that's an invalid Task!");
        }

        if (cmdString.length == 1) {
            cmdDetails = "";
        } else {
            cmdDetails = cmdString[1].strip();
        }

        return cmdType.executeCommand(tasks, ui, cmdDetails);
    }
}

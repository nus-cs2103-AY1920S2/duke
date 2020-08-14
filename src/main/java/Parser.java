import java.time.format.DateTimeParseException;

public class Parser {
    private String[] inputs;
    private String command;
    private String description;
    private String timing;


    /**
     * Constructor for Parser Class.
     *
     * @param input User input.
     */
    public Parser(String input) {
        this.inputs = input.split(" ");
        this.command = inputs[0];
        this.description = "";
        this.timing = "";
        String splitter = " /";

        if (command.equals("deadline")) {
            splitter = " /by ";
        } else if (command.equals("event")) {
            splitter = " /at ";
        } else if (command.equals("snooze")) {
            splitter = (" /to ");
        }

        String[] descriptionSplit;
        descriptionSplit = input.split(splitter);
        if (descriptionSplit.length > 1) {
            this.timing = descriptionSplit[1];
        }

        String splitterforDesc = command;
        splitterforDesc += " ";
        String[] finalDes = descriptionSplit[0].split(splitterforDesc);

        if (finalDes.length > 1) {
            description = finalDes[1];
        }

    }

    /**
     * Returns the command provided to the Parser.
     *
     * @return command provided.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns the description provided to the Parser.
     *
     * @return description provided.
     * @throws DukeException when no description is provided.
     */
    public String getDescription() throws DukeException {
        if (description.equals("")) {
            throw new DukeException("It appears that no description was provided for this " + command + "!");
        }
        return description;
    }

    /**
     * Returns the timing provided to the Parser.
     *
     * @return timing provided in String format.
     * @throws DukeException when no timing is provided.
     */
    public String getTiming() throws DukeException {
        if (timing.equals("")) {
            throw new DukeException("It appears that no timing was provided for this " + command + "!");
        }
        return timing;
    }

    /**
     * Returns the index number provided to the Parser.
     *
     * @return Index number.
     * @throws DukeException when index number cannot be parsed as an integer.
     */
    public int getIndex() throws DukeException {
        if (description.equals("")) {
            throw new DukeException("It appears that no index was provided for the command " + command + "!");
        }

        try {
            return (Integer.parseInt(description));
        } catch (NumberFormatException ex) {
            throw new DukeException("Index provided for command " + command + " is not an integer!");
        }
    }


    /**
     * returns a String of the parsed command.
     *
     * @param ui    the GUI used.
     * @param tasks the list of tasks provided.
     * @return a string of the parsed command.
     */
    public String parseCommand(Gui ui, TaskList tasks) {
        String toReturn = "";

        try {
            switch (command) {
            case "bye":
                toReturn = ui.terminateGui();
                break;
            case "list":
                toReturn = ui.printOutTasks(tasks);
                break;
            case "delete":
                toReturn = ui.printOutDeleted(tasks, getIndex() - 1);
                tasks.removeTask(getIndex() - 1);
                break;
            case "done":
                tasks.getTask(getIndex() - 1).doTask();
                toReturn = (ui.printOutDoneTask(tasks, getIndex() - 1));
                break;
            case "find":
                toReturn = (ui.printOutFound(tasks.getMatches(getDescription())));
                break;
            case "snooze":
                tasks.getTask(getIndex() - 1).snooze(timing);
                toReturn = ui.printOutSnoozed(tasks, getIndex() - 1, timing);
                break;
            case "event":
                tasks.newEvent(getDescription(), getTiming());
                toReturn = ui.printOutAdded(tasks);
                break;
            case "todo":
                tasks.newToDo(getDescription());
                toReturn = ui.printOutAdded(tasks);
                break;
            case "deadline":
                try {
                    tasks.newDeadline(getDescription(), getTiming());
                    toReturn = ui.printOutAdded(tasks);
                    break;
                } catch (DateTimeParseException e) {
                    throw new DukeException("Invalid date format for deadline used! "
                            + "Please re-try using the date format 'yyyy-mm-dd HHMM'");

                }
            default:
                throw new DukeException("I'm sorry, I do not understand what that means!");
            }
        } catch (DukeException e) {
            toReturn = e.toString();
        }


        return toReturn;
    }

}

import java.time.LocalDate;

public class Parser {

    private String raw_user_input;

    /**
     * Constructor method for Parser.
     * @param user_input The raw user input, read in as a String.
     */
    public Parser(String user_input) {
        raw_user_input = user_input;
    }

    /**
     * Splits the raw user input by spaces and checks the length of the resulting array.
     * Used for checking for exceptions (eg. empty Task descriptions)
     * @return Length of the raw user input, split by spaces.
     */
    public int splitStringLength() {
        String[] split_str = raw_user_input.split(" ");
        return split_str.length;
    }

    /**
     * Processes raw user input and extracts the command word.
     * @return Extracted command.
     */
    public String processCommand() {
        String[] split_str = raw_user_input.split(" ");
        return split_str[0];
    }

    /**
     * Processes raw user input and extracts the description.
     * @return Extracted description of the Todo.
     */
    public String processDescriptionForTodo() throws DukeException {
        if (splitStringLength() == 1) {
            throw new DukeException("Mission parameters are null!");
        }
        int start_index = processCommand().length() + 1;
        int end_index = raw_user_input.length();
        return raw_user_input.substring(start_index, end_index);
    }

    /**
     * Processes raw user input and extracts the description.
     * This is only called when the command is an Event, or Deadline.
     * @return Extracted description of the Event or Deadline.
     * @throws DukeException when there is no description
     */
    public String processDescriptionForEventOrDeadline() throws DukeException {
        if (splitStringLength() == 1) {
            throw new DukeException("Mission parameters are null!");
        }
        int start_index = processCommand().length() + 1;
        int end_index = raw_user_input.indexOf("/");
        return raw_user_input.substring(start_index, end_index);
    }

    /**
     * Processes raw user input for Deadline Tasks, extracts the date of Task and converts it to a LocalDate object.
     * @return LocalDate object.
     * @throws DukeException when there is no description
     */
    public LocalDate processDateForDeadline() {
        int start_index = raw_user_input.indexOf("/");
        int end_index = raw_user_input.length();

        assert start_index <= end_index: "No date provided for the mission.";

        String unconverted_date = raw_user_input.substring(start_index, end_index).replace("/by ", "");
        return LocalDate.parse(unconverted_date);
    }

    /**
     * Processes raw user input for Event Tasks, extracts the date of Task and converts it to a LocalDate object.
     * @return
     */
    public LocalDate processDateForEvent() {
        int start_index = raw_user_input.indexOf("/");
        int end_index = raw_user_input.length();

        assert start_index <= end_index: "No date provided for the mission.";

        String unconverted_date = raw_user_input.substring(start_index, end_index).replace("/at ", "");
        return LocalDate.parse(unconverted_date);
    }

    /**
     * Returns the specified index for the respective command.
     * This is only called when the command is "done" or "delete".
     * @return Specified index in the form of an integer variable.
     */
    public int processIndex() {
        String[] split_str = raw_user_input.split(" ");

        assert split_str.length != 1: "Which mission have you completed?";

        return Integer.parseInt(split_str[1]) - 1;
    }

    /**
     * Returns the keyword for the respective command.
     * This is only called when the command is "find".
     * @return Specified keyword in the form of a String variable.
     */
    public String processKeyword() {
        String[] split_str = raw_user_input.split(" ");

        assert split_str.length != 1: "Which mission are you trying to complete?";

        return split_str[1];
    }

    /**
     * Only used when the command is "notes", meaning that the following commands are dealing with the TranScribe.
     * @return the notes commands.
     */
    public String processNotesCommand() {
        int start_index = raw_user_input.indexOf(" ") + 1;
        int end_index = raw_user_input.length();
        return raw_user_input.substring(start_index, end_index);
    }

}

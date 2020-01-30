import java.util.HashMap;
import java.util.Map;

/**
 * Represents the available command Chatbot EXE recognise with
 * an abbreviation tag to command that will generate a Task object.
 *
 * @author Kenny Ho
 */
public enum CommandList {
    /**
     * Deadline command with D as abbreviation.
     */
    DEADLINE("D"),
    /**
     * Delete command with no abbreviation.
     */
    DELETE(""),
    /**
     * Done command with no abbreviation.
     */
    DONE(""),
    /**
     * Event command with E as abbreviation.
     */
    EVENT("E"),
    /**
     * List command with no abbreviation.
     */
    LIST(""),
    /**
     * Todo command with T as abbreviation.
     */
    TODO("T"),
    /**
     * Bye command with no abbreviation.
     */
    BYE("");

    private String abbreviation;
    private static final Map<String, CommandList> lookup = new HashMap<>();

    CommandList(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Returns the abbreviation of command as a String.
     *
     * @return An abbreviation String object representing the command.
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    static {
        for (CommandList cl : CommandList.values()) {
            lookup.put(cl.getAbbreviation(), cl);
        }
    }

    /**
     * Returns CommandList object by passing it's corresponding abbreviation.
     *
     * @param abbreviation Abbreviation in the form of string representing the command.
     * @return CommandList object correspond to the abbreviation passed in as param.
     */
    public static CommandList get(String abbreviation) {
        return lookup.get(abbreviation);
    }
}

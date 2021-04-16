package duke.core;

/**
 * Represents a manager for user interaction related functions.
 */
public class Ui {
    /**
     * Constructs a fresh Ui instance.
     */
    public Ui() {
    }

    /**
     * Returns the introductory message for Duke.
     * @return The introductory message.
     */
    public String introMessage() {
        return Message.INTRO;
    }

    /**
     * Returns the goodbye message for Duke.
     * @return The goodbye message.
     */
    public String goodbyeMessage() {
        return Message.EXIT;
    }

    /**
     * Prints the divider lines for messages in Duke.
     * @param content The content within the divider lines.
     */
    public static String printLines(String content) {
        assert content.length() != 0 : "Content should not be empty.";
        
        StringBuilder result = new StringBuilder();

        result.append("    ____________________________________________________________");
        String[] split = content.split("\\r?\\n");
        for (String str : split) {
            result.append("\n" + "     ");
            result.append(str);
        }
        result.append("    ____________________________________________________________");

        return result.toString();
    }
}
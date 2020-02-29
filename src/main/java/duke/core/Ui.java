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
     * Prints the introductory message for Duke.
     */
    public String printIntro() {
        return "I'm not Duke, I'm Cute!\n"
        + "I'm feeling a lil hungry... ooh!\n"
        + "Hi little fishy, what you up to?";
    }

    /**
     * Prints the goodbye message for Duke.
     */
    public String printGoodbye() {
        return "Cute lunges forward and snaps you up in a gulp.\n"
        + "Oops, maybe she should've been named Hungry instead.";
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
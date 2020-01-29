import java.util.List;

/**
 * This class handles the user interface, specifically the output of the program
 * to the user.
 */
public class Ui {
    private static final String outputBorder = "===";

    /**
     * Formats and prints the String {@code line}.
     * 
     * @param line the String to print
     */
    public void output(String line) {
        System.out.println("    " + outputBorder);
        System.out.println("    " + line);
        System.out.println("    " + outputBorder);
    }

    /**
     * Formats and prints the List of {@code lines}, where each element in the list
     * has its own line.
     *
     * @param lines the {@code List<String>} to print.
     */
    public void output(List<String> lines) {
        System.out.println("    " + outputBorder);
        for (String line : lines) {
            System.out.println("    " + line);
        }
        System.out.println("    " + outputBorder);
    }
}
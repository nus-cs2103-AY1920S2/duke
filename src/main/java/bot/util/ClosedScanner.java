package bot.util;

import java.util.Scanner;

/**
 * An interface that contains a static method
 * to get a closed Scanner (Scanner that has
 * no output)
 */
public interface ClosedScanner {
    /**
     * A method that returns a closed Scanner,
     * which is a Scanner that has no output
     *
     * @return The closed Scanner.
     */
    static Scanner getClosedScanner() {
        Scanner sc = new Scanner("");
        sc.close();
        return sc;
    }
}

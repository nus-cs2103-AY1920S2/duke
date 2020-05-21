/**
 * Represent a class to handle the parsing of the text for processing of commands.
 */
public class TextParser {

    /**
     * Parse a string into array using String.split(" ", 2).
     * @param line to be parsed
     * @return String[]
     */
    public static String[] myFirstParser(String line) {
        String[] arrSplit = line.split(" ", 2);
        return arrSplit;
    }

    /**
     * Parse a string into array using String.split("/", 2).
     * @param line to be parsed
     * @return String[]
     */
    public static String[] mySecondParser(String line) {
        String[] arrSplit = line.split("/", 2);
        return arrSplit;
    }

    /**
     * Parse a string into array using String.split("/").
     * no limits
     * @param line to be parsed
     * @return String[]
     */
    public static String[] myThirdParser(String line) {
        String[] arrSplit = line.split("/");
        return arrSplit;
    }
}

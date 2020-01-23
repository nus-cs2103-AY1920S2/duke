public class DukeFormat {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Adds a 4-space indent to all lines of a given text.
     *
     * @param text the text to indent.
     * @return the indented text.
     */
    public static String indent(String text) {
        return indent(text, 4);
    }

    /**
     * Adds an indent to all lines of a given text.
     *
     * @param text the text to indent.
     * @param indentWidth the character width of the indentation.
     * @return the indented text.
     */
    public static String indent(String text, int indentWidth) {
        String indent = " ".repeat(indentWidth);
        return text.replaceAll("(?m)^", indent);
    }

    /**
     * Returns a line that is 60 characters long.
     *
     * @return a line that is 60 characters long.
     */
    public static String line() {
        return line(60);
    }

    /**
     * Returns a line of a given length.
     *
     * @param lineWidth the character width of the line.
     * @return a line that of the specified character length.
     */
    public static String line(int lineWidth) {
        String lineSymbol = "-";
        return lineSymbol.repeat(lineWidth);
    }

    /**
     * Returns a line that is 60 characters long and with a 4-space indent.
     *
     * @return a line that is 60 characters long and with a 4-space indent.
     */
    public static String indentedLine() {
        return indentedLine(4, 60);
    }

    /**
     * Returns a line of a given length and indentation.
     *
     * @param indentWidth the character width of the indentation.
     * @param lineWidth the character width of the line.
     * @return a line of a given length and indentation.
     */
    public static String indentedLine(int indentWidth, int lineWidth) {
        return indent(line(lineWidth), indentWidth);
    }
}

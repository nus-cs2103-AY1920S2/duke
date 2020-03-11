package dude.parser;

import java.time.LocalDate;

public interface IDateParser {
    /**
     * Returns a format string demonstrating the use of this parser.
     *
     * @return the format this parser accepts.
     */
    String getFormat();

    /**
     * Returns a list of example strings that are parsable by this parser.
     *
     * @return array of strings of positive date examples.
     */
    String[] getExamples();

    /**
     * Returns an explanation of the type of date format this parser understands,
     * and the date it translates into.
     *
     * @return an explanation string.
     */
    String getExplanation();

    /**
     * Attempts to parse the given date string into the parser's format. Returns null if the
     * parse fails.
     *
     * @param dateString the string to parse as a date.
     * @return the parsed LocalDate, null otherwise.
     */
    LocalDate parse(String dateString);
}

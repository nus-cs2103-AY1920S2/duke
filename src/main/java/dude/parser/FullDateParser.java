package dude.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A parser of a fully specified date that can be parsed by Java's LocalDate#parse.
 */
public class FullDateParser implements IDateParser {
    private String format;
    private String[] examples;
    private String explanation;
    private DateTimeFormatter formatter;

    /**
     * Initializes the parser.
     *
     * @param format the format this parser accepts.
     * @param examples array of strings of positive date examples.
     * @param explanation an explanation of the parser.
     */
    public FullDateParser(String format, String[] examples, String explanation) {
        this.format = format;
        this.examples = examples;
        this.explanation = explanation;
        this.formatter = DateTimeFormatter.ofPattern(format);
    }

    /**
     * Returns a format string demonstrating the use of this parser.
     *
     * @return the format this parser accepts.
     */
    @Override
    public String getFormat() {
        return this.format;
    }

    /**
     * Returns a list of example strings that are parsable by this parser.
     *
     * @return array of strings of positive date examples.
     */
    @Override
    public String[] getExamples() {
        return this.examples;
    }

    /**
     * Returns an explanation of the type of date format this parser understands,
     * and the date it translates into.
     *
     * @return an explanation string.
     */
    @Override
    public String getExplanation() {
        return this.explanation;
    }

    /**
     * Attempts to parse the given date string into the parser's format. Returns null if the
     * parse fails.
     *
     * @param dateString the string to parse as a date.
     * @return the parsed LocalDate, null otherwise.
     */
    @Override
    public LocalDate parse(String dateString) {
        try {
            return LocalDate.parse(dateString, this.formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}

package dude.parser;

import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A parser of offsets from the current date in terms
 * of days, weeks, months or years.
 */
public class DateOffsetParser implements IDateParser {
    private static final String[] EXAMPLES = {"10 days", "4 weeks", "1 year"};
    private static final Pattern FORMAT =
            Pattern.compile("(?<number>\\d+) (?<period>day|week|month|year)s?");

    /**
     * Returns a format string demonstrating the use of this parser.
     *
     * @return the format this parser accepts.
     */
    @Override
    public String getFormat() {
        return "number [day(s) | week(s) | month(s) | year(s)]";
    }

    /**
     * Returns a list of example strings that are parsable by this parser.
     *
     * @return array of strings of positive date examples.
     */
    @Override
    public String[] getExamples() {
        return EXAMPLES;
    }

    /**
     * Returns an explanation of the type of date format this parser understands,
     * and the date it translates into.
     *
     * @return an explanation string.
     */
    @Override
    public String getExplanation() {
        return "The given duration after today. Eg. If today is 2020-02-09, '10 days' "
                + "would be 2020-02-19";
    }

    /**
     * Attempts to parse the given date string as an offset from the current date in terms
     * of days, weeks, months or years. Returns null otherwise.
     *
     * @param dateString the string to parse as a date.
     * @return the parsed LocalDate, null otherwise.
     */
    @Override
    public LocalDate parse(String dateString) {
        Matcher m = FORMAT.matcher(dateString);
        if (!m.matches()) {
            return null;
        }

        int number = Integer.parseInt(m.group("number"));
        try {
            switch (m.group("period")) {
            case "day":
                return LocalDate.now().plusDays(number);
            case "week":
                return LocalDate.now().plusWeeks(number);
            case "month":
                return LocalDate.now().plusMonths(number);
            case "year":
                return LocalDate.now().plusYears(number);
            default:
                return null;
            }
        } catch (DateTimeException e) {
            return null;
        }
    }
}

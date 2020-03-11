package dude.parser;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A parser of the day of the week, taken to be the next occurrence of said day
 * from current date. Additional week offsets can be optionally specified.
 */
public class DayOfWeekParser implements IDateParser {
    private static final String[] EXAMPLES = {"Monday +4", "Tue", "Sun +3"};
    private static final Pattern FORMAT  =
            Pattern.compile("(?<day>\\w+)( \\+(?<number>\\d+))?");

    /**
     * Returns a format string demonstrating the use of this parser.
     *
     * @return the format this parser accepts.
     */
    @Override
    public String getFormat() {
        return "dayOfWeek [+number]";
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
        return "If just a day of the week is given, say Tue or Wed, the date is "
                + "taken to be the next closest day from today. Eg. 'Tue' refers to next Tuesday."
                + "If a number n is specified, the date is the given day n weeks from now. "
                + "Eg. 'Monday +4' refers to the Monday 4 weeks from now";
    }

    /**
     * Attempts to parse the given day of week into the next occurrence of said day from the current
     * date, optionally with the specified week offset. Returns null otherwise.
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

        int number = 0;
        if (m.group("number") != null) {
            number = Integer.parseInt(m.group("number"));
        }
        try {
            DayOfWeek day = DateTimeFormatter.ofPattern("EEE")
                    .parse(m.group("day").substring(0,3))
                    .query(DayOfWeek::from);
            return getNearestDate(day).plusWeeks(number);
        } catch (DateTimeException e) {
            return null;
        }
    }

    private LocalDate getNearestDate(DayOfWeek day) {
        LocalDate currDay = LocalDate.now();
        return currDay.datesUntil(currDay.plusWeeks(1))
                .filter(date -> date.getDayOfWeek() == day)
                .findFirst()
                .get(); // We are sure that within 1 week we will find the given day
    }

}

package dude.parser;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser {
    /**
     * Parses the given string from the given command from one of the accepted formats into a LocalDate.
     * Accepts these formats:
     * - yyyy-mm-dd
     * - d MMM yyyy
     * - dayOfWeek [+number]
     * - number [day(s) | week(s) | month(s) | year(s)]
     *
     * @param dateString the string to parse.
     * @param command the command to parse given dateString for.
     * @return the parsed LocalDate.
     * @throws ParsingException if given string is invalid or results in an invalid date.
     */
    public static LocalDate parse(String dateString, String command) throws ParsingException {
        LocalDate date;
        for (Function<String, LocalDate> parser : parsers) {
            date = parser.apply(dateString);
            if (date != null) {
                return date;
            }
        }

        // If none of the parsers match, throw ParsingException
        String errorMsg = "I don't understand this date: " + dateString
                + ". Type 'help -date' to see the date formats I accept.";
        throw new ParsingException(errorMsg, Parser.USAGES.get(command));
    }

    private static List<Function<String, LocalDate>> parsers = List.of(
        ds -> fullDateParser(ds, DateTimeFormatter.ISO_LOCAL_DATE),
        ds -> fullDateParser(ds, DateTimeFormatter.ofPattern("d MMM yyyy")),
        DateParser::dayOfWeekParser,
        DateParser::offsetParser);

    private static LocalDate fullDateParser(String dateString, DateTimeFormatter dtf) {
        try {
            return LocalDate.parse(dateString, dtf);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static final Pattern dayOfWeekFormat =
            Pattern.compile("(?<day>\\w+)( \\+(?<number>\\d+))?");

    private static LocalDate dayOfWeekParser(String dateString) {
        Matcher m = dayOfWeekFormat.matcher(dateString);
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

    private static LocalDate getNearestDate(DayOfWeek day) {
        LocalDate currDay = LocalDate.now();
        return currDay.datesUntil(currDay.plusWeeks(1))
                .filter(date -> date.getDayOfWeek() == day)
                .findFirst()
                .get(); // We are sure that within 1 week we will find the given day
    }

    private static final Pattern offsetFormat =
            Pattern.compile("(?<number>\\d+) (?<period>day|week|month|year)s?");

    private static LocalDate offsetParser(String dateString) {
        Matcher m = offsetFormat.matcher(dateString);
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

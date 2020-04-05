package sharadhr.duke.parse;

import sharadhr.duke.exception.DukeInvalidDateTimeException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Locale;
import java.util.Optional;

/**
 * A utility class to parse {@link String}s into {@link ZonedDateTime} objects,
 * and format
 * vice-versa, useable by the Duke program.
 */
public final class DateParser {
    private static final DateTimeFormatter DAY_FORMAT_PATTERN = DateTimeFormatter
        .ofPattern("[EEEE][E]").withResolverFields(ChronoField.DAY_OF_WEEK);
    private static final DateTimeFormatter DATE_FORMAT_PATTERN = DateTimeFormatter.ofPattern(
        "[dd][d]['st']['nd']['rd']['th'][' of'][/][-][ ][LLLL][MMM][L][, ][/][-][ ][uuuu]")
                                                                                  .withResolverFields(
                                                                                      ChronoField.DAY_OF_MONTH,
                                                                                      ChronoField.MONTH_OF_YEAR,
                                                                                      ChronoField.YEAR);
    private static final DateTimeFormatter TIME_FORMAT_PATTERN = DateTimeFormatter
        .ofPattern("[kk[[:][.]mm]][[hh][h][[:][.]mm][ ]a][ v]")
        .withResolverFields(ChronoField.HOUR_OF_DAY, ChronoField.CLOCK_HOUR_OF_AMPM,
                            ChronoField.MINUTE_OF_HOUR, ChronoField.AMPM_OF_DAY,
                            ChronoField.OFFSET_SECONDS);
    private static final DateTimeFormatter[] FORMATTERS = { DAY_FORMAT_PATTERN, DATE_FORMAT_PATTERN,
        TIME_FORMAT_PATTERN };

    private static final DateTimeFormatter OUTPUT_FORMAT_PATTERN = DateTimeFormatter
        .ofPattern("EEEE dd MMMM YYYY hh:mm a v").withLocale(Locale.ENGLISH)
        .withZone(java.time.ZoneId.systemDefault()).withResolverFields(ChronoField.values());

    private static final ZonedDateTime now = ZonedDateTime.now();

    /**
     * Returns a {@link java.time.ZonedDateTime} after parsing
     * {@code dateTimeString}.
     *
     * @param dateTimeString the {@link java.lang.String} to be parsed
     * @return the date-time object after parsing the string
     * @throws DukeInvalidDateTimeException if {@code dateTimeString} cannot be
     *                                      parsed
     */
    public static ZonedDateTime parseDateTimeString(String dateTimeString)
        throws DukeInvalidDateTimeException {
        Optional<ZonedDateTime> returnable = Optional.empty();

        for (DateTimeFormatter formatter : FORMATTERS) {
            for (String token : dateTimeString.split("\\s+")) {
                returnable = parseStringWithFormatter(token, formatter, returnable);
            }
        }
        return returnable.orElseThrow(
            () -> new DukeInvalidDateTimeException(DateParser.class.getName(), dateTimeString));
    }

    /**
     * Returns a {@link String} from the provided {@code dateTimeGroup}, as
     * specified by a
     * formatter in this class. The formatter is provided with the pattern:
     * {@code "EEEE dd MMMM
     * YYYY hh:mm a v"}.
     *
     * @param dateTimeGroup the {@link ZonedDateTime} to be formatted
     * @return A {@link String} of the formatted date time group
     */
    public static String formatZonedDateTime(ZonedDateTime dateTimeGroup) {
        return OUTPUT_FORMAT_PATTERN.format(dateTimeGroup);
    }

    /**
     * Returns an encoded {@link String} to be written to the data file, by
     * formatting {@code dateTimeGroup}.
     *
     * @param dateTimeGroup The {@link ZonedDateTime} date-time-group to be
     *                      formatted
     * @return The formatted string
     */
    public static String encodeZonedDateTime(ZonedDateTime dateTimeGroup) {
        return DateTimeFormatter.ISO_INSTANT.format(dateTimeGroup);
    }

    /**
     * Returns a decoded {@link ZonedDateTime} from the given {@link String}
     * {@code input}, using the {@link DateTimeFormatter#ISO_INSTANT} parser.
     *
     * @param encoded A previously-encoded {@link String}, usually from the data
     *                file
     * @return the {@link ZonedDateTime} instant corresponding to the parsed string
     */
    public static ZonedDateTime decodeString(String encoded) {
        return DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(encoded, ZonedDateTime::from);
    }

    private static Optional<ZonedDateTime> parseStringWithFormatter(String string,
                                                                    DateTimeFormatter formatter,
                                                                    Optional<ZonedDateTime> possibleZDT)
        throws DukeInvalidDateTimeException {
        Optional<TemporalAccessor> possiblyParsed;

        try {
            possiblyParsed = Optional.of(formatter.parse(string));
        }
        catch (DateTimeParseException e) {
            possiblyParsed = Optional.empty();
        }
        if (possiblyParsed.isPresent()) {
            ZonedDateTime returnable = possibleZDT.orElse(now);
            TemporalAccessor parsed = possiblyParsed.get();
            for (TemporalField field : formatter.getResolverFields()) {
                returnable = returnable
                    .with(field, parsed.isSupported(field) ? parsed.get(field) : now.get(field));
            }
            return Optional.of(returnable);
        } else {
            return possibleZDT;
        }
    }
}
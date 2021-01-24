import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * Deals with the parsing of LocalDateTime objects to and from strings.
 */
public class Parser {
    /**
     * Parses LocalDateTime to or from text file into proper format.
     * @param input Input string to be parsed
     * @return LocalDateTime object from parsed string
     */
    public static LocalDateTime parseDateTime(String input) {
        Pattern patternDateTime = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s+\\d{4}");
        Pattern patternDate = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

        if (patternDateTime.matcher(input).find()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(input, formatter);
        } else if (patternDate.matcher(input).find()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            return LocalDate.parse(input, formatter).atStartOfDay();
        }
        throw new UnsupportedOperationException("Supplied string is not of proper format.");
    }
}

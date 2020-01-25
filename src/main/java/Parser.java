import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM uuuu");

    public static LocalDate dateParser(String str) throws BadDateException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM uu");
            return LocalDate.parse(str, formatter);
        } catch (DateTimeParseException e) {
            throw new BadDateException("Bad date format");
        }
    }
}

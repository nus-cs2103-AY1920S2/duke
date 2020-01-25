import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM uuuu");

    public static LocalDate dateParser(String str) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM uu");
            return LocalDate.parse(str, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Sorry I don't recognise this date format!\n" +
                    "Please make sure the format is: dd mm yy");
            return null;
        }
    }
}

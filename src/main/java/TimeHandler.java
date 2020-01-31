import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class TimeHandler {
    private static SimpleDateFormat myDate = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Returns an Optional of a Date object if the dateString is in the correct format of yyyy-MM-dd
     * Else returns Optional.empty
     * @param dateString
     * @return
     */
    public static Optional<Date> dateFromString(String dateString) {
        try{
            Date t = myDate.parse(dateString);
            return Optional.of(t);
        }catch (ParseException e) {

        }

        return Optional.empty();

    }
}

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class TimeHandler {
    private static SimpleDateFormat myDate = new SimpleDateFormat("yyyy-MM-dd");

    public static Optional<Date> dateFromString(String dateString) {
        try{
            Date t = myDate.parse(dateString);
            return Optional.of(t);
        }catch (ParseException e) {

        }

        return Optional.empty();

    }
}

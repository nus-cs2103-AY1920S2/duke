import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Datetime {

    String dateTime;

    public Datetime(String dateTime) {
        this.dateTime = dateTime;
    }

    public static String formatDateTime(String dateTime) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd hh-mm");
        Date inputDate = inputFormat.parse(dateTime);
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm", Locale.ENGLISH);
        String date = myFormat.format(inputDate) ;
        return date;
    }

    @Override
    public String toString() {
        String result = null;
        try {
            result = formatDateTime(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}

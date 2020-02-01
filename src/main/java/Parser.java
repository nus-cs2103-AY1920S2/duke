import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

    public static Date stringToDate(String text) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_1);
        try {
            date = formatter.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static boolean checkDateFormat(String text) {
        String[] tokens = text.split("-");
        if (tokens[0].length() != 4) {
            return false;
        }
        if (tokens[1].length() != 2 || tokens[1].compareTo("00") <= 0 || tokens[1].compareTo("12") > 0) {
            return false;
        }
        if (tokens[2].length() != 2 || tokens[2].compareTo("00") <= 0 || tokens[2].compareTo("31") > 0) {
            return false;
        }
        return true;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_2);
        String text = formatter.format(date);
        return text;
    }
}

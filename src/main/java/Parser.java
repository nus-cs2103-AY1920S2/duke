import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public Parser() {

    }

    public boolean parseCommand(String input, String command) {
        if (input.compareTo(command) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String parseTime(String time) {
        String result = "";
        String dateRegex = "\\d{4}-\\d{2}-\\d{2}";
        String timeRegex = "([0-1][0-9]|2[0-3])[0-5][0-9]";
        String[] split = time.split(" ");
        for (String str : split) {
            Boolean dateMatches = str.matches(dateRegex);
            Boolean timeMatches = str.matches(timeRegex);
            if (dateMatches) {
                result += reformatDate(str) + " ";
            } else if (timeMatches) {
                result += reformatTime(str) + " ";
            } else {
                result += str + " ";
            }
        }
        return result.trim();
    }

    private static String reformatDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        String result = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return result;
    }

    private static String reformatTime(String time) {
        String result = "";
        int hour = Integer.parseInt(time.substring(0,2));
        String minutes = time.substring(2);
        if (hour <= 12) {
            result += hour;
            result += ":" + minutes;
        } else {
            result += hour - 12;
            result += ":" + minutes;
        }

        result += hour < 12 ? "am" : "pm";
        return result;
    } 
}
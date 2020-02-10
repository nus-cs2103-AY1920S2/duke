package duke.temporal;

public class DateTimeParser {
    /**
     * Take in the raw date and time and returns a date string parsable by LocalDate.
     *
     * @param rawDateTime Raw date and time string of format dd/MM/yyyy hhmm.
     * @return date string parsable by LocalDate.
     */
    public static String getParsableDate(String rawDateTime) {
        String[] separatedDateTime = rawDateTime.split(" ");
        String[] date = separatedDateTime[0].split("/");
        if (date[0].length() < 2) {
            date[0] = "0" + date[0];
        }
        if (date[1].length() < 2) {
            date[1] = "0" + date[1];
        }
        String formattedDate = date[2] + "-" + date[1] + "-" + date[0];
        return formattedDate;
    }

    /**
     * Takes in the raw date and time and returns a time string parsable by LocalTime.
     *
     * @param rawDateTime Raw date and time string of format dd/MM/yyyy hhmm.
     * @return time string parsable by LocalTime.
     */
    public static String getParsableTime(String rawDateTime) {
        String[] separatedDateTime = rawDateTime.split(" ");
        String hour = separatedDateTime[1].substring(0, 2);
        if (hour.equals("24")) {
            hour = "00";
        }
        String minute = separatedDateTime[1].substring(2, 4);
        String formattedTime = hour + ":" + minute + ":00";
        return formattedTime;
    }
}

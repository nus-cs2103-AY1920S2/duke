package resources;
/**
 * The class Resources.
 * DateTime creates LocalDateTime objects for functions
 */
import java.time.LocalDateTime;

public class DateTime {

    private static LocalDateTime date;

    /**
     * Prints the time now.
     */
    public static void printNow() {
        date = LocalDateTime.now();
        System.out.println("Today is " + date);
    }

    /**
     * Gets date time local date time.
     *
     * @param date the date
     * @param time the time
     * @return the local date time
     */
    public static LocalDateTime getDateTime(String date, String time) {
        String[] dataArr = date.split("/");
        int year = Integer.parseInt(dataArr[2]);
        int month = Integer.parseInt(dataArr[1]);
        int day = Integer.parseInt(dataArr[0]);

        int timeConverted = Integer.parseInt(time);
        int hour = timeConverted / 100;
        int mins = timeConverted % 100;
        return LocalDateTime.of(year, month, day, hour, mins);
    }

    /**
     * Gets date local date time.
     *
     * @param date the date
     * @return the local date time
     */
    public static LocalDateTime getDate(String date) {
        String[] dataArr = date.split("/");
        int year = Integer.parseInt(dataArr[2]);
        int month = Integer.parseInt(dataArr[1]);
        int day = Integer.parseInt(dataArr[0]);

        return LocalDateTime.of(year, month, day, 0, 0, 0, 0);
    }

    /**
     * Gets time local date time.
     *
     * @param time the time
     * @return the local date time
     */
    public static LocalDateTime getTime(String time) {
        String[] dataArr = time.split("/");
        int timeConverted = Integer.parseInt(time);
        int hour = timeConverted / 100;
        int mins = timeConverted % 100;

        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        int day = LocalDateTime.now().getDayOfMonth();

        return LocalDateTime.of(year, month, day, hour, mins);
    }

}

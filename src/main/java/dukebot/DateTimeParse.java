package dukebot;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Scanner;

public class DateTimeParse {
    private static final DateTimeFormatter[] formats = {
            new DateTimeFormatterBuilder()
                    .appendPattern("yyyy/MM/dd")
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .toFormatter(),
            new DateTimeFormatterBuilder()
                    .appendPattern("dd/MM/yyyy")
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .toFormatter(),
            new DateTimeFormatterBuilder()
                    .appendPattern("dd/MM")
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.YEAR, ZonedDateTime.now().getYear())
                    .toFormatter(),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            new DateTimeFormatterBuilder()
                    .appendPattern("dd/MM HHmm")
                    .parseDefaulting(ChronoField.YEAR, ZonedDateTime.now().getYear())
                    .toFormatter(),
            new DateTimeFormatterBuilder()
                    .appendPattern("HHmm")
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, ZonedDateTime.now().getDayOfMonth())
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, ZonedDateTime.now().getMonthValue())
                    .parseDefaulting(ChronoField.YEAR, ZonedDateTime.now().getYear())
                    .toFormatter(),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")
    };
    //    private static final DateTimeFormatter format_ddMMyyyy = DateTimeFormatter.ofPattern("dd MM yyyy");
    //    private static final DateTimeFormatter format_ddMMyyyyHHmm = DateTimeFormatter.ofPattern("dd MM yyyy HHmm");
    //    private static final DateTimeFormatter format_yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyy MM dd HHmm");

    public static LocalDateTime parseDate(String dateTime) throws DateTimeParseException {
        dateTime = dateTime.replaceAll("[\\\\/\\-]+", "/");
        LocalDateTime parsedDate = null;
        boolean isFound = false;
        for (DateTimeFormatter dateFormat: formats) {
            try {
                parsedDate = LocalDateTime.parse(dateTime, dateFormat);
                isFound = true;
                break;
            } catch (DateTimeParseException e) {
                // System.out.println(e);
            }
        }
        if (isFound) {
            // System.out.println(parsedDate);
            return parsedDate;
        } else {
            // This throws a DateTimeParseException
            return LocalDateTime.parse(dateTime, formats[3]);
        }
    }

    //    public static void main(String[] args) {
    //        System.out.println("\nMaster: ");
    //        while (true) {
    //            Scanner sc = new Scanner(System.in);
    //            String inp = sc.nextLine();
    //            parseDate(inp);
    //        }
    //    }
}
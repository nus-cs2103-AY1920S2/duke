package duke.util;

import java.time.LocalDate;

public class DateUtil {
    /**
     * Formats the date in the accepted format.
     *
     * @param dt the datetime
     */
    public static LocalDate formatDate(String dt) {
        assert (!dt.isEmpty());
        dt = dt.replaceAll("\\s", "");
        try {
            java.time.format.DateTimeFormatter formatter
                    = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return java.time.LocalDate.parse(dt, formatter);
        } catch (Exception ex) {
            try {
                java.time.format.DateTimeFormatter formatter
                        = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy hhmm");
                return java.time.LocalDate.parse(dt, formatter);
            } catch (Exception ex2) {
                System.out.println();
            }

        }
        return LocalDate.now();
    }
}

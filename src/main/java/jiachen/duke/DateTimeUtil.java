package jiachen.duke;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;

/**
 * Singleton Datetime helper.
 * Helps parse natual dates via a simple lookup table
 */
public class DateTimeUtil {
    /**
     * This is the format input dates should come int. eg 24/07/1996 1200
     */
    public static DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static DateTimeFormatter EVENT_FORMATTER = DateTimeFormatter.ofPattern("MMM d YYYY");
    private static DateTimeUtil _instance = null;
    private HashMap<String, DayOfWeek> aliasLookupTable = new HashMap<String, DayOfWeek>();

    private DateTimeUtil() {
        aliasLookupTable.put("monday", DayOfWeek.MONDAY);
        aliasLookupTable.put("mon", DayOfWeek.MONDAY);
        aliasLookupTable.put("tuesday", DayOfWeek.TUESDAY);
        aliasLookupTable.put("tue", DayOfWeek.TUESDAY);
        aliasLookupTable.put("wednesday", DayOfWeek.WEDNESDAY);
        aliasLookupTable.put("wed", DayOfWeek.WEDNESDAY);
        aliasLookupTable.put("thursday", DayOfWeek.THURSDAY);
        aliasLookupTable.put("thur", DayOfWeek.THURSDAY);
        aliasLookupTable.put("friday", DayOfWeek.FRIDAY);
        aliasLookupTable.put("fri", DayOfWeek.FRIDAY);
        aliasLookupTable.put("saturday", DayOfWeek.SATURDAY);
        aliasLookupTable.put("sat", DayOfWeek.SATURDAY);
        aliasLookupTable.put("sunday", DayOfWeek.SUNDAY);
        aliasLookupTable.put("sun", DayOfWeek.SUNDAY);
    }

    /**
     * Get instance date time util.
     *
     * @return the date time util
     */
    public static DateTimeUtil getInstance() {
        if (_instance == null) {
            return new DateTimeUtil();
        }
        return _instance;
    }

    /**
     * Check if date string is a natual date.
     *
     * @param date as string
     * @return return true if date string is a natural date
     */
    public boolean isNatualDate(String date) {
        return aliasLookupTable.containsKey(date.toLowerCase());
    }

    /**
     * Convert from natual date string.
     * returns empty string if not natual date
     * caller to first check if string is a natual date via isNatualDate method
     *
     * @param timestamp in natual format
     * @return date string formmated using the input format
     */
    public String convertFromNatualDate(String timestamp) {
        timestamp = timestamp.toLowerCase();
        if (!isNatualDate(timestamp)) {
            return "";
        }
        DayOfWeek day = aliasLookupTable.get(timestamp);
        LocalDateTime now = LocalDateTime.now();
        now = now.with(TemporalAdjusters.next(day));
        return now.format(INPUT_FORMATTER);
    }
}

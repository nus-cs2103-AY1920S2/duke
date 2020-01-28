package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Stores all the different format for the date and time input and output.
 */
public enum DateTimeUtil {

    FORMAT_1("yyyy-MM-dd HH-mm"),
    FORMAT_2("yyyy/MM/dd HH/mm"),
    FORMAT_3("yyyy/MM/dd HH:mm"),
    FORMAT_4("yyyy\\MM\\dd HH:mm"),
    FORMAT_5("yyyy\\MM\\dd HH-mm");

    String string;

    DateTimeUtil(String string){
        this.string=string;
    }

    /**
     * convert the date and time to standard format
     * @param time date and time
     * @return the standard format of date and time in string
     */
    public static String standardFormat(LocalDateTime time){
        return time.format(DateTimeFormatter.ofPattern(FORMAT_1.toString()));
    }

    @Override
    public String toString() {
        return string;
    }
}

package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Stores all the different format for the date input and output.
 */
public enum DateTimeUtil {

    FORMAT_1("yyyy-MM-dd"),
    FORMAT_2("yyyy/MM/dd"),
    FORMAT_3("yyyy/MM/dd"),
    FORMAT_4("yyyy\\MM\\dd"),
    FORMAT_5("yyyy\\MM\\dd");

    String string;

    DateTimeUtil(String string){
        this.string=string;
    }

    /**
     * convert the date to standard format
     * @param time date
     * @return the standard format of date in string
     */
    public static String standardFormat(LocalDate time){
        return time.format(DateTimeFormatter.ofPattern(FORMAT_1.toString()));
    }

    @Override
    public String toString() {
        return string;
    }
}

package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Stores all the different format for the date input and output.
 */
public enum DateUtil {

    FORMAT_1("yyyy-MM-dd"),
    FORMAT_2("yyyy/MM/dd"),
    FORMAT_3("yyyy/MMM/dd"),
    FORMAT_4("yyyy\\MM\\dd"),
    FORMAT_5("yyyyMMdd"),
    FORMAT_6("MMM-dd-yyyy"),
    FORMAT_7("yy-MM-dd");

    String string;

    DateUtil(String string){
        this.string=string;
    }

    /**
     * convert the date to standard format
     * @param time date
     * @return the standard format of date in string
     */
    public static String standardFormat(LocalDate time){
        return time.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String toString() {
        return string;
    }
}

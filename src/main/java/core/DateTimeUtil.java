package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static String standardFormat(LocalDateTime time){
        return time.format(DateTimeFormatter.ofPattern(FORMAT_1.toString()));
    }

    @Override
    public String toString() {
        return string;
    }
}

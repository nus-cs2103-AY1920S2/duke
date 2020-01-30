package Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DukeDate {

    LocalDate date;
    String dateString;

    public DukeDate(LocalDate date){
        this.date = date;
        this.dateString = convertToString(date);
    }

    public DukeDate(String dateString){
        this.date = convertToDate(dateString);
        this.dateString = dateString;
    }

    public static LocalDate convertToDate(String dateString){
        return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String convertToString(LocalDate date){
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }

    public LocalDate getDate(){
        return this.date;
    }

    public String getDateString(){
        return this.dateString;
    }

}

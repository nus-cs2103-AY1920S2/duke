package Backend.Parsers;

import Backend.Constants.DateFormats;
import Backend.Exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class DateParser {

    private LocalDate date;
    private String dateString;

    /**
     * Creates a DateParser object given a LocalDate
     * @param date - LocalDate object
     */
    public DateParser( LocalDate date ) {
        this.date = date;
        this.dateString = convertToString( date );
    }

    /**
     * Creates a DateParser object given a string. This reduces the need for repeated conversion.
     * @param dateString string in format YYYY-MM-DD
     */
    public DateParser( String dateString ) throws DukeException{
        this.date = convertToDate( dateString );
        this.dateString = dateString;
    }

    /**
     * Converts a string to LocalDate object
     * @param dateString string in format YYYY-MM-DD
     * @return LocalDate object
     */
    public static LocalDate convertToDate(String dateString) throws DukeException {

        try{
            return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch ( DateTimeParseException e ){
            throw new DukeException( e );
        }
    }

    /**
     * Converts LocalDate object to String
     * @param date LocalDate object
     * @return string in format YYYY-MM-DD
     */
    public static String convertToString(LocalDate date){
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }

    /**
     * Gets LocalDate object
     * @return LocalDate object
     */
    public LocalDate getDate(){
        return this.date;
    }

    /**
     * Gets date string
     * @return string in format YYYY-MM-DD
     */
    public String getDateString(){
        return this.dateString;
    }

    /**
     * Gets the timeOfDay enum depending on the LocalDateTime.
     * Takes the current time and compares it with two other times set as NOON and EVENING.
     * If current time is before NOON, it is MORNING.
     * If current time is after EVENING it is NIGHT.
     * Otherwise, it is AFTERNOON.
     * @return enum timeOfDay
     */
    public static TimeOfDay getTimeOfDay(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( DateFormats.DATE_TIME_FORMAT );

        LocalDateTime now = LocalDateTime.now();
        String nowString = now.format(formatter);
        String dateString = nowString.split(" ")[0];
        LocalDateTime noon = LocalDateTime.parse(dateString + " " + DateFormats.NOON_STRING, formatter);
        LocalDateTime night = LocalDateTime.parse( dateString + " " + DateFormats.EVENING_STRING, formatter);

        assert( noon.isBefore( night ));

        if( now.isBefore( noon )){
            return TimeOfDay.MORNING;
        } else if ( now.isAfter( night ) ){
            return TimeOfDay.EVENING;
        } else {
            return TimeOfDay.AFTERNOON;
        }

    }

}

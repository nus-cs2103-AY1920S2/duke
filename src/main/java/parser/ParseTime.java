package parser;

import data.exceptions.ParseException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseTime {

    /**
     * Parses user input into command for execution.
     *
     * @param timeString time YYYY-MM-DD in string format
     * @return Date object
     */
    public static LocalDate parseStringToLocalTime(String timeString) {
        Pattern pattern = Pattern.compile("[0-9][0-9][0-9][0-9]-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[01])");
        timeString = timeString.substring(3);
        Matcher matcher = pattern.matcher(timeString);
        LocalDate localDate = null;
        try {
            // check if format is correct
            if (!matcher.matches()){
                throw new ParseException("Parse Error");
            } else {
                //parse
                String str [] = timeString.split("-");
                localDate = LocalDate.of(Integer.parseInt(str[0]),
                        Integer.parseInt(str[1]),
                        Integer.parseInt(str[2]));
            }
        } catch (ParseException pe) {
            System.out.println("Warning: The input date can not be understood!");
        }
        return localDate;
    }
}

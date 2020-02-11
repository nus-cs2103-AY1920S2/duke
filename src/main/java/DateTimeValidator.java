import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * DateTimeValidator Class.
 * The class main role is to validate the input date and time
 * given by the user. Date must be in the format of yyyy-mm-dd and
 * time must be in 24-hour format. Any deviations from this will result
 * an exception being thrown
 *
 * @author Amos Cheong
 */
public class DateTimeValidator {

    /**
     * Only checks the date input by user. Checks if the date complies with
     * the Duke requirements and ensure it is not empty.
     *
     * @param date Input date in the format of yyyy-mm-dd.
     * @return String The formatted date to user, translating yyyy-mm-dd to Day Month and Year.
     * @throws DukeException Thrown when there is no date input by user.
     * @throws DateTimeParseException Thrown when input date does not follow this format : yyyy-mm-dd.
     */
    public String DateValidator(String date) throws DukeException, DateTimeParseException {
        DateTimeFormatter formatdate = DateTimeFormatter.ofPattern("uuuu-MM-dd");

        if (date.equals("")) {
            throw new DukeException("Please enter date and time!");
        }

        // Translating input date to the form of "MM d yyyy"
        String[] inputdate = date.split("\\s+", 2);

        LocalDate localdate = LocalDate.parse(inputdate[0],
                formatdate.withResolverStyle(ResolverStyle.STRICT));

        // Return formatted date to user.
        // For example, from 2019-10-3 to 3 Oct 2019
        return localdate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     *  Checks if the input time is in 24-hour format.
     *
     * @param time Time variable to be validated.
     * @return boolean Returns true if is in 24-hour format and return false otherwise.
     */
    public boolean isTwentyFourHourFormat(String time) {
        return (time.toCharArray()).length != 4;
    }

    /**
     *
     * @param minutes The minutes of the 24-hour time format.
     * @param fullTime The time given by the user.
     * @return boolean Returns true if within 24-hour time range and return false otherwise.
     */
    public boolean isValidTime(int minutes, int fullTime) {
        return (minutes > 60 || minutes < 0) || (fullTime > 2359 || fullTime < 0);
    }
}

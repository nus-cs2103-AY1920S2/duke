import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Custom class that represents time of a Deadline
 * Task or Event Task
 */
public class PrettyTime {
    private final Optional<String> rawText;
    private final Optional<LocalDate> date;

    /**
     * Constructor for a PrettyTime, using a
     * String with date in this format:
     * DD-MM-YYYY, e.g. 5-12-2020 to refer
     * to 5th December 2020
     *
     * @param s The String in DD-MM-YYYY format
     */
    public PrettyTime(String s) {
        String[] split = s.split("-");
        boolean splitSuccessful = true;
        String date = "";
        String month = "";
        String year = "";
        try {
            date = split[0].length() == 2 ? split[0] : "0" + split[0];
            month = split[1].length() == 2 ? split[1] : "0" + split[1];
            year = split[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            splitSuccessful = false;
        }

        LocalDate parsedDate = null;
        boolean parseSuccessful = true;
        if (splitSuccessful) {
            try {
                parsedDate = LocalDate.parse(year + "-" + month + "-" + date);
            } catch (DateTimeParseException e) {
                parseSuccessful = false;
            }
        }

        if (splitSuccessful && parseSuccessful) {
            this.date = Optional.of(parsedDate);
            this.rawText = Optional.empty();
        } else {
            this.rawText = Optional.of(s);
            this.date = Optional.empty();
        }
    }

    /**
     * Checks whether this PrettyDate has a LocalDate
     * representation.
     *
     * @return True, if there exists the LocalDate
     * representation.
     */
    public boolean hasLocalDate() {
        return this.date.isPresent();
    }

    @Override
    public String toString() {
        return this.date.map(
                date -> date.format(DateTimeFormatter.ofPattern("d MMMM y"))
        ).orElseGet(
                () -> this.rawText.orElse("")
        );
    }
}

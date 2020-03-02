package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A task that has a time attribute that it occurs at.
 */
class EventTask extends Task {
    protected LocalDateTime time;

    /**
     * Constructs an EventTask object.
     *
     * @param inputArr a String array that represents the input.
     * @throws Exception if there is not enough information or wrong format is given.
     */
    public EventTask(String[] inputArr) throws Exception {
        this.type = "event";
        if (inputArr.length < 2) {
            throw new Exception(" Event tasks must have a non-empty description!");
        }
        this.description = Arrays.stream(inputArr)
                .map(str -> str.toLowerCase().equals("event") ? "" : str.equals("/at") ? "at" : str)
                .collect(Collectors.joining(" "));

        int lastAt = description.lastIndexOf(" at ");
        if (lastAt == -1) {
            if (description.contains("at")) {
                throw new Exception((" Date & time missing."));
            }
            throw new Exception(" Keyword \"at\" missing.");
        } else {
            /*
                The second try clause handles when a task is read from data.csv, wherein its date format is
                MMM d yyyy hh:mma
             */
            try {
                this.time = LocalDateTime.parse(this.description.substring(lastAt + 4),
                        DateTimeFormatter.ofPattern("yyyy-LL-dd HHmm"));
            } catch (DateTimeParseException e) {
                try {
                    this.time = LocalDateTime.parse(this.description.substring(lastAt + 4),
                            DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
                } catch (DateTimeParseException e2) {
                    throw new Exception(" Error: unable to decipher date & time input.");
                }
            }
            this.description = this.description.substring(0, lastAt + 3) + ' '
                    + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));

        }
    }

    /**
     * Returns the String representation of this EventTask object.
     *
     * @return the String representation of this EventTask object.
     */
    @Override
    public String toString() {
        return " EVENT      :" + super.toString();
    }
}
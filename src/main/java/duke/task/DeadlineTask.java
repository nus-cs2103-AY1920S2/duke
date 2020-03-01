package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A class that represents a task with a deadline.
 */
class DeadlineTask extends Task {
    protected LocalDateTime time;

    /**
     * Constructs an DeadlineTask object.
     *
     * @param inputArr a String array that represents the input.
     * @throws Exception if there is not enough information or wrong format is given.
     */
    public DeadlineTask(String[] inputArr) throws Exception {
        this.type = "deadline";
        if (inputArr.length < 2) {
            throw new Exception(" Deadline tasks must have a non-empty description!");
        }
        this.description = Arrays.stream(inputArr)
                .map(str -> str.toLowerCase().equals("deadline") ? "" : str.equals("/by") ? "by" : str)
                .collect(Collectors.joining(" "));
        int lastBy = description.lastIndexOf(" by ");
        if (lastBy == -1) {
            throw new Exception(" Keyword \"by\" missing");
        } else {
            /*
                The second try clause handles when a task is read from data.csv, wherein its date format is
                MMM d yyyy hh:mma
             */
            try {
                this.time = LocalDateTime.parse(this.description.substring(lastBy + 4),
                        DateTimeFormatter.ofPattern("yyyy-LL-dd HHmm"));

            } catch (DateTimeParseException e) {
                try {
                    this.time = LocalDateTime.parse(this.description.substring(lastBy + 4),
                            DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
                } catch (DateTimeParseException e2) {
                    throw new Exception(" Error: unable to decipher date & time input.");
                }
            }
            this.description = this.description.substring(0, lastBy + 3) + ' '
                    + time.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
        }
    }

    /**
     * Returns the String representation of this DeadlineTask object.
     *
     * @return the String representation of this DeadlineTask object.
     */
    @Override
    public String toString() {
        return " DEADLINE:" + super.toString();
    }
}

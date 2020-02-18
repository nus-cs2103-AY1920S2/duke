package duke.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The InvalidDateError program is an error thrown when user inputs an invalid date.
 *
 * @version 1.2
 * @since 17/2/2020
 */
public class InvalidDateError extends Exceptions {

    String issue;
    public InvalidDateError(String type, String issue) {

        super(type);
        this.issue = issue;

    }


    /**
     * String representation of the exception.
     *
     * @return error message.
     */
    @Override
    public String toString() {

        if (issue.equals("Date")) {

            if (type.equals("deadline")) {

                return "Date should be after " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm"));

            } else {

                assert type.equals("event") : "Wrong event type";

                return "Start date should be after " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm"))
                        + " and end date should be after start date";

            }

        } else {

            if (type.equals("deadline")) {

                return "Date should be in d/MM/yyyy HH:mm format";

            } else {

                assert type.equals("event") : "Wrong event type";

                return "Date should be in d/MM/yyyy HH:mm to d/MM/yyyy HH:mm format";

            }
        }
    }
}

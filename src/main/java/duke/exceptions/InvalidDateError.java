package duke.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The InvalidDateError program is an error thrown when user inputs an invalid date.
 *
 * @version 1.3
 * @since 19/2/2020
 */
public class InvalidDateError extends Exceptions {

    String issue;

    /**
     * Constructor.
     *
     * @param type refers to the type of task.
     * @param issue refers to the reason date input is wrong.
     */
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

            if (type.equals("D")) {

                return "Date should be after "
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm"));

            } else {

                assert type.equals("E") : "Wrong task type!";

                return "End date should be after "
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm"))
                        + " and start date";

            }

        } else {

            if (type.equals("D")) {

                return "Date should be in /by d/MM/yyyy HH:mm format";

            } else {

                assert type.equals("E") : "Wrong task type";

                return "Date should be in /at d/MM/yyyy HH:mm to d/MM/yyyy HH:mm format";

            }
        }
    }
}

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    private String[] inputs;

    /**
     * Class constructor.
     *
     * @param input User input to be interpreted.
     */
    public Parser(String input) {
        this.inputs = input.split(" ");
    }

    /**
     * Returns command of user input.
     *
     * @return Command of user input.
     */
    public String getCommand() {
        return inputs[0];
    }

    /**
     * Return index of task to be deleted/marked as done.
     *
     * @return Index of task to be deleted/marked as done
     */
    public int getTaskIndex() {
        return Integer.valueOf(inputs[1]);
    }

    /**
     * Return description of task to be added.
     *
     * @return Description of task.
     * @throws DukeException Thrown when empty description is found.
     */
    public String getDescription() throws DukeException {
        if (inputs.length == 1) {
            throw new DukeException("\u2639" + " OOPS!!! The description of a "
                    + inputs[0] + " cannot be empty\n");
        }

        String description = inputs[1];

        if (inputs[0].equals("event") || inputs[0].equals("deadline")) {
            int j = 2;
            while (j != inputs.length && inputs[j].charAt(0) != '/') {
                description = description.concat(" " + inputs[j]);
                j++;
            }
        } else {
            for (int i = 2; i < inputs.length; i++) {
                description = description.concat(" " + inputs[i]);
            }
        }

        return description;
    }

    /**
     * Returns the index of the date in a 'deadline' or 'event' user command.
     *
     * @return Index of date.
     */
    public int getDateIndex() {
        int i = 2;

        while (i != inputs.length && inputs[i].charAt(0) != '/') {
            i++;
        }

        return i;
    }

    /**
     * Returns date of deadline/event.
     *
     * @return Date of deadline/event.
     * @throws DukeException Thrown when date is not found.
     */
    public LocalDate getDate() throws DukeException {
        int dateIndex = this.getDateIndex() + 1;

        if (dateIndex == (inputs.length + 1) || dateIndex == inputs.length) {
            throw new DukeException("\u2639" + " OOPS!!! This task requires a date\n");
        }

        LocalDate localDate = LocalDate.parse(inputs[dateIndex]);

        return localDate;
    }

    /**
     * Returns time of deadline/event.
     *
     * @return Time of deadline/event.
     * @throws DukeException Thrown when time is not found.
     */
    public LocalTime getTime() throws DukeException {
        int timeIndex = this.getDateIndex() + 2;

        if (timeIndex == inputs.length) {
            throw new DukeException("\u2639" + " OOPS!!! This task requires a timing\n");
        }

        LocalTime timing = LocalTime.parse(inputs[timeIndex]);

        return timing;
    }
}

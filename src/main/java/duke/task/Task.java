package duke.task;

import duke.DaysOfWeek;
import duke.DukeException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Task which handles all forms of Tasks
 *
 */
public class Task {

    /**
     * @param d1 Formatted date and time which is being passed by the user
     */
    LocalDateTime d1;
    private String description;
    private boolean isDone;
    private DaysOfWeek dm = new DaysOfWeek();
    private String[] days;
    private boolean isDay;
    // To check if there is time inclusive in the command a not
    private boolean hasTime;
    private Deadline_event_hash deadline_event_hash;


    /**
     * Instantiates a new Task.
     *
     * @param description the description
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * From the input given by the user, filter out the commands (TO-DO/Deadline/Event)
     * And returns the description of the string.
     *
     * @param s the s
     * @return the string
     * @throws DukeException the duke exception
     */
    public String format_tasks(String s) throws DukeException {
        return getDescription().substring(getDescription().indexOf(s)).
                replaceAll(s + " ", "");
    }

    /**
     * Gets status icon. Either the tick or the cross icons.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task as complete
     *
     * @param done the done
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Sets the description
     *
     * @param s the s
     * @throws DukeException the duke exception
     */
    public void setDescription(String s) throws DukeException {
        this.description = s;
    }

    /**
     * Sets the at or by fields for deadline and event class respectively.
     * Mainly for the date and time of the task (Deadline/event) being held.
     * Returns the String of the date and time which is then processed by LocalDateTime
     *
     * @param s the s
     * @return the by at
     */
// Main method to set the At and By for deadline and duke.task.Event classes
    // After the "../at and ../by methods for deadline and event classes
    String set_by_at(String s) {
        StringBuilder returned = new StringBuilder();
        String[] splitted_string = s.split("/");
        String day_of_week_in_string = splitted_string[0];
        days = day_of_week_in_string.split(" ");
        if (this.dm.getHm().containsKey(days[0].toUpperCase())) {
            isDay = true;
        }

        // If its a date. Not a day.

        if (!isDay) {
            // If the user enters "2" instead or "02", this changes it to "02"

            // Means if the splitted string has time. Eg: "2/12/2019 1800"
            for (int j = 0; j < 2; j++) {
                if (splitted_string[j].length() != 2) {
                    splitted_string[j] = ("0" + splitted_string[j]);
                }
            }

            for (int i = 0; i < splitted_string.length - 1; i++) {
                returned.append(splitted_string[i]).append("/");
            }


            // Check if the time component is inside
            // if its include splitted_string[2] should be 2/12/[2019 1800]
            if (splitted_string[2].length() > 4) {
                hasTime = true;
                returned.append(splitted_string[2]);
            } else {
                returned.append(splitted_string[splitted_string.length - 1]);
            }
        }


        // Means theres a day component included. Eg :Monday/Tuesday
        else {

            // Means theres a time component
            if(days.length > 1) {
                hasTime = true;
            } else {
                returned = new StringBuilder(day_of_week_in_string);

            }
        }
        return returned.toString();
    }

    /**
     * Sets the string passed by set_by_at into a LocalDateTime format.
     *
     * @param at the at
     * @throws DukeException the duke exception
     */
// Formats the Date and time of the task. Into the datetimeformat
    void setD1(String at) throws DukeException {
        if (!isDay) {
            // Means the string is entered as "2/12/2019",
            // We format it so that the date becomes "2019-12-02"
            if (at.contains("/")) {
                // DateTimeFormatterw tells java what format is the input of
                // The date and time being entered.
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                TimeFormatter(at, dateFormat);

            } else if (at.contains("-")) {
                // Parse the string into the LocalDate class
                // Provided that the string is formatted as
                // "2019-10-15", "yyyy-mm-dd"
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                TimeFormatter(at, dateFormat);
            } else {
                throw new DukeException("Date is not in correct format!");
            }
        }

        // If it includes a day in the "/at Monday..."a
        else {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime current = LocalDateTime.now();
            DayOfWeek dayOfWeek = current.getDayOfWeek();
            int dayOfWeekIntValue = dayOfWeek.getValue();

            int date_difference = this.dm.getHm().get(days[0].toUpperCase()) - dayOfWeekIntValue;

            if (date_difference <= 0) {
                date_difference += 7;
            }

            LocalDateTime d2 = current.plusDays(date_difference);

            // d4 = date only. Formatted to dateFormat
            String d4 = d2.toLocalDate().toString();

            // If has specific time component, we set it.
            if (hasTime) {
                String day_and_time = days[1].substring(0, 2) + ':' + days[1].substring(2, 4);
                String d3 = d2.toLocalDate().toString() + " " + day_and_time;
                this.d1 = LocalDateTime.parse(d3, dateFormat);
            } else {
                this.d1 = LocalDateTime.parse(d4 + " 00:00", dateFormat);
            }
        }
    }

    // Between "-" date formats and "/" date formats. To include the ":" for time
    // Or to set to default the time to "00:00" if no time is given.
    private void TimeFormatter(String at, DateTimeFormatter dateFormat) {
        if(!hasTime) {
            this.d1 = LocalDateTime.parse(at + " 00:00" , dateFormat);
        }
        // If there is a time component
        else {
            //TODO Find a way not to force the substring values.
            String day_and_time = at.substring(11 , 13) + ':' + at.substring(13, 15);

            String[] split_date_from_time = at.split(" ");
            String date_only = split_date_from_time[0];
            this.d1 = LocalDateTime.parse(date_only + " " + day_and_time , dateFormat);
        }
    }


    @Override
    public String toString() {
        return " [" + this.getStatusIcon() + "] " + getDescription();
    }

    /**
     * The enum Task codes.
     */
// For whether its To-do(T), duke.task.Deadline (D), duke.task.Event (E)
    public enum Task_Codes {
        /**
         * T task codes.
         */
        T,
        /**
         * D task codes.
         */
        D,
        /**
         * E task codes.
         */
        E
    }

    /**
     * The enum Task type.
     */
    public enum Task_Type {
        /**
         * Todo task type.
         */
        Todo,
        /**
         * Deadline task type.
         */
        Deadline,
        /**
         * Event task type.
         */
        Event
    }


}


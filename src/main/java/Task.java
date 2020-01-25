import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Task {

    private String description;
    private boolean isDone;
    protected LocalDateTime d1;
    protected DaysOfWeek dm = new DaysOfWeek();
    protected String[] days;
    protected boolean isDay;
    // To check if there is time inclusive in the command a not
    protected boolean hasTime;
    protected String time;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    String format_tasks(String s) throws DukeException {
        return getDescription().substring(getDescription().indexOf(s)).
                replaceAll(s + " ", "");
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    // Get the description
    String getDescription() {
        return description;
    }

    // Set the task as complete
    void setDone(boolean done) {
        isDone = done;
    }

    void setDescription(String s) throws DukeException {
        this.description = s;
    }

    protected void setD1(String at) throws DukeException {
        if (!isDay) {
            // Means the string is entered as "2/12/2019",
            // We format it so that the date becomes "2019-12-02"
            if (at.contains("/")) {
                // DateTimeFormatterw tells java what format is the input of
                // The date and time being entered.
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


                Comparision_between_inputs(at, dateFormat);


            } else if (at.contains("-")) {

                // Parse the string into the LocalDate class
                // Provided that the string is formatted as
                // "2019-10-15", "yyyy-mm-dd"
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

                Comparision_between_inputs(at, dateFormat);


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


            //Get this thing done
            int date_difference = this.dm.getHm().get(days[0].toUpperCase()) - dayOfWeekIntValue;

            if (date_difference <= 0) {
                date_difference += 7;
            }


            LocalDateTime d2 = current.plusDays(date_difference);

            // d4 = date only. Formatted to dateFormatt
            String d4 = d2.toLocalDate().toString();

            // If has specific time component, we set it.

            // Time component included.
            if (hasTime) {
                String day_and_time = days[1].substring(0, 2) + ':' + days[1].substring(2, 4);
                String d3 = d2.toLocalDate().toString() + " " + day_and_time;
                this.d1 = LocalDateTime.parse(d3, dateFormat);
            } else {
                this.d1 = LocalDateTime.parse(d4 + " 00:00", dateFormat);
            }


        }


    }

    // Between "-" date formats and "/" date formats. Same
    private void Comparision_between_inputs(String at, DateTimeFormatter dateFormat) {
        if(!hasTime) {
            LocalDateTime date = LocalDateTime.parse(at + " 00:00" , dateFormat);
            this.d1 = date;
        }
        // If there is a time component
        else {
            System.out.println("Hello");
            //TODO Find a way not to force the substring values.
            String day_and_time = at.substring(11 , 13) + ':' + at.substring(13, 15);

            String[] split_date_from_time = at.split(" ");
            String date_only = split_date_from_time[0];

            LocalDateTime date = LocalDateTime.parse(date_only + " " + day_and_time , dateFormat);
            this.d1 = date;
        }
    }


    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return " [" + this.getStatusIcon() + "] " + getDescription();
    }

    // For whether its To-do(T), Deadline (D), Event (E)
    public enum Task_Codes {
        T, D, E
    }

    public enum Task_Type {
        Todo, Deadline, Event
    }


}


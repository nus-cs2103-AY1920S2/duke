import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Task {

    private String description;
    private boolean isDone;
    protected LocalDate d1;
    protected DaysOfWeek dm = new DaysOfWeek();
    protected String[] days;
    protected boolean isDay;
    protected String time;
    protected boolean hasTime;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    String format_tasks(String s) throws DukeException {




        return getDescription().substring(getDescription().indexOf(s)).
                replaceAll(s+ " ", "");
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
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                this.d1 = LocalDate.parse(at, dateFormat);
                System.out.println("The converted date isa " + this.d1);

            } else if (at.contains("-")) {

                // Parse the string into the LocalDate class
                // Provided that the string is formatted as
                // "2019-10-15", "yyyy-mm-dd"
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                this.d1 = LocalDate.parse(at, dateFormat);
                System.out.println("The converted date is " + this.d1);
            } else {
                throw new DukeException("Date is not in correct format!");
            }
        }

        // If it includes a day in the "/at Monday..."a
        else {
            LocalDate current = LocalDate.now();
            DayOfWeek dayOfWeek = current.getDayOfWeek();
            int dayOfWeekIntValue = dayOfWeek.getValue();


            //Get this thing done
            int date_difference = this.dm.getHm().get(days[0].toUpperCase()) - dayOfWeekIntValue;

            if (date_difference <= 0) {
                date_difference += 7;
            }

            this.d1 = current.plusDays(date_difference);


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


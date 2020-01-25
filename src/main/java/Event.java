import java.time.format.DateTimeFormatter;

public class Event extends Task {

    // for the date and time option
    private String at;
    private String time;

    // isDay if the user uses "../at Monday" instead of a standard yyyy-mm-dd format
    //private static boolean isDay = false;
    // Days array to determine if its a day or a yyyy-mm-dd format
    //String[] days;

    Event(String description, String at) {

        super(description);
        this.at = at.trim();
    }

    private String getAt() {
        return at;
    }

    void setAt(String s) {

        this.at = super.set_by_at(s);

    }


    void setD1() throws DukeException {
        super.setD1(getAt());
    }

    // Covers exceptions caused by empty descriptions
    // Incorrect use of identifiers "../at" (More stringent check of formatting)
    // No deadline descriptors. Eg: "deadline /by Monday"
    // Same for TO-DO, Deadline and Event classes!
    @Override
    String format_tasks(String s) throws DukeException {

        String[] splited_string = getDescription().split("event ");

        if(splited_string[0].length() <1) {
            throw new DukeException("You cannot leave the description empty");
        } else {
            try{
                return s.substring(s.indexOf("at")).replaceAll("at ", "");

            } catch (Exception e) {
                throw new DukeException("Please use ../at instead of any other identifiers ");
            }
        }

    }

    // Ensures the user uses the correct format for an event
    // Eg: Just typing "event".
    @Override
    void setDescription(String s) throws DukeException {

        try {
            String event_task = s.substring(s.indexOf("event"), s.indexOf("/"));
            String event_name = event_task.replaceAll("event", "").trim();
            super.setDescription(event_name);
        } catch (Exception e) {
            throw new DukeException("Please enter the correct format for an event ");
        }
    }

    @Override
    public String toString() {
        return " [" + Task_Codes.E + "]" + super.toString() + " (at: " +
                // The format is to change the formatting patterns (Dec 2 2019 OR 2/12/2019)
                d1.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}

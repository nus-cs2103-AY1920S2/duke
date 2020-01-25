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
        String[] splitted_string = s.split("/");
        String day_of_week_in_string = splitted_string[0];
        days = day_of_week_in_string.split(" ");
        if (super.dm.getHm().containsKey(days[0].toUpperCase())) {
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
                this.at += splitted_string[i] + "/";
            }


            // Check if the time component is inside
            // if its include splitted_string[2] should be 2/12/[2019 1800]
            if (splitted_string[2].length() > 4) {
                hasTime = true;
                this.at += splitted_string[2];
            } else {
                this.at += splitted_string[splitted_string.length - 1];
            }
        }



        // Means theres a day component included. Eg :Monday/Tuesday
        else {

            // Means theres a time component
            if(days.length > 1) {
                hasTime = true;
            } else {
                this.at = day_of_week_in_string;
            }
        }
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

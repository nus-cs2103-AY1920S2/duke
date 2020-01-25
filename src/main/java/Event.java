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

        if (!isDay) {

            // If the user enters "2" instead or "02", this changes it to "02"

            for (int j = 0; j < 2; j++) {
                if (splitted_string[j].length() != 2) {
                    splitted_string[j] = ("0" + splitted_string[j]);
                }
            }

            for (int i = 0; i < splitted_string.length - 1; i++) {
                this.at += splitted_string[i] + "/";
            }
            this.at += splitted_string[splitted_string.length - 1];
        } else {
            this.at = day_of_week_in_string;
        }
    }


    void setD1() throws DukeException {
        super.setD1(getAt());
    }

    @Override
    String format_tasks(String s) {
       return s.substring(s.indexOf("at")).replaceAll("at ", "");
    }

    @Override
    void setDescription(String s) throws DukeException {

        try {
            String event_task = s.substring(s.indexOf("event"), s.indexOf("/"));
            String event_task2 = event_task.replaceAll("event", "").trim();
            super.setDescription(event_task2);
        } catch (Exception e) {
            throw new DukeException("Please enter the correct format for an event ");
        }
    }

    @Override
    public String toString() {
        return " [" + Task_Codes.E + "]" + super.toString() + " (at: " + super.d1 + ")";
    }
}

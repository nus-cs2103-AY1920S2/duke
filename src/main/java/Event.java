import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    // for the date and time option
    private String at;
    private LocalDate d1;

    Event(String description, String at) {

        super(description);
        this.at = at.trim();
    }

    private String getAt() {
        return at;
    }

    void setAt(String s) {
        String date_time = s.substring(s.indexOf("at")).replaceAll("at ", "");

        String[] splitted_string = date_time.split("/");

        String abc = splitted_string[0];

        for(Days_of_week e : Days_of_week.values()) {
            if(abc.contains(e.toString())) {
                System.out.println("Hello WOrld!");
            }
        }

        System.out.println(splitted_string[0]);

        // If the user enters "2" instead or "02", this changes it to "02"

        for(int j=0; j<2;j++) {
            if(splitted_string[j].length()!=2) {
                splitted_string[j] = ("0" + splitted_string[j]);
            }
        }

        for(int i=0; i< splitted_string.length-1; i++) {
            this.at += splitted_string[i] + "/";
        }
        this.at += splitted_string[splitted_string.length-1];

    }


    void setD1() throws DukeException {

       // boolean isDay = false;
//        String[] splitted_string = getAt().split("/");
//
//        String abc = splitted_string[0];
//
//        for (Days_of_week e : Days_of_week.values()) {
//            if (abc.contains(e.toString())) {
//                isDay = true;
//            }
//        }

      //  if (!isDay) {

            // Means the string is entered as "2/12/2019",
            // We format it so that the date becomes "2019-12-02"
            if (getAt().contains("/")) {
                // DateTimeFormatterw tells java what format is the input of
                // The date and time being entered.
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                this.d1 = LocalDate.parse(getAt(), dateFormat);
                System.out.println("The converted date isa " + this.d1);

            } else if (getAt().contains("-")) {

                // Parse the string into the LocalDate class
                // Provided that the string is formatted as
                // "2019-10-15", "yyyy-mm-dd"
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                this.d1 = LocalDate.parse(getAt(), dateFormat);
                System.out.println("The converted date is " + this.d1);
            }

            else {
                throw new DukeException("Date is not in correct format!");
            }
   //     }

        // If it includes a day in the "/at Monday..."a
        // TODO Will do later
       // else {
            LocalDate current = LocalDate.now();
            DayOfWeek dayOfWeek = current.getDayOfWeek();
            int dayOfWeekIntValue = dayOfWeek.getValue();


       // }
    }


    @Override
    void setDescription(String s) throws DukeException {

        try {
            String event_task = s.substring(s.indexOf("event"), s.indexOf("/"));
            String event_task2 = event_task.replaceAll("event", "").trim();
            super.setDescription(event_task2);
        } catch (Exception e) {
            throw new DukeException("", "Event");
        }
    }

    @Override
    public String toString() {
        return " [" + Task_Codes.E + "]" + super.toString() + " (at: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

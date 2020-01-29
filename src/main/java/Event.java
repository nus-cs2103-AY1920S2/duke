import java.io.IOException;

public class Event extends Task{
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public static String getEventDate(char[] input) {
        String date = "";
        int marker = 0;
        for (int i = input.length - 1; (input[i] != '/'); i--) {
            marker = i;
        }
        for (int i = marker + 3; i < input.length; i++) {
            date += input[i];
        }
        return date;
    }

    public static String getEventDesc(char[] input) {
        String desc = "";
        int marker = 0;
        for (int i = input.length - 1; (input[i] != '/'); i--) {
            marker = i;
        }
        for (int i = 6; i < marker - 2; i++) {
            desc += input[i];
        }
        return desc;
    }

    public static Event createEvent(String desc, String date) {
        return new Event(desc, date);
    }

    @Override
    public String saveToText() {
        String output;
        if(this.isDone) {
            output = "E - 1 -" + this.getDescription() + " - " + this.date;
        } else {
            output = "E - 0 - " + this.getDescription() + " - " + this.date;
        }
        return output;
    }

    @Override
    public String toString(){
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + this.date + ")";
    }

}

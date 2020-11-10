import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Event extends Task{
    protected LocalDate date;

    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    public static String getEventDate(String input) {
        String[] tmp = input.split(" /at ");
        String[] inputs = tmp[1].split(" ");
        return inputs[0];
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

    public String formatDate() {
        return date.format(DateTimeFormatter.ofPattern("d-MMM-yyyy"));
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String saveToText() {
        String output;
        if(this.isDone) {
            output = "E - 1 - " + this.getDescription() + " - " + formatDate();
        } else {
            output = "E - 0 - " + this.getDescription() + " - " + formatDate();
        }
        return output;
    }

    @Override
    public String toString(){
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + formatDate() + ")";
    }

}

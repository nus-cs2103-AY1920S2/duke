import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task{
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.date = LocalDate.parse(by);
    }

    public static String getDesc(char[] input) {
        int marker = 0;
        String desc = "";
        for (int i = input.length - 1; (input[i] != '/'); i--) {
            marker = i;
        }
        for (int i = 9; i < marker - 2; i++) {
            desc += input[i];
        }
        return desc;
    }

    public static String getDate(String input) { //YYYY-MM-DD
        String[] tmp = input.split(" /by ");
        String[] inputs = tmp[1].split(" ");
        return inputs[0];
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
            output = "D - 1 - " + this.getDescription() + " - " + formatDate();
        } else {
            output = "D - 0 - " + this.getDescription() + " - " + formatDate();
        }
        return output;
    }

    @Override
    public String toString(){
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + formatDate() + ")";
    }
}

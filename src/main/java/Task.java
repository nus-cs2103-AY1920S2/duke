import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Task {
    public String type;
    public String name;
    public String status;
    public String date;
    public LocalDate processedDate;

    public Task(String type, String name) {
        this.type = type;
        this.name = name;
        this.status = "[✗]";
    }

    public void setDone() {
        this.status = "[✓]";
    }

    public String getIcon() {
        if (this.type.equals("todo")) {
            return "[T]";
        }
        else if (this.type.equals("deadline")) {
            return "[D]";
        }
        else {                  // event
            return "[E]";
        }
    }

    public String getProcessedDate() {
        String dateFormatted = "";
        this.date.split("\\s");
        for (int i = 1; i < this.date.split("\\s").length; i++) {
            if (i == 1) {
                dateFormatted += this.date.split("\\s")[i];
            }
            else {
                dateFormatted += " " + this.date.split("\\s")[i];
            }
        }
        LocalDate ld = LocalDate.parse(dateFormatted);
        this.processedDate = ld;
        return ld.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getDescription() {
        if (this.type.equals("todo")) {
            return this.name;
        }
        else if (this.type.equals("deadline")) {
//            String dateFormatted = "";
//            this.date.split("\\s");
//            for (int i = 1; i < this.date.split("\\s").length; i++) {
//                if (i == 1) {
//                    dateFormatted += this.date.split("\\s")[i];
//                }
//                else {
//                    dateFormatted += " " + this.date.split("\\s")[i];
//                }
//            }
//            dateFormatted = "(by: " + dateFormatted + ")";
//            return this.name + dateFormatted;
            return this.name + "(by: " + this.getProcessedDate() + ")";
        }
        else {                  // event
            String dateFormatted = "";
            this.date.split("\\s");
            for (int i = 1; i < this.date.split("\\s").length; i++) {
                if (i == 1) {
                    dateFormatted += this.date.split("\\s")[i];
                }
                else {
                    dateFormatted += " " + this.date.split("\\s")[i];
                }
            }
            dateFormatted = "(at: " + dateFormatted + ")";
            return this.name + dateFormatted;
        }
    }

    public void addDate(String date) {
        this.date = date;
    }
}

import java.util.*;

public class Task {
    public String type;
    public String name;
    public String status;
    public String date;

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

    public String getDescription() {
        if (this.type.equals("todo")) {
            return this.name;
        }
        else if (this.type.equals("deadline")) {
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
            dateFormatted = "(by: " + dateFormatted + ")";
            return this.name + dateFormatted;
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

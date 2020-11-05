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
    public String fileDate;
    public boolean hasProcessedDate = false;
    public int priority = 0;

    public Task(String type, String name) {
        this.type = type;
        this.name = name;
        this.status = "[\u2718]";
    }

    public void setDone() {
        this.status = "[\u2713]";
    }

    public int getStatusBinary() {
        if (this.status.equals("[\u2713]")) {
            return 1;
        } else {
            return 0;
        }

    }

    public String getIcon() {
        if (this.type.equals("todo") && this.priority == 0) {
            return "[T]";
        } else if (this.type.equals("todo") && this.priority == 1) {
            return "[TH]";
        } else if (this.type.equals("deadline") && this.priority == 0) {
            return "[D]";
        } else if (this.type.equals("deadline") && this.priority == 1) {
            return "[DH]";
        } else if (this.type.equals("event") && this.priority == 0){                  // event
            return "[E]";
        } else {
            return "[EH]";
        }
    }

    /**
     * Processes the user input date into a localdate format.
     *
     * @return String This returns the formatted form of the localdate.
     */

    public String getProcessedDate() {
        String dateFormatted = "";
        String taskType = this.date.split("\\s")[0];
        for (int i = 1; i < this.date.split("\\s").length; i++) {       // starts from i = 1 to remove the "by" or the "at"
            if (i == 1) {
                dateFormatted += this.date.split("\\s")[i];
            } else {
                dateFormatted += " " + this.date.split("\\s")[i];
            }
        }

        if (taskType.equals("at")) {                                            // event, so no need local date format
            this.fileDate = dateFormatted;
            return this.fileDate;
        } else {                                                                // deadline, so need local date format
            LocalDate ld = LocalDate.parse(dateFormatted);
            this.processedDate = ld;
            this.hasProcessedDate = true;
            this.fileDate = ld.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return this.fileDate;
        }
    }

    public String addProcessedDate(String dateFormatted) {
        //System.out.println(dateFormatted);
        int month = 0;
        int day = Integer.parseInt(dateFormatted.split("\\s")[1]);
        int year = Integer.parseInt(dateFormatted.split("\\s")[2]);
        if (dateFormatted.split("\\s")[0].equals("Jan")) {
            month = 1;
        } else if (dateFormatted.split("\\s")[0].equals("Feb")) {
            month = 2;
        } else if (dateFormatted.split("\\s")[0].equals("Mar")) {
            month = 3;
        } else if (dateFormatted.split("\\s")[0].equals("Apr")) {
            month = 4;
        } else if (dateFormatted.split("\\s")[0].equals("May")) {
            month = 5;
        } else if (dateFormatted.split("\\s")[0].equals("Jun")) {
            month = 6;
        } else if (dateFormatted.split("\\s")[0].equals("Jul")) {
            month = 7;
        } else if (dateFormatted.split("\\s")[0].equals("Aug")) {
            month = 8;
        } else if (dateFormatted.split("\\s")[0].equals("Sep")) {
            month = 9;
        } else if (dateFormatted.split("\\s")[0].equals("Oct")) {
            month = 10;
        } else if (dateFormatted.split("\\s")[0].equals("Nov")) {
            month = 11;
        } else {
            month = 12;
        }
        this.processedDate = LocalDate.of(year, month, day);
        this.hasProcessedDate = true;
        this.fileDate = this.processedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return this.fileDate;
    }

    public String getDescription() {
        if (this.type.equals("todo")) {
            return this.name;
        } else if (this.type.equals("deadline")) {
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
            if (this.hasProcessedDate == false) {
                return this.name + " (by: " + this.getProcessedDate() + ")";
            } else {
                return this.name + " (by: " + this.fileDate + ")";
            }
        } else {                  // event
            return this.name + " (at: " + this.fileDate + ")";
        }
    }

    public void setPriority(int priorityLevel) {
        this.priority = priorityLevel;
    }
    public void addDate(String date) {
        this.date = date;
    }
}

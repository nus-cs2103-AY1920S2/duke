package packagedirectory.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import packagedirectory.test.Message;
import packagedirectory.test.Tasks;

import java.time.temporal.ChronoUnit;

public class Deadlines extends Tasks {

    private LocalDate date;

    Deadlines(Message msg) {
        super(msg);
        super.logo = "[D]";
        String[] data = super.msg.getMsg().split("by: ");
        String dates = data[1].substring(1,11);
        date = LocalDate.parse(dates);
        String newMsg = data[0] + "by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        super.msg = new Message(newMsg);
    }

    public Deadlines(Message message, String status) {
        super(message);
        super.status = status;
        super.logo = "[D]";
    }

    @Override
    public String toString() {
        return logo + super.status + " " + super.msg.getMsg() + "\n";
    }
}

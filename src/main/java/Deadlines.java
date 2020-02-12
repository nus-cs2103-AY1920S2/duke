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
    public String done() {
        status = "[o]";
        Message output = new Message("Nice! I've finish this task:\n"
                + logo
                + status
                + " "
                + msg.getMsg());
        return output.getMsg();
    }

    @Override
    public String removed() {
        count--;
        Message output = new Message("Noted. I've removed this tasks:\n"
                + logo
                + status
                + " " + msg.getMsg() +
                "\nNow you have " + count + " tasks in the list.\n");
        return output.getMsg();
    }

    @Override
    public String added() {
        String output = Message.lines
                + "Got it. I've added this task:\n"
                + this + "\n"
                + "Now you have "
                + count
                + " tasks in the list.\n"
                + Message.lines;
        return output;
    }

    @Override
    public String toString() {
        return logo + super.status + " " + super.msg.getMsg() + "\n";
    }
}

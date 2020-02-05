package packagedirectory.test;

import packagedirectory.test.Message;
import packagedirectory.test.Tasks;

public class ToDos extends Tasks {
    //private String logo = "[T]";
    public ToDos(Message msg) {
        super(msg);
        super.logo = "[T]";
    }

    public ToDos(Message message, String status) {
        super(message);
        super.status = status;
        super.logo = "[T]";
    }

    @Override
    public String done() {
        status = "[o]";
        Message output = new Message("Nice! I've finish this task:\n" + logo + status + " " + msg.getMsg());
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

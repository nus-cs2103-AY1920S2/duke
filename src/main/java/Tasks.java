package packagedirectory.test;

import packagedirectory.test.Message;

public class Tasks {

    protected Message msg;
    protected String status;
    public static int count;
    protected String logo;
    private String text;

    public Tasks(Message msg) {
        this.msg = msg;
        this.status = "[x]";
        this.logo = "";
        count++;
    }

    public Tasks(String logo, String text) {
        this.logo = logo;
        this.text = text;
    }

    public Tasks() {
        this.status = "";
        this.logo = "";
    }

    public String getText() {
        return text;
    }

    public String getLogo() {
        return logo;
    }

    public Message getMsg() {
        return msg;
    }

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

    public String done() {
        status = "[o]";
        Message output = new Message("Nice! I've finish this task:\n"
                + logo
                + status
                + " "
                + msg.getMsg());
        return output.getMsg();
    }

    public String removed() {
        count--;
        Message output = new Message("Noted. I've removed this tasks:\n"
                + logo
                + status
                + " " + msg.getMsg() +
                "\nNow you have " + count + " tasks in the list.\n");
        return output.getMsg();
    }

    public boolean isEmpty() {
        return status.equals("");
    }

    @Override
    public String toString() {
        return status + " " + msg.getMsg() + "\n";
    }

}

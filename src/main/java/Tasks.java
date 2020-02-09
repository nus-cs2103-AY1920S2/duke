package packagedirectory.test;

import packagedirectory.test.Message;

public class Tasks {

    protected Message msg;
    protected String status;
    public static int count;
    protected String logo;

    public Tasks(Message msg) {
        this.msg = msg;
        this.status = "[x]";
        this.logo = "";
        count++;
    }

    public Tasks() {
        this.status = "";
        this.logo = "";
    }

    public Message getMsg() {
        return msg;
    }

    public String added() {
        String output = Message.lines + "added: " + msg.getMsg() + "\n" + Message.lines;
        return output;
    }

    public String done() {
        status = "[o]";
        Message output = new Message("Nice! I've finish this task:\n" + status + " " + msg.getMsg());
        return output.getMsg();
    }

    public String removed() {
        Message output = new Message("Noted. I've removed this task:\n" + status + " " + msg.getMsg());
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

package packagedirectory.test;

public class Tasks {

    protected Message msg;
    protected String status;
    public static int count;

    public Tasks(Message msg) {
        this.msg = msg;
        this.status = "[x]";
        count++;
    }

    public void added() {
        String output = Message.lines + "added: " + msg.getMsg() + "\n" + Message.lines;
        System.out.println(output);
    }

    public void done() {
        status = "[o]";
        Message output = new Message("Nice! I've finish this task:\n" + status + " " + msg.getMsg());
        System.out.println(output);
    }

    @Override
    public String toString() {
        return status + " " + msg.getMsg() + "\n";
    }

}

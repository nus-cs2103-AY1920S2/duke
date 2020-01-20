package projectdirectoryjava;

public class Tasks {

    private Message msg;
    private String status;

    public Tasks(Message msg) {
        this.msg = msg;
        this.status = "[x]";
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

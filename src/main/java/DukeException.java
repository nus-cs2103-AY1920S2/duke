public class DukeException extends Exception {

    public String msg;

    public DukeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        if (!msg.equals("others")) {
            return "____________________________________________________________\n" +
                    msg + " does not tell me anything! BE SPECIFIC! \n" +
                    "____________________________________________________________\n";
        } else {
            return "____________________________________________________________\n" +
                    "I do not understand what you mean!!! :( \n" +
                    "____________________________________________________________\n";
        }
    }
}

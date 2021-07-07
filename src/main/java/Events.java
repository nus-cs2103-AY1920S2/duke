package packagedirectory.test;

import packagedirectory.test.Message;
import packagedirectory.test.Tasks;

public class Events extends Tasks {
    //private String logo = "[E]";
    Events(Message msg) {
        super(msg);
        super.logo = "[E]";
    }

    public Events(Message message, String status) {
        super(message);
        super.status = status;
        super.logo = "[E]";
    }

    @Override
    public String toString() {
        return logo + super.status + " " + super.msg.getMsg() + "\n";
    }
}

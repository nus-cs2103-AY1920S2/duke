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
    public String toString() {
        return logo + super.status + " " + super.msg.getMsg() + "\n";
    }
}

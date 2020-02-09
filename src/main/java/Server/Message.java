package Server;

import java.io.Serializable;

public class Message implements Serializable {
    public String message;
    public Object object;
    public Message(String message, Object object) {
        this.message = message;
        this.object = object;
    }
}

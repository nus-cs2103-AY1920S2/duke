import java.util.ArrayList;

public class Response {

    private Message message;
    private Object argument;

    public Response(Message message, Object argument) {

        this.message = message;
        this.argument = argument;
    }

    public Message getMessage() {

        return message;
    }

    public Object getArgument() {

        return argument;
    }

}

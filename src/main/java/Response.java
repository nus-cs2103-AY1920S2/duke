import java.util.ArrayList;

/**
 * The type Response.
 */
public class Response {

    private Message message;
    private Object argument;

    /**
     * Instantiates a new Response.
     *
     * @param message  the message
     * @param argument the argument
     */
    public Response(Message message, Object argument) {

        this.message = message;
        this.argument = argument;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public Message getMessage() {

        return message;
    }

    /**
     * Gets argument.
     *
     * @return the argument
     */
    public Object getArgument() {

        return argument;
    }

}

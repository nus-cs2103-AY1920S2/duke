package main;

/**
 * The type main.Response.
 */
public class Response {

    private Message message;
    private Object argument;

    /**
     * Instantiates a new main.Response.
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

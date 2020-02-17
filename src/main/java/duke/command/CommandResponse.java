package duke.command;

/**
 * Represents the response given to the user after executing a command.
 */
public class CommandResponse {
    private final String response;

    CommandResponse() {
        this("");
    }

    CommandResponse(String response) {
        this.response = response;
    }

    /**
     * Returns the string representation of this CommandResponse.
     *
     * @return The string representation of this CommandResponse.
     */
    @Override
    public String toString() {
        return response;
    }
}

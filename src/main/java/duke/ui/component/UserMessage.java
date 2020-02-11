package duke.ui.component;

/**
 * Represents a message sent by the user.
 */
public class UserMessage extends Message {
    /**
     * Constructs a message sent the user.
     * 
     * @param messageString Message string
     */
    public UserMessage(String messageString) {
        super(messageString, "/image/DaUser.png", Alignment.RIGHT);
    }
}

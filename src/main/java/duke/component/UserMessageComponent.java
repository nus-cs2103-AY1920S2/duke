package duke.component;

/**
 * Represents a message sent by the user.
 */
public class UserMessageComponent extends MessageComponent {
    /**
     * Constructs a message sent the user.
     * 
     * @param messageString Message string
     */
    public UserMessageComponent(String messageString) {
        super(messageString, "/image/DaUser.png", Alignment.RIGHT);
    }
}

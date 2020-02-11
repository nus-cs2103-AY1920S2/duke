package duke.component;

/**
 * Represents a message sent by Duke.
 */
public class DukeMessageComponent extends MessageComponent {
    /**
     * Constructs a message sent by Duke.
     * 
     * @param messageString Message string
     */
    public DukeMessageComponent(String messageString) {
        super(messageString, "/image/DaDuke.png", Alignment.LEFT);
    }
}

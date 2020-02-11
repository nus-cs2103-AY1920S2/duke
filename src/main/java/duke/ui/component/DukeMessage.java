package duke.ui.component;

/**
 * Represents a message sent by Duke.
 */
public class DukeMessage extends Message {
    /**
     * Constructs a message sent by Duke.
     * 
     * @param messageString Message string
     */
    public DukeMessage(String messageString) {
        super(messageString, "/image/DaDuke.png", Alignment.LEFT);
    }
}

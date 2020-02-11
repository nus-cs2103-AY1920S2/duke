package duke.ui;

/**
 * Encapsulates a deferred message to be displayed in the UI.
 * This is needed because the GUI may need to handle displayed errors
 * before it is initialized with `Gui#start()`.
 */
class Message {
    String text;

    Message(String text) {
        this.text = text;
    }
}

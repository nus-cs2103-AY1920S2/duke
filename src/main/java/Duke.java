import packagedirectory.test.ChatBox;
import packagedirectory.test.Message;

import java.io.File;

public class Duke {

    private ChatBox chatBox = new ChatBox("data/duke.txt");

    public String initialize() {
        String initMsg = chatBox.initialise();
        return initMsg;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output;
        output = chatBox.reply(new Message(input));
        return output;
    }

    public void getResponse(String type, String detail) {
        String input = type + " " + detail;
        Message response = new Message(input);
        chatBox.reply(response);
    }

    public boolean isClose() {
        return chatBox.isClose();
    }
}

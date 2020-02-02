import packagedirectory.test.ChatBox;

public class Duke {

    public static void main(String[] args) {
        try {
            ChatBox chatBox = new ChatBox("./src/main/duke.txt");
            chatBox.initialise();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}

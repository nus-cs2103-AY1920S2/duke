import packagedirectory.test.ChatBox;

public class Duke {

    public static void main(String[] args) {
        try {
            ChatBox cb = new ChatBox("./src/main/duke.txt");
            cb.initialise();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}

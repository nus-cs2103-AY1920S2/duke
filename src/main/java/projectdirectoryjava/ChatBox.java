package projectdirectoryjava;

import java.util.Scanner;

public class ChatBox {
    private Folder folder;
    private boolean toClose;

    public ChatBox() {
        this.folder = new Folder();
        this.toClose = true;
    }

    public void reply(Message input) {
        String msg = input.getMsg();
        switch (msg) {
            case "bye" :
                Message.end();
                toClose = false;
                break;
            case "list" :
                folder.show();
                break;
            default:
                folder.add(input);
                input.added();
        }
    }

    public void initialise() {
        Message.welcome();
        Scanner scan = new Scanner(System.in);
        while(toClose && scan.hasNextLine()) {
            Message input = new Message();
            String msg = scan.nextLine();
            input.add(msg);
            reply(input);
        }
        scan.close();
    }
}
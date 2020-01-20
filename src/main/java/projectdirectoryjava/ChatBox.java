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
        String[] msg = input.getMsg().split(" ");
        String key = msg[0];
        switch (key) {
            case "bye" :
                Message.end();
                toClose = false;
                break;
            case "list" :
                folder.show();
                break;
            case "done" :
                int i = Integer.parseInt(msg[1]);
                folder.finishTasks(i);
                break;
            default:
                folder.add(new Tasks(input));
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
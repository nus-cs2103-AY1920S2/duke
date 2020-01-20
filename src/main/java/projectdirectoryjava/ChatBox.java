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
                Tasks tasks;
                if(key.equals("todo")) {
                    String s = input.getMsg().split("todo ")[1];
                    tasks = new ToDos(new Message(s));
                } else if(key.equals("deadline")) {
                    String s1 = input.getMsg().split("deadline ")[1];
                    String s2 = s1.split("/by")[0];
                    String s3 = s1.split("/by")[1];
                    tasks = new Deadlines(new Message(s2 + "(by: " + s3 +")"));
                } else if(key.equals("event")) {
                    String s1 = input.getMsg().split("event ")[1];
                    String s2 = s1.split("/at")[0];
                    String s3 = s1.split("/at")[1];
                    tasks = new Deadlines(new Message(s2 + "(by: " + s3 +")"));
                } else {
                    tasks = new Tasks(input);
                }
                folder.add(tasks);
                tasks.added();

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
package projectdirectoryjava;

import java.util.Scanner;

public class ChatBox {

    public static void reply(Message input) {
        System.out.println(input);
    }

    public static void initialise() {
        Message.welcome();
        Scanner scan = new Scanner(System.in);
        Message input = new Message();
        boolean toClose = true;
        while(toClose && scan.hasNextLine()) {
            String msg = scan.nextLine();
            input.add(msg);
            if(msg.equals("bye")) {
                Message.end();
                toClose = false;
            } else {
                reply(input);
            }
        }
        scan.close();
    }
}
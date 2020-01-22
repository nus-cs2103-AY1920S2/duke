import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                sayBye();
                break;
            } else {
                echoCommand(command);
            }
        }
    }

    public static void greet() {
        String greeting = "Hi there, I'm Dodo!\nHow may I help you today?";
        System.out.println(greeting);
    }

    public static void echoCommand(String command) {
        System.out.println(command);
    }

    public static void sayBye() {
        String byeCommand = "Stop procrastinating. See you!";
        System.out.println(byeCommand);
    }
}

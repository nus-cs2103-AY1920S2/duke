import java.util.*;

public class Duke {
    String stupidLogo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String resSpace = "    ";
    static String line = "____________________________________________________________";
    static String greetings = "Hello! I'm Duke\n" +
            "What can I do for you?";
    static void respond(String... respondStr) {
        Scanner sc2;
        System.out.print(resSpace);
        System.out.println(line);
        for(String str : respondStr) {
            sc2 = new Scanner(str);
            while(sc2.hasNext()) {
                System.out.print(resSpace + " ");
                System.out.println(sc2.nextLine());
            }
        }
        System.out.print(resSpace);
        System.out.println(line);
        System.out.println();
    }

    static boolean isCommand(String cmd) {
        return true;
    }

    static void execCommand(String cmd) {
        switch(cmd) {
            case "exit":
            respond("Bye. Hope to see you again soon!");
            System.exit(0);
            break;
            default:
                respond(cmd);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        respond(greetings);
        String cmd;
        while(isCommand(cmd = sc.nextLine())) {
            execCommand(cmd);
        }
    }
}

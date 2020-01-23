import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String machine = "Dude: ";
    public static String user = "dude: ";

    public static void main(String[] args) {
        greeting();
        String command = sc.next();
        while (!command.equals("bye")) {
            System.out.println(machine + command);
            System.out.print(user);
            command = sc.next();
        }
        System.out.println(machine + "Okay see ya dude!");
    }

    public static void greeting() {
        String welcome = "\n"
                + machine + "Hi dude! I'm your Dude\n"
                + "      What do you want dude?\n"
                + user;
        System.out.print(welcome);
    }
}

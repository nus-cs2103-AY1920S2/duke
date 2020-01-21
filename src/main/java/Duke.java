import java.util.Scanner;

public class Duke {

    private final static String INDENT = "    ";
    private final static String BORDER = "____________________________________________________________";
    private final static String EXIT = "bye";
    private final static String GOODBYE_MESSAGE = "Goodbye and have a beautiful time!";

    public static void main(String[] args) {
        String logo = INDENT + "  _____  __    __  _____ \n"
                + INDENT + " |  ___||  \\  /  ||  ___| \n"
                + INDENT + " | |__   \\  \\/  / | |__\n"
                + INDENT + " |  __|   |    |  |  __|\n"
                + INDENT + " | |___  /  /\\  \\ | |___\n"
                + INDENT + " |_____||__/  \\__\\|_____| \n";
        System.out.println(INDENT + BORDER);
        System.out.println(logo);
        System.out.println(INDENT + "Hello! I'm EXE, I'll execute anything on your command! :)");
        System.out.println(INDENT + "What do you want to exe?");
        System.out.println(INDENT + BORDER);

        Scanner scanner = new Scanner(System.in);

        String message = scanner.nextLine();

        while (!message.equals(EXIT)) {
            System.out.println(formatReply(message));
            message = scanner.nextLine();
        }

        System.out.println(formatReply(GOODBYE_MESSAGE));

        scanner.close();
    }

    public static String formatReply(String message) {
        String reply = INDENT + BORDER + "\n" + INDENT + " " + message + "\n" + INDENT + BORDER;
        return reply;
    }

}

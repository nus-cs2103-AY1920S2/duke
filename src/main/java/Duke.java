import java.util.Scanner;

public class Duke {
    private static final String line = "    ____________________________________________________________";
    private static final String space = "    ";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Greetings, I am\n" + logo);
        System.out.println("How may I help you in this fine day today?");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println(space + "I bid you adieu. Until the day we meet again.");
                System.out.println(line);
                break;
            } else {
                System.out.println(line);
                System.out.println(space + input);
                System.out.println(line);
            }
        }

    }
}

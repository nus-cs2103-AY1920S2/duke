import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        String intro =
            "Hello! I'm Duke\n" +
            "What can I do for you?\n";

        System.out.println(stringWrapper((intro)));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(input);
            if (input.equals("bye")) {
                System.out.println(stringWrapper("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.println(stringWrapper(input));
        }

    }

    private static String stringWrapper(String string) {
        return "____________________\n" + string + "\n" + "____________________\n";
    }

}

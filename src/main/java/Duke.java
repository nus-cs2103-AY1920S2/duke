import java.util.Scanner;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        String greeting = "Hey there! I'm DingDing!\n"
                + "    What's up today? ;D";

        printWithBorder(greeting);
        output();
    }

    public static void output() {
        String reply = SCANNER.nextLine();
        while(!reply.equals("bye")) {
            printWithBorder(reply);
            reply = SCANNER.nextLine();
        }
        printWithBorder("Bye! See ya later, alligator!");
    }

    public static void printWithBorder(String message) {
        System.out.println("    #############################\n"
                + "    " + message +"\n"
                + "    #############################\n");

    }

}

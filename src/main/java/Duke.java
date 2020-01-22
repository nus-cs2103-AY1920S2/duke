import java.util.Scanner;

public class Duke {

    static Scanner sc = new Scanner(System.in);

    static void printIntro() {
        System.out.print(
                "____________________________________________________________\n" +
                        "Hello! I'm Duke\n" +
                        "What can I do for you?\n" +
                        "____________________________________________________________\n\n");
    }

    static void printReply(String input) {
        System.out.print(
                "____________________________________________________________\n" +
                input +
                "\n____________________________________________________________\n");
    }

    static void printGoodbye() {
        System.out.print(
                "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!" +
                        "\n____________________________________________________________\n");
    }
    public static void main(String[] args) {
        printIntro();
        String input = sc.nextLine();
        while (!input.toLowerCase().equals("bye")){
            printReply(input);
            input = sc.nextLine();
        }
        printGoodbye();
    }


}

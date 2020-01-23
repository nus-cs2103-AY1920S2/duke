import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        runDuke();
    }

    public static void greeting() {
        System.out.println("Hello! I'm Duchess");
        System.out.println("What can I do for you?");
    }

    public static void runDuke() {
        greeting();

        Scanner sc = new Scanner(System.in);
        String input;
        while (!(input = sc.nextLine()).equals("bye")) {
            System.out.println(input + "\n");
        }
        System.out.println("See you soon!");
    }
}

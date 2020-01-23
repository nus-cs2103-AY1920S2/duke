import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I am from North Korea\n" +
                "What can I do for you?");
        System.out.println("____________________________________");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(input);
                input = sc.nextLine();
            }
        }

        System.out.println("GOODBYE!! MUAHAHHAHAHAHHAAHAHHAHAHA");

    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I am from North Korea\n" +
                "What can I do for you?");
        System.out.println("____________________________________\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<String>();

        String input = sc.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
                input = sc.nextLine();
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
                input = sc.nextLine();
            }
        }

        System.out.println("GOODBYE!! MUAHAHHAHAHAHHAAHAHHAHAHA");

    }
}

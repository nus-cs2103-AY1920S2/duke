import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Greeting message
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printBye();
                break;
            } else if (input.equals("list")) {
                printList(list);
            } else {
                addToList(input, list);
            }
        }
    }

    public static void addToList(String input, ArrayList<String> list) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    added: " + input);
        System.out.println("    ____________________________________________________________");
        list.add(input);
    }

    public static void printList(ArrayList<String> list) {
        System.out.println("    ____________________________________________________________");
        for (String s : list) {
            int listNo = list.indexOf(s) + 1;
            System.out.println("    " + listNo + ". " + s);
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void printBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}

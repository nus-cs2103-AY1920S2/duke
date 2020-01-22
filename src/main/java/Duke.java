import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> lst = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.compareTo("bye") != 0) {
                if (input.compareTo("list") != 0) {
                    lst.add(input);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     added: " + input);
                    System.out.println("    ____________________________________________________________");
                } else {
                    printList(lst);
                }
            } else {
                System.out.println("    ____________________________________________________________\n"
                        + "     Bye. Hope to see you again soon!\n"
                        + "    ____________________________________________________________");
                break;
            }
        }
    }

    private static void printList(ArrayList lst) {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println("    " + (i+1) + ". " + lst.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }
}

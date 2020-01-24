import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    -----------------------------------\n"
                + "      Hello! I'm\n" + logo);
        System.out.println("      What can I do for you?\n"
                + "    -----------------------------------");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> strArr = new ArrayList<>();

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (!input.equals("bye")) {
                if (input.equals("list")) {
                    System.out.println("    -----------------------------------");
                    for (int i = 1; i < strArr.size() + 1; i++) {
                        System.out.println("      " + i + ". " + strArr.get(i-1));
                    }
                    System.out.println("    -----------------------------------");
                } else {
                    strArr.add(input);
                    System.out.println("    -----------------------------------\n"
                            + "      added: " + input +"\n"
                            + "    -----------------------------------");
                }
            } else {
                System.out.println("    -----------------------------------\n"
                        + "      Bye. Hope to see you again soon!\n"
                        + "    -----------------------------------");
                System.exit(0);
            }
        }
    }
}

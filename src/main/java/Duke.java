import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        boolean end = false;

        String opening_message = "____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(opening_message);

        while(!end) {
            String next_cmd = sc.nextLine();
            if (next_cmd.equals("bye")) {
                end = true;
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
            }
            else if (next_cmd.equals("list")) {
                String list_of_items = "";
                for(int i = 0; i < list.size(); i++) {
                    list_of_items += "     "+ (i+1) + ":" + list.get(i) + "\n";
                }
                System.out.println("    ____________________________________________________________\n" +
                        list_of_items +
                        "    ____________________________________________________________");
            }
            else if (next_cmd.equals("blah")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     blah\n" +
                        "    ____________________________________________________________");

            }
            else {
                list.add(next_cmd);
                System.out.println("    ____________________________________________________________\n" +
                        "     " + next_cmd + "\n" +
                        "    ____________________________________________________________");
            }
        }
    }
}

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        String borderDesign = "    ____________________________________________________________";
        String greet = borderDesign + "\n"
                + "     Hello! I'm Duke\n     What can I do for you?\n"
                + borderDesign + "\n";
        System.out.println(greet);

        String exit = "     Bye. Hope to see you again soon!\n"
                + borderDesign;

        Scanner sc = new Scanner(System.in);
        ArrayList<String> lst = new ArrayList<String>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            System.out.println(borderDesign);
            if (line.equals("bye")) {
                System.out.println(exit);
                System.exit(0);

            } else if (line.equals("list")) {
                for (int i = 1; i <= lst.size(); i++) {
                    String item = "     " + i + ". " + lst.get(i - 1);
                    System.out.println(item);
                }

            } else {
                lst.add(line);
                System.out.println("     added: " + line);
            }
            System.out.println(borderDesign + "\n");
        }
    }
}

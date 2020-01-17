import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> stored_list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // Welcome message for the user
        String welcome_message = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" + "What can I do for you today?\n"+
                "____________________________________________________________";

        // Lines are for in between the two words
        String lines = "        ____________________________________________________________";
        String space = "        ";

        System.out.println(welcome_message);

        while(sc.hasNext()) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println(lines);
                System.out.println("        Bye. Hope to see you again soon");
                System.out.println(lines);
                stored_list.clear();
                break;
            } else if (input.equals("list")) {
                System.out.println(lines);
                for(int i=0; i< stored_list.size(); i++) {
                    System.out.println(space + (i+1) + ". " + stored_list.get(i));
                }
                System.out.println(lines);
                continue;
            }
            stored_list.add(input);
            // Print out the input
            System.out.println(lines);
            System.out.println("         added: "+input);
            System.out.println(lines);
        }
    }
}
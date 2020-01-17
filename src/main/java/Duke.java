import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> stored_list = new ArrayList<>();
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
                    System.out.println(space + (i+1) + ". [" + stored_list.get(i).getStatusIcon() + "]"
                     +" "+ stored_list.get(i).getDescription());
                }
                System.out.println(lines);
                continue;
            } else if (input.contains("done")) {
                // To split the done with the number
                // Will think of a smarter way tomorrow.
                String[] splited_string = input.split(" ");
                Integer number = Integer.valueOf(splited_string[1]);
                Task finished_task = stored_list.get(number-1);
                finished_task.setDone(true);
                System.out.println(lines);
                System.out.println(space +"Nice! I've marked this task as done");
                System.out.println(space + " [" + finished_task.getStatusIcon() + "]"
                        +" "+ finished_task.getDescription());
                System.out.println(lines);
                continue;
            }

            // Else we add new tasks to the list.
            stored_list.add(new Task(input));
            // Print out the input
            System.out.println(lines);
            System.out.println("         added: "+input);
            System.out.println(lines);
        }
    }
}
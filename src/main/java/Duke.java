import java.util.*;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> lst = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hey! I'm Duke!");
        System.out.println("What can I help you with?");
        boolean done = false;
        while (done != true) {
            String output = sc.nextLine();
            if (output.equals("bye")) {
                System.out.println("Bye! See you around:)");
                done = true;
            } else if (output.equals("list")) {
                int counter = 1;
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println(counter +".[" + lst.get(i).getStatusIcon() + "] " +lst.get(i).getD() );
                    counter++;
                }
            } else if (output.split(" ")[0].equals("done")) {
                int space = Integer.parseInt(output.split(" ")[1]) -1;
                lst.get(space).doAct();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + lst.get(space).getStatusIcon() + "] " + lst.get(space).getD());
            } else {
                System.out.println("added: " + output);
                lst.add(new Task(output));
            }
        }
        sc.close();
    }
}


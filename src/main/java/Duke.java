import java.util.*;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> lst = new ArrayList<String>();
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
                    System.out.println(counter +". " + lst.get(i));
                    counter++;
                }
            } else {
                System.out.println("added: " + output);
                lst.add(output);
            }
        }
        sc.close();
    }
}

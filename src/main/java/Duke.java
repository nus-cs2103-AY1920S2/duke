import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static int num = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String intro = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(intro);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equals("list")) {
                for (String l : list) {
                    System.out.println(l);
                }
            }
            else {
                String line = "" + num + ". " + input;
                list.add(line);
                System.out.println("added: " + line);
                num++;
            }
        }
    }
}
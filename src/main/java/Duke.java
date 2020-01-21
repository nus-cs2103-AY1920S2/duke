import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static int num = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String intro = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(intro);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] stringArr = input.split(" ");
            String action = stringArr[0];
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equals("list")) {
                for (Task t : list) {
                    int index = list.indexOf(t) + 1;
                    System.out.println("" + index + "." + t.toString());
                }
            }
            else if (action.equals("done")) {
                int val = Integer.parseInt(stringArr[1]);
                Task task = list.get(val - 1);
                task.setCheck();
                System.out.println("Nice! I've marked this task as done:\n" + task.toString());

            }
            else {
                Task task = new Task(input);
                String line = "" + num + ". " + input;
                list.add(task);
                System.out.println("added: " + line);
                num++;
            }
        }
    }
}
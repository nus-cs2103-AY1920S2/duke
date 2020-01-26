import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        //initiate objects
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        //read user input
        while(true) {
            String input = sc.nextLine();
            String firstWord = input.split(" ")[0];
            if (input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("%d. %s", i+1, list.get(i)));
                }
            } else if (firstWord.equals("done")){
                int num = Integer.parseInt(input.split(" ")[1]);
                System.out.println("Nice! I've marked this task as done: ");
                list.get(num-1).markAsDone();
                System.out.println(list.get(num-1));
            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }
}

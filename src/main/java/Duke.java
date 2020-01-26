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
            } else if (firstWord.equals("todo")) {
                System.out.println("Got it. I've added this task:");
                Task todo = new ToDo(input.substring(5));
                System.out.println(todo);
                list.add(todo);
                System.out.println(String.format("Now you have %d tasks in your list", list.size()));
            } else if (firstWord.equals("deadline")) {
                System.out.println("Got it. I've added this task:");
                Task deadline = new Deadline(input.substring(9, input.indexOf('/')),
                        input.substring(input.indexOf('/') + 4));
                System.out.println(deadline);
                list.add(deadline);
                System.out.println(String.format("Now you have %d tasks in your list", list.size()));
            } else if (firstWord.equals("event")) {
                System.out.println("Got it. I've added this task:");
                Task event = new Event(input.substring(6, input.indexOf('/')),
                        input.substring(input.indexOf('/') + 4));
                System.out.println(event);
                list.add(event);
                System.out.println(String.format("Now you have %d tasks in your list", list.size()));
            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }
}

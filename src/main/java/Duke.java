import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void promptUser(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        //initiate objects
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        //read user input
        while(true) {
            String input = sc.nextLine();
            String[] words = input.split(" ");
            String firstWord = words[1];
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
                try {
                    if(words.length == 1) {
                        throw new StringIndexOutOfBoundsException("without argument");
                    }
                    Task todo = new ToDo(input.substring(5));
                    list.add(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);
                    System.out.println(String.format("Now you have %d tasks in your list", list.size()));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (firstWord.equals("deadline")) {
                try {
                    if(words.length == 1) {
                        throw new StringIndexOutOfBoundsException("without argument");
                    }
                    Task deadline = new Deadline(input.substring(9, input.indexOf('/')),
                            input.substring(input.indexOf('/') + 4));
                    list.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    System.out.println(String.format("Now you have %d tasks in your list", list.size()));
                } catch(StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (firstWord.equals("event")) {
                try {
                    if(words.length == 1) {
                        throw new StringIndexOutOfBoundsException("without argument");
                    }
                    Task event = new Event(input.substring(6, input.indexOf('/')),
                        input.substring(input.indexOf('/') + 4));
                    list.add(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                    System.out.println(String.format("Now you have %d tasks in your list", list.size()));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                }
            } else if (firstWord.equals("delete")) {
                int deleteNum = Integer.parseInt(words[1]);
                Task deleted = list.get(deleteNum - 1);
                list.remove(deleteNum - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println(deleted);
                System.out.println(String.format("Now you have %d tasks in your list", list.size()));
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}

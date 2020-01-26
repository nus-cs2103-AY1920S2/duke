import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Duke Setup
        List<String> todoList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        //Welcome Text
        print("Hello! I'm Duke\nWhat can I do for you?");

        //Main Program Loop, possible change to Switch Case later on
        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                break;
            } else {
                if (input.equals("list")) {
                    showList(todoList);
                } else {
                    todoList.add(input);
                    print("added: " + input);
                }
            }
        }
    }

    //Custom print Method to print simple inputs
    static void print(String output) {
        System.out.println("____________________________________________________________");
        System.out.println(output);
        System.out.println("____________________________________________________________\n");
    }

    //Custom showList Method to print the list with the horizontal borders + running index
    static void showList(List<String> todoList) {
        System.out.println("____________________________________________________________");

        for(int i = 0; i < todoList.size(); i++) {
            System.out.println(i+1 + ". " + todoList.get(i));
        }

        System.out.println("____________________________________________________________\n");
    }
}

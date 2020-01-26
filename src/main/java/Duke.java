import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        //initiate objects
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        //read user input
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")){
                for(int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("%d. %s", i+1, list.get(i)));
                }
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}

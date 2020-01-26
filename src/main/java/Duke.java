
import java.util.Scanner;
import java.util.*;

public class Duke{

    static Scanner sc = new Scanner(System.in);

    static void printGreetings() {
        System.out.print("____________________________________________________________ \n" + "Hello! I'm Duke \n"
                + "What can I do for you?\n" + "____________________________________________________________ \n"

        );
    }

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<String>(); // Creating arraylist

        printGreetings();
        String input = sc.nextLine();
        int count = 1;
        Iterator itr = list.iterator();



        while (!input.equals("bye")) {
            if(!input.equals("list")){
                System.out.println("____________________________________________________________\n" + "added: " + input
                        + "\n____________________________________________________________\n");
                list.add(input);
            }
            if(input.equals("list")){
                count = 1;
                System.out.println("____________________________________________________________\n");

                for(String obj: list){
                    System.out.println(count + ". " + obj);
                    count++;
                }
                System.out.println("____________________________________________________________\n");


            }
            input = sc.nextLine();

        }
        System.out.println("____________________________________________________________\n" + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n");

    }
}
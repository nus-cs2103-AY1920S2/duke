
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

        ArrayList<Task> list = new ArrayList<Task>(); // Creating arraylist

        printGreetings();
        String input = sc.nextLine();
        int count = 1;
        Iterator itr = list.iterator();



        while (!input.equals("bye")) {
            if(!input.equals("list") && !input.contains("done")){ //adding item to list

                Task t = new Task(input);
                list.add(t);
                System.out.println("____________________________________________________________\n" + "added: " + input
                        + "\n____________________________________________________________\n");
            }
            if(input.contains("done")){
                System.out.println("Nice! I've marked this task as done:");
                int itemPos = Integer.parseInt(input.replaceAll("[^0-9]" , ""));
                itemPos-- ;
                list.get(itemPos).markDone();
                System.out.println("[" + list.get(itemPos).getStatus() + "]" + list.get(itemPos).getDescription());
            }
            if(input.equals("list")){
                count = 1;
                System.out.println("____________________________________________________________\n");


                for(int i = 0 ; i < list.size();i++){
                    String icon = list.get(i).getStatus();

                    System.out.println(count + ".[" + icon + "] " + list.get(i).getDescription() );
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
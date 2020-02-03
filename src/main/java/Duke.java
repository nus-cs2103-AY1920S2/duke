
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

        ArrayList<Task> list = new ArrayList<Task>(); // Creating arraylist for list of things
        ArrayList<String> type = new ArrayList<String>(); // arraylist for list type

        printGreetings();
        String input = sc.nextLine();
        int count = 1;
        Iterator itr = list.iterator();



        while (!input.equals("bye")) {
            if(!input.equals("list") && !input.contains("done")){ //adding item to list

                if(input.contains("todo")) {
                    int numOfWords = 0;
                    for (String inputDisect : input.split(" ")) {
                        numOfWords++;
                    }
                    if (numOfWords > 1) {
                        String taskDescription = input.replaceAll("todo ", "");
                        Todo t = new Todo(taskDescription);
                        list.add(t);
                        type.add("T");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("[T][X] " + taskDescription);
                        System.out.println("Now you have " + (list.size()) + " tasks in the list.");

                    }
                    //Error handling when todo doesnt have descriptions
                    else
                        System.out.println("OOPS!!! The description of todo cannot be empty.");
                }

                if(input.contains("deadline")) {

                    int numOfWords = 0;
                    for (String inputDisect : input.split(" ")) {
                        numOfWords++;
                    }
                    if (numOfWords > 1) {
                        String taskDescription = input.replaceAll("deadline ", "");
                        String[] strArray = taskDescription.split("/by ");

                        Deadline t = new Deadline(strArray[0], strArray[1]);
                        list.add(t);
                        type.add("D");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t.toString());
                        System.out.println("Now you have " + (list.size()) + " tasks in the list.");

                    }
                    //Error handling when deadline doesnt have descriptions
                    else
                        System.out.println("OOPS!!! The description of deadline cannot be empty.");
                }
                if(input.contains("event")) {
                    int numOfWords = 0;
                    for (String inputDisect : input.split(" ")) {
                        numOfWords++;
                    }
                    if (numOfWords > 1) {
                        String taskDescription = input.replaceAll("event ", "");
                        String[] strArray = taskDescription.split("/at ");

                        event t = new event(strArray[0], strArray[1]);
                        list.add(t);
                        type.add("E");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t.toString());
                        System.out.println("Now you have " + (list.size()) + " tasks in the list.");
                    }
                    //Error handling when deadline doesnt have descriptions
                    else
                        System.out.println("OOPS!!! The description of deadline cannot be empty.");
                }


            }
            //Error handling when user type words that are not the intended instructions
            if(!input.contains("done") && !input.contains("list") && !input.contains("todo") &&  !input.contains("deadline")
                    && !input.contains("event")){
                System.out.println("OPPS!!! I'm sorry, but I don't know what that means :-( ");
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
                    String addInfo = "";
                    if(type.get(i).equals("E")){
                        addInfo = (list.get(i)).getWhen();
                    }

                    if(type.get(i).equals("D")){
                        addInfo = (list.get(i)).getWhen();
                    }
                    System.out.println(count + ".[" + type.get(i) + "][" + icon + "] "
                            + list.get(i).getDescription() + addInfo);
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
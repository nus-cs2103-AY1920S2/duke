import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> commandList = new ArrayList<>();


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner input = new Scanner(System.in);
        String word = input.nextLine();
        while (!word.equalsIgnoreCase("bye")) {
            System.out.println("____________________________________________________________");
            if (word.equalsIgnoreCase("list")){

                for (int i = 1; i <= commandList.size(); i++) {
                    System.out.println(i + ". " + commandList.get(i-1));
                }
            }
            else {

                commandList.add(word);
                System.out.println("added: " + word);


            }
            System.out.println("____________________________________________________________");
            word = input.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }

}

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> commandList = new ArrayList<>();


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

                for (int i = 0; i < commandList.size(); i++) {
                    System.out.println(commandList.get(i));
                }
            }



            else {
                String[] words = word.split(" ");
                if (words[0].equalsIgnoreCase("done") && words[1].matches("\\d+")) {
                    int doneTarget = Integer.parseInt(words[1]);
                    commandList.get(doneTarget-1).setDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(commandList.get(doneTarget-1));

                }
                else {
                    Task task = new Task(commandList.size() + 1, word);
                    commandList.add(task);
                    System.out.println("added: " + word);
                }
            }
            System.out.println("____________________________________________________________");
            word = input.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }

}

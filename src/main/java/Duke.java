import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        Task[] tasks = new Task[100];
        int i = 0;

        String command = "";

        while(!(command).equals("bye")) {
            String[] current = sc.nextLine().split(" ");
            command = current[0];

            switch (command) {
                case "list":
                    System.out.println("    ____________________________________________________________");
                    for(int count = 0; count < i; count++){
                        System.out.println("    " + (count+1) + ". " + tasks[count].getStatusIcon() + " "
                                + tasks[count].description);
                    }

                    System.out.println("    ____________________________________________________________");
                    break;
                case "done":
                    int value = Integer.parseInt(current[1]);
                    tasks[value-1].markAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Nice! I've marked this task as done: \n");
                    System.out.println("    " + tasks[value-1].getStatusIcon() + " "
                            + tasks[value-1].description);
                    System.out.println("    ____________________________________________________________");
                    break;
                default:
                    System.out.println("    ____________________________________________________________");
                    String description  = String.join(" ", current);
                    System.out.println("    added:  " + description);



                    System.out.println("    ____________________________________________________________");
                    tasks[i] = new Task(description);
                    i++;
                    break;


            }



        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}

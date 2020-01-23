import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        Scanner myObj = new Scanner(System.in);
        int count = 0;
        int count2 = 0;
        int count3 = 1;

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        String userInput = myObj.nextLine();

        while(!userInput.equals("bye")){
            if(userInput.equals("list")){
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the tasks in your list:");
                count2 = 0;
                count3 = 1;
                while(count2 < count){
                    System.out.println("     " + count3 + ".[" + tasks[count2].getStatusIcon() + "] " + tasks[count2].getDescription());
                    count2++;
                    count3++;
                }
                System.out.println("    ____________________________________________________________\n");

            } else if(userInput.equals("done 2")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done: ");
                count2 = 0;
                while(count2 < count){
                    if(tasks[count2].getDone() == true) {
                        System.out.println("       " + tasks[count2].getStatusIcon() + " " + tasks[count2].getDescription());
                    }
                    count2++;
                }
                System.out.println("    ____________________________________________________________");
            } else {
                Task t = new Task(userInput);
                tasks[count] = t;
                count++;
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + userInput);
                System.out.println("    ____________________________________________________________\n");
            }
            userInput = myObj.nextLine();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}

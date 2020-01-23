import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        Scanner myObj = new Scanner(System.in);
        int count = 0;
        int count2 = 0;
        int count3 = 1;
        String content2;
        String content3;

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
                    System.out.println("     " + count3 + "." + tasks[count2].toString());
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
                if(userInput.contains("todo")){
                    content2 = userInput.substring(5);
                    Task t = new Todo(content2);
                    tasks[count] = t;

                } else if(userInput.contains("deadline")){
                    userInput = userInput.substring(9);
                    content2 = userInput.substring(0, userInput.indexOf("/by") - 1);
                    content3 = userInput.substring(userInput.indexOf("/by") + 4);
                    Task t = new Deadline(content2, content3);
                    tasks[count] = t;
                } else {
                    userInput = userInput.substring(6);
                    content2 = userInput.substring(0, userInput.indexOf("/at") - 1);
                    content3 = userInput.substring(userInput.indexOf("/at") + 4);
                    Task t = new Event(content2, content3);
                    tasks[count] = t;
                }
                count++;
            }
            userInput = myObj.nextLine();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}

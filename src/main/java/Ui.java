import java.util.Scanner;

public class Ui {
    public Ui() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }

    public void showLoadingError(){

    }

    public String input(){
        Scanner myObj = new Scanner(System.in);
        String inputData = myObj.nextLine();
        return inputData;
    }

    public static void bye(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void list(TaskList tasks){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        int i = 0;
        int j = 1;
        while (i < tasks.totalTasksCount) {
            System.out.println("     " + j + "." + tasks.totalTasks[i].toString());
            i++;
            j++;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void done(TaskList tasks, String userInput){
        int taskNo = Integer.parseInt(userInput.substring(userInput.indexOf(" ")+1));

        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        tasks.totalTasks[taskNo - 1].markAsDone(); // to mark as done; -1 as since count in totalTasks starts from 0
        System.out.println("       " + tasks.totalTasks[taskNo - 1].getStatusIcon() + " " + tasks.totalTasks[taskNo - 1].getDescription());
        System.out.println("    ____________________________________________________________");
    }
}

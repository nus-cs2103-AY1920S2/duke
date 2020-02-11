import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();
        boolean end = false;

        String opening_message = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println(opening_message);

        while(!end) {
            String next_cmd = sc.nextLine();

            //Command: Bye (Termination of Program)
            if (next_cmd.equals("bye")) {
                end = true;
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
            }

            //Command: List (List out all the Tasks and Completion Status)
            else if (next_cmd.equals("list")) {
                //No Tasks in List
                if (taskList.size() == 0) {
                    System.out.println("    ____________________________________________________________\n" +
                            "     There are no tasks available\n" +
                            "    ____________________________________________________________");
                }

                else {
                    String list_of_tasks = "";
                    for(int i = 0; i < taskList.size(); i++) {
                        list_of_tasks += "     "+ (i+1) + ":" + taskList.get(i) + "\n";
                    }
                    System.out.println("    ____________________________________________________________\n" +
                            list_of_tasks +
                            "    ____________________________________________________________");
                }
            }

            //Command: Done (Mark the particular task as done)
            else if ((next_cmd.split(" ")[0]).equals("done")) {
                int task_num = Integer.parseInt(next_cmd.split(" ")[1]) - 1;
                taskList.get(task_num).markAsDone();
                System.out.println("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done:");
                System.out.println("       " + taskList.get(task_num));
                System.out.println("    ____________________________________________________________");
            }

            else {
                taskList.add(new Task(next_cmd));
                System.out.println("    ____________________________________________________________\n" +
                        "      added: "  + next_cmd + "\n" +
                        "    ____________________________________________________________");
            }
        }
    }
}

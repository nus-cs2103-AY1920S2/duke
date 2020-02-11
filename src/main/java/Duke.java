import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws Exception {
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
                    throw new DukeException("There are no tasks availaible");
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

            //Invalid Command: Command does not have description
            else if (next_cmd.split(" ").length == 1) {
                if (next_cmd.equals("todo") || next_cmd.equals("deadline") || next_cmd.equals("event")) {
                    throw new DukeException("☹ OOPS!!! The description of a " + next_cmd + " cannot be empty.");
                }
                else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }

            //Command: Done (Mark the particular task as done)
            else if ((next_cmd.split(" ")[0]).equals("done")) {


                int task_num = Integer.parseInt(next_cmd.split(" ")[1]) - 1;
                if(task_num > taskList.size() || task_num < 0) {
                    throw new DukeException("Invalid Task Number");
                }
                taskList.get(task_num).markAsDone();
                System.out.println("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done:");
                System.out.println("       " + taskList.get(task_num));
                System.out.println("    ____________________________________________________________");
            }
            else if ((next_cmd.split(" ")[0]).equals("delete")) {
                int task_num = Integer.parseInt(next_cmd.split(" ")[1]) - 1;
                if (task_num > taskList.size() || task_num < 0) {
                    throw new DukeException("Invalid Task Number");
                } else {
                    System.out.println("    ____________________________________________________________\n" +
                            "     Noted. I've removed this task:");
                    System.out.println("       " + taskList.get(task_num));
                    taskList.remove(task_num);
                    if (taskList.size() > 1) {
                        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                    } else {
                        System.out.println("     Now you have " + taskList.size() + " task in the list.");
                    }
                    System.out.println("    ____________________________________________________________");
                }
            }

            //Add Task to taskList
            else {
                Task t = null;
                String taskType = next_cmd.split(" ")[0];
                next_cmd = next_cmd.substring(next_cmd.indexOf(" "));
                switch (taskType) {
                    case "todo":
                        t = new ToDo(next_cmd);
                        break;
                    case "event":
                        t = new Event(next_cmd.split("/at")[0], next_cmd.split("/at")[1]);
                        break;
                    case "deadline":
                        t = new Deadline(next_cmd.split("/by")[0], next_cmd.split("/by")[1]);
                        break;
                    default: throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                taskList.add(t);
                System.out.println("    ____________________________________________________________\n" +
                        "      Got it. I've added this task: \n" + "       " +
                        t + "\n" + "     Now you have " + taskList.size() + " tasks in the list.\n" +
                        "    ____________________________________________________________");
            }
        }
    }
}

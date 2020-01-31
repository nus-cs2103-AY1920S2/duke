import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<Task>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Default messages in Duke
        String line = "    ____________________________________________________________";
        String greeting = "     Hello! I'm Duke\n" + "     What can I do for you?";
        String add = "     added: ";
        String exit = line + "\n" + "     Bye. Hope to see you again soon!" + "\n" + line;
        String list_Header = "     Here are the tasks in your list:";
        String task_Done_Message = "     Nice! I've marked this task as done:";
        String task_Add_Message = "     Got it. I've added this task:";
        String delete_Message = "     Noted. I've removed this task:";

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        try {

            String input = br.readLine();
            String[] command = input.split(" ");

            while (!command[0].equals("bye")) {
                if (command[0].equals("list")) {

                    // List command lists out all tasks
                    System.out.println(line);
                    System.out.println(list_Header);
                    for (int i = 0; i < tasks.size(); i++) {
                        String output = "     " + (i + 1) + "." + tasks.get(i);
                        System.out.println(output);
                    }
                    System.out.println(line);

                    input = br.readLine();
                    command = input.split(" ");
                } else if (command[0].equals("done")) {
                    int task_Done = Integer.parseInt(command[1]);

                    // Checks the task as 'Done'
                    tasks.get(task_Done - 1).taskDone();

                    System.out.println(line);
                    System.out.println(task_Done_Message);
                    System.out.println("       " + tasks.get(task_Done - 1));
                    System.out.println(line);

                    input = br.readLine();
                    command = input.split(" ");
                } else if(command[0].equals("todo") || command[0].equals("deadline") || command[0].equals("event")) {

                    // Any other command besides list, done, bye
                    String type = command[0];
                    try {
                        // Test string to see if exception should be thrown
                        String test = command[1];

                        if(command[0].equals("todo)")) {
                            Task new_Task = new Todo(type, input);
                            tasks.add(new_Task);
                        } else if(command[0].equals("deadline")) {
                            Task new_Task = new Deadline(type, input);
                            tasks.add(new_Task);
                        } else {
                            Task new_Task = new Event(type, input);
                            tasks.add(new_Task);
                        }


                        // Putting the output string together
                        String output = line + "\n" +
                                task_Add_Message + "\n" +
                                "       " + tasks.get(tasks.size() - 1) + "\n";
                        output += "     Now you have " + tasks.size() + " task(s) in the list." + "\n" + line;
                        System.out.println(output);

                        // Reset for new command
                        input = br.readLine();
                        command = input.split(" ");
                    } catch (Exception e) {
                        System.out.println(new DukeException(type));
                        input = br.readLine();
                        command = input.split(" ");
                    }
                } else if(command[0].equals("delete")) {

                    // Identify index to remove task from
                    int position = Integer.parseInt(command[1]);

                    // Putting output string together
                    String output = line + "\n" + delete_Message + "\n" + "       " + tasks.get(position - 1) + "\n";
                    output += "     Now you have " + (tasks.size() - 1) + " task(s) in the list." + "\n" + line;
                    System.out.println(output);
                    tasks.remove(position - 1);

                    // Reset for new command
                    input = br.readLine();
                    command = input.split(" ");
                } else {
                    System.out.println(new DukeException("error"));
                    input = br.readLine();
                    command = input.split(" ");
                }
            }

            br.close();
            System.out.println(exit);
        } catch(Exception e) {
            System.err.println(e);
        }
    }
}

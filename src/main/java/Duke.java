import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList= new ArrayList<Task>();
        //String[] taskDes = new String[100];
        int x = 1;
        int y = 0;
        while(true) {
            String in = sc.nextLine();
            Task T = new Task(in);

            if(in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            else if(in.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                x = 1;
                for(Task A: taskList) {
                    System.out.printf("%d.%s\n", x, A);
                    x++;
                }
            }

            else if(in.substring(0,Math.min(in.length(), 6)).equals("delete")) {
                int toDel = Integer.parseInt(in.substring(7));
                System.out.println("Noted. I've removed this task:");
                System.out.println(taskList.get(toDel - 1));
                taskList.remove(toDel - 1);
                System.out.printf("Now you have %s tasks in the list\n", taskList.size());
            }

            else if(in.substring(0,Math.min(in.length(), 4)).equals("done")) {
                int index = Integer.parseInt(in.substring(5,in.length()));
                taskList.get(index - 1).doTask();
                System.out.println("Nice, I've marked this task as done:");
                //System.out.printf("[%s] %s\n", taskList.get(index - 1).getStatusIcon(), taskList.get(index - 1).getDescription());
                System.out.println(taskList.get(index - 1));

            }

            else {

                try {
                    String[] commands = in.split(" /");
                    String[] eventType = commands[0].split(" ");

                    if(!((eventType[0].equals("todo"))|| eventType[0].equals("deadline")|| eventType[0].equals("event"))) {
                        throw new DukeException("I'm sorry, but I do not know what that means :-(");
                    }

                    if(eventType.length == 1) {
                        throw new DukeException("The description of a " + commands[0] + " cannot be empty.");
                    }

                    if (eventType[0].equals("event")) {
                        String[] subS = commands[1].split(" ");
                        taskList.add(new Event(commands[0].substring(6, commands[0].length()), subS[0], commands[1].substring(subS[0].length())));
                    } else if (eventType[0].equals("deadline")) {
                        taskList.add(new Deadline(commands[0].substring(9, commands[0].length()), commands[1].substring(3)));
                    } else if (eventType[0].equals("todo")) {
                        taskList.add(new ToDo(commands[0].substring(5, commands[0].length())));
                    }

                    System.out.printf("Got it. I've added this task:\n");
                    System.out.println("  " +taskList.get(taskList.size() - 1));
                    if(taskList.size() < 2) {
                        System.out.println("Now you have 1 task in the list.");
                    }
                    else {
                        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                    }
                }

                catch (DukeException e) {
                    System.out.println(e);
                    //break;
                }

            }
        }

    }
}

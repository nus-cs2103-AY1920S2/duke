import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    static int i;
    static Task[] tasks;
    public static void main(String[] args) throws DukeException{
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        tasks = new Task[100];
        String command = "";

        while (!(command).equals("bye")) {
            String[] current = sc.nextLine().split(" ");
            command = current[0];
            if ((command).equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            }

            if (command.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("      Here are the tasks in your list: \n");
                for (int count = 0; count < i; count++) {
                    System.out.print("     " + (count+1) + ". ");
                    if (tasks[i] instanceof Deadline) {
                        System.out.println(((Deadline)tasks[count]).toString());
                    } else if (tasks[i] instanceof EventObj){
                        System.out.println(((EventObj)tasks[count]).toString());
                    } else{
                        System.out.println(tasks[count]);
                    }
                }

                System.out.println("    ____________________________________________________________");
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                try {
                    addTask(current);
                } catch(DukeException ex){
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + ex.getMessage());
                    System.out.println("    ____________________________________________________________");
                }

            } else if (command.equals("done")) {

                int value = Integer.parseInt(current[1]);
                tasks[value - 1].markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done: \n");
                System.out.println("    " + tasks[value - 1].getStatusIcon() + " "
                        + tasks[value - 1].description);
                System.out.println("    ____________________________________________________________");
            } else {
                try {
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }catch(Exception ex){
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + ex.getMessage());
                    System.out.println("    ____________________________________________________________");
                }
            }


        }

    }

    public static void addTask(String[] current) throws DukeException{
        String[] words =  Arrays.stream(current).skip(1).toArray(String[]::new);
        String command = current[0];

        if (words.length == 0) {
            throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
        }
        if (command.equals("deadline")){
            int position = 0 ;
            boolean specifyDate = false;
            for (String w: words){
                if (w.equals("/by")){
                    String description = String.join(" ", List.of(words).subList(0, position));
                    String date = List.of(words).stream().skip(position+1).collect(Collectors.joining(" "));
                    tasks[i] = new Deadline(description, date);
                    specifyDate = true;
                    break;
                } else{
                    position++;
                }

            }

            if (!specifyDate){
                String description = String.join(" ", words);
                tasks[i] = new Deadline(description, "");
            }

        } else if (command.equals("event")){
            int position = 0 ;
            boolean specifyDate = false;
            for (String w: words){
                if (w.equals("/at")){
                    String description = String.join(" ", List.of(words).subList(0, position));
                    String datetime = List.of(words).stream().skip(position+1).collect(Collectors.joining(" "));
                    tasks[i] = new EventObj(description, datetime);
                    specifyDate = true;
                    break;
                } else{
                    position++;
                }

            }

            if (!specifyDate){
                String description = String.join(" ", words);
                tasks[i] = new EventObj(description, "");
            }

        } else {
            tasks[i] = new Task(String.join(" ", words));

        }

        tasks[i].setType(command);

        System.out.println("    ____________________________________________________________");
        System.out.println("      Got it. I've added this task:  \n");

        System.out.print("      ");
        if (tasks[i] instanceof Deadline) {
            System.out.println(((Deadline)tasks[i]).toString());
        } else if (tasks[i] instanceof EventObj){
            System.out.println(((EventObj)tasks[i]).toString());
        } else{
            System.out.println(tasks[i]);
        }
        i++;
        System.out.println("      Now you have " + i + " tasks in the list.  \n");
        System.out.println("    ____________________________________________________________");
    }

}

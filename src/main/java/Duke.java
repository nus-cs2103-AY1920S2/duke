import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    static int i;
    static List<Task> tasks;
    public static void main(String[] args) throws DukeException{
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        tasks = new ArrayList<>();
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
                    System.out.println(tasks.get(count));
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
                Task cur = tasks.get(value - 1);
                cur.markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done: \n");
                System.out.println("    " + cur.getStatusIcon() + " "
                        + cur.getDescription());
                System.out.println("    ____________________________________________________________");
            } else if (command.equals("delete")) {
                int value = Integer.parseInt(current[1]);
                Task cur = tasks.get(value - 1);
                tasks.remove(cur);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Noted. I've removed this task: \n");
                System.out.println("    " + cur);
                i--;
                System.out.println("      Now you have " + i + " tasks in the list.  \n");
                System.out.println("    ____________________________________________________________");

            }else
             {
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
                    Task t = new Deadline(description, date);
                    t.setType(command);
                    tasks.add(i, t);
                    specifyDate = true;
                    break;
                } else{
                    position++;
                }

            }

            if (!specifyDate){
                String description = String.join(" ", words);
                Task t = new Deadline(description, "");
                t.setType(command);
                tasks.add(i, t);
            }

        } else if (command.equals("event")){
            int position = 0 ;
            boolean specifyDate = false;
            for (String w: words){
                if (w.equals("/at")){
                    String description = String.join(" ", List.of(words).subList(0, position));
                    String datetime = List.of(words).stream().skip(position+1).collect(Collectors.joining(" "));
                    Task t = new EventObj(description, datetime);
                    t.setType(command);
                    tasks.add(i, t);
                    specifyDate = true;
                    break;
                } else{
                    position++;
                }

            }

            if (!specifyDate){
                String description = String.join(" ", words);
                Task t = new EventObj(description, "");
                t.setType(command);
                tasks.add(i, t);
            }

        } else {
            Task t = new Task(String.join(" ", words));
            t.setType(command);
            tasks.add(i, t);

        }


        System.out.println("    ____________________________________________________________");
        System.out.println("      Got it. I've added this task:  \n");

        System.out.print("      ");
        Task t = tasks.get(i);
        if (t instanceof Deadline) {
            System.out.println(t.toString());
        } else if (t instanceof EventObj){
            System.out.println(((EventObj)t).toString());
        } else{
            System.out.println(t);
        }
        i++;
        System.out.println("      Now you have " + i + " tasks in the list.  \n");
        System.out.println("    ____________________________________________________________");
    }

}

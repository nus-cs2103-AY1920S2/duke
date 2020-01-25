import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String indent = "    ";
    private static String line = "    ____________________________________________________________";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> toDo = new ArrayList<Task>();

        String logo = indent + " ____        _        \n"
                + indent + "|  _ \\ _   _| | _____ \n"
                + indent + "| | | | | | | |/ / _ \\\n"
                + indent + "| |_| | |_| |   <  __/\n"
                + indent + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "\n" + indent + "Hello! I'm Snow\n" + indent + "What can I do for you?\n" + logo + line);

        String input = "";
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            if (input.equals("bye")){
                System.out.println(line);
                System.out.println(indent + "Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equals("list")){
                System.out.println(line);
                System.out.println(indent + "Here are the tasks in your list:");
                for (int i = 0; i < toDo.size(); i++){
                    System.out.println(indent + String.valueOf(i + 1) + "." + toDo.get(i));
                }
                System.out.println(line);
            } else if (input.split(" ")[0].equals("done")){
                if (input.split(" ").length == 1){
                    System.out.println(line);
                    System.out.println(indent + "☹ OOPS!!! The number of a done command cannot be empty.");
                    System.out.println(line);
                } else {
                    try{
                        int num = Integer.parseInt(input.split(" ")[1]);
                        if (toDo.size() == 0){
                            System.out.println(line);
                            System.out.println(indent + "☹ OOPS!!! You have no tasks currently.");
                            System.out.println(line);
                        }  else if (num  == 0){
                            System.out.println(line);
                            System.out.println(indent + "☹ OOPS!!! Task number cannot be 0");
                            System.out.println(line);
                        } else if (num > toDo.size()){
                            System.out.println(line);
                            System.out.println(indent + "☹ OOPS!!! The number of a done command cannot be greater than the number of tasks " +
                                    "you have.");
                            System.out.println(line);
                        } else {
                            toDo.get(num - 1).markAsDone();
                            System.out.println(line);
                            System.out.println(indent + "Nice! I've marked this task as done: ");
                            System.out.println(indent + indent + toDo.get(num - 1));
                            System.out.println(line);
                        }
                    }catch (NumberFormatException ex) {
                        //handle exception here
                        System.out.println(line);
                        System.out.println(indent + "☹ OOPS!!! Enter a valid number after done command");
                        System.out.println(line);
                    }
                }
            } else if (input.split(" ")[0].equals("todo")){
                if (input.split(" ").length == 1){
                    System.out.println(line);
                    System.out.println(indent + "☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(line);
                } else {
                    String description = input.replaceFirst("todo ", "");
                    Task newTask = new ToDos(description);
                    toDo.add(newTask);
                    System.out.println(line);
                    System.out.println(indent + "Got it. I've added this task:");
                    System.out.println(indent + indent + newTask);
                    System.out.println(indent + "Now you have " + String.valueOf(toDo.size()) + " tasks in the list.");
                    System.out.println(line);
                }

            } else if (input.split(" ")[0].equals("deadline")){
                if (input.split(" ").length == 1){
                    System.out.println(line);
                    System.out.println(indent + "☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println(line);
                } else {
                    String description = input.replaceFirst("deadline ", "").split("/")[0].trim();
                    if (!input.contains("/by ")) {
                        System.out.println(line);
                        System.out.println(indent + "☹ OOPS!!! The /by of a deadline cannot be empty.");
                        System.out.println(line);
                    } else if (input.split(" ").length < 4) {
                        System.out.println(line);
                        System.out.println(indent + "☹ OOPS!!! The task or /by of a deadline cannot be empty.");
                        System.out.println(line);
                    } else {
                        String datetime = input.replaceFirst("deadline ", "").split("/")[1].replaceFirst("by ", "");
                        Task newTask = new Deadlines(description, datetime);
                        toDo.add(newTask);
                        System.out.println(line);
                        System.out.println(indent + "Got it. I've added this task:");
                        System.out.println(indent + indent + newTask);
                        System.out.println(indent + "Now you have " + String.valueOf(toDo.size()) + " tasks in the list.");
                        System.out.println(line);
                    }
                }
            } else if (input.split(" ")[0].equals("event")){
                if (input.split(" ").length == 1){
                    System.out.println(line);
                    System.out.println(indent + "☹ OOPS!!! The description of an event cannot be empty.");
                    System.out.println(line);
                } else {
                    if (!input.contains("/at ")) {
                        System.out.println(line);
                        System.out.println(indent + "☹ OOPS!!! The /at of an event cannot be empty.");
                        System.out.println(line);
                    } else if (input.split(" ").length < 4) {
                        System.out.println(line);
                        System.out.println(indent + "☹ OOPS!!! The task or /at of an event cannot be empty.");
                        System.out.println(line);
                    } else {
                        String description = input.replaceFirst("event ", "").split("/")[0].trim();
                        String datetime = input.replaceFirst("event ", "").split("/")[1].replaceFirst("at ", "");
                        Task newTask = new Events(description, datetime);
                        toDo.add(newTask);
                        System.out.println(line);
                        System.out.println(indent + "Got it. I've added this task:");
                        System.out.println(indent + indent + newTask);
                        System.out.println(indent + "Now you have " + String.valueOf(toDo.size()) + " tasks in the list.");
                        System.out.println(line);
                    }
                }

            } else {
                System.out.println(line);
                System.out.println(indent + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
            }
        }


    }
}

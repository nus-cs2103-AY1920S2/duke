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
            Command userCommand = Command.OTHERS;

            if (input.equals("bye")){
                userCommand = Command.BYE;
            } else if (input.equals("list")){
                userCommand = Command.LIST;
            } else if (input.split(" ")[0].equals("done")){
                userCommand = Command.DONE;
            }  else if (input.split(" ")[0].equals("delete")){
                userCommand = Command.DELETE;
            } else if (input.split(" ")[0].equals("todo")){
                userCommand = Command.TODOS;
            } else if (input.split(" ")[0].equals("deadline")){
                userCommand = Command.DEADLINES;
            } else if (input.split(" ")[0].equals("event")){
                userCommand = Command.EVENTS;
            }

            switch (userCommand) {
                case BYE:
                    System.out.println(line);
                    System.out.println(indent + "Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                case LIST:
                    System.out.println(line);
                    System.out.println(indent + "Here are the tasks in your list:");
                    for (int i = 0; i < toDo.size(); i++){
                        System.out.println(indent + String.valueOf(i + 1) + "." + toDo.get(i));
                    }
                    System.out.println(line);
                    break;
                case DONE:
                    if (input.split(" ").length == 1){
                        printError("☹ OOPS!!! The number of a done command cannot be empty.");
                    } else {
                        try{
                            int num = Integer.parseInt(input.split(" ")[1]);
                            if (toDo.size() == 0){
                                printError("☹ OOPS!!! You have no tasks currently.");
                            }  else if (num  == 0){
                                printError("☹ OOPS!!! Task number cannot be 0");
                            } else if (num > toDo.size()){
                                printError("☹ OOPS!!! The number of a done command cannot be greater than the number of tasks " +
                                        "you have.");
                            } else {
                                toDo.get(num - 1).markAsDone();
                                System.out.println(line);
                                System.out.println(indent + "Nice! I've marked this task as done: ");
                                System.out.println(indent + indent + toDo.get(num - 1));
                                System.out.println(line);
                            }
                        }catch (NumberFormatException ex) {
                            //handle exception here
                            printError("☹ OOPS!!! Enter a valid number after done command");
                        }
                    }
                    break;
                case DELETE:
                    if (input.split(" ").length == 1){
                        printError("☹ OOPS!!! The number of a delete command cannot be empty.");
                    } else {
                        try{
                            int num = Integer.parseInt(input.split(" ")[1]);
                            if (toDo.size() == 0){
                                printError("☹ OOPS!!! You have no tasks currently.");
                            }  else if (num  == 0){
                                printError("☹ OOPS!!! Task number cannot be 0");
                            } else if (num > toDo.size()){
                                printError("☹ OOPS!!! The number of a delete command cannot be greater than the number of tasks " +
                                        "you have.");
                            } else {
                                Task removedTask = toDo.remove(num - 1);
                                System.out.println(line);
                                System.out.println(indent + "Noted. I've removed this task: ");
                                System.out.println(indent + indent + removedTask);
                                System.out.println(indent + "Now you have " + String.valueOf(toDo.size()) + " tasks in the list.");
                                System.out.println(line);
                            }
                        }catch (NumberFormatException ex) {
                            //handle exception here
                            printError("☹ OOPS!!! Enter a valid number after done command");
                        }
                    }
                    break;
                case TODOS:
                    if (input.split(" ").length == 1){
                        printError("☹ OOPS!!! The description of a todo cannot be empty.");
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
                    break;
                case DEADLINES:
                    if (input.split(" ").length == 1){
                        printError("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        String description = input.replaceFirst("deadline ", "").split("/")[0].trim();
                        if (!input.contains("/by ")) {
                            printError("☹ OOPS!!! The /by of a deadline cannot be empty.");
                        } else if (input.split(" ").length < 4) {
                            printError("☹ OOPS!!! The task or /by of a deadline cannot be empty.");
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
                    break;
                case EVENTS:
                    if (input.split(" ").length == 1){
                        printError("☹ OOPS!!! The description of an event cannot be empty.");
                    } else {
                        if (!input.contains("/at ")) {
                            printError("☹ OOPS!!! The /at of an event cannot be empty.");
                        } else if (input.split(" ").length < 4) {
                            printError("☹ OOPS!!! The task or /at of an event cannot be empty.");
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
                    break;

                default:
                    printError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }

        }
    }

    public static void printError(String message){
        System.out.println(line);
        System.out.println(indent + message);
        System.out.println(line);
    }
}

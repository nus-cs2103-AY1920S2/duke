import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> commandList = new ArrayList<>();


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner input = new Scanner(System.in);
        String word = input.nextLine();
        while (!word.equalsIgnoreCase("bye")) {
            System.out.println("____________________________________________________________");
            if (word.equalsIgnoreCase("list")) {
                if (commandList.size() > 0) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < commandList.size(); i++) {
                        int a = i + 1;
                        System.out.print(a + ". " + commandList.get(i));
                    }
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, I can't find any task");
                }
            }



            else {
                String[] words = word.split(" ");

                if (words[0].equalsIgnoreCase("done") && words[1].matches("\\d+")) {
                    int doneTarget = Integer.parseInt(words[1]);
                    if (doneTarget > 0 && doneTarget <= commandList.size()) {
                        commandList.get(doneTarget - 1).setDone();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(commandList.get(doneTarget - 1));
                    } else {
                        System.out.println("☹ OOPS!!! I'm sorry, I can't find that task");
                    }

                }

                else if (words[0].equalsIgnoreCase("delete") && words[1].matches("\\d+")) {
                    int deleteTarget = Integer.parseInt(words[1]);
                    if (deleteTarget > 0 && deleteTarget <= commandList.size()) {
                        Task task = commandList.get(deleteTarget - 1);
                        commandList.remove(deleteTarget - 1);
                        System.out.println("Noted. I've removed this task: ");
                        System.out.print(task);
                        System.out.println("Now you have "+commandList.size()+" tasks in the list.");
                    } else {
                        System.out.println("☹ OOPS!!! I'm sorry, I can't find that task");
                    }

                }
                else if (words[0].equalsIgnoreCase("todo")){
                    if (word.contains("todo ") && !word.substring(5).isEmpty()) {
                        String substr = word.substring(5);
                        ToDo task = new ToDo(substr);
                        commandList.add(task);
                        System.out.println("Got it. I've added this task: ");
                        System.out.print(task);
                        System.out.println("Now you have " + commandList.size()+ " tasks in the list.");
                    } else {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                }

                else if (words[0].equalsIgnoreCase("deadline")){
                    String substr = word.substring(9);
                    if (substr.contains(" /")) {
                    String[] deadlineSplit = substr.split(" /");
                    Deadline task = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                    commandList.add(task);
                    System.out.println("Got it. I've added this task: ");
                    System.out.print(task);
                    System.out.println("Now you have "+commandList.size()+" tasks in the list.");
                    }
                    else {
                        System.out.println("☹ OOPS!!! I'm sorry, but you need to specify the deadline");
                    }

                }

                else if (words[0].equalsIgnoreCase("event")){
                    String substr = word.substring(9);
                    if (substr.contains(" /")) {
                    String[] eventSplit = substr.split(" /");
                    Event task = new Event(eventSplit[0], eventSplit[1]);
                    commandList.add(task);
                    System.out.println("Got it. I've added this task: ");
                    System.out.print(task);
                    System.out.println("Now you have "+commandList.size()+" tasks in the list.");}
                    else {
                        System.out.println("☹ OOPS!!! I'm sorry, but you need to specify the event time");
                    }
                }
                else{
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }


            }
            System.out.println("____________________________________________________________");
            word = input.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }

}

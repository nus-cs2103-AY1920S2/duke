import java.util.*;

public class Duke {
    public static void main(String[] args) {
/*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
*/
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        ArrayList <Task> arr = new ArrayList<Task>();

        do {
            userInput = sc.nextLine();
            if (userInput.equals("list")) {                                                  //Lists out the tasks
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i <= arr.size(); i++) {
                    System.out.println(i + "." + arr.get(i - 1).getIcon() + arr.get(i - 1).status + " " + arr.get(i - 1).getDescription());
                }
            }
            else if (userInput.split("\\s")[0].equals("done")) {
                int doneTask = Integer.parseInt(userInput.split("\\s")[1]);
                arr.get(doneTask - 1).setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(arr.get(doneTask - 1).status + " " + arr.get(doneTask - 1).getDescription());
            }
            else if (userInput.split("\\s")[0].equals("todo")) {
                if (userInput.split("\\s").length == 1) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                else {
                    String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. [return, book]
                    String taskDescription = "";
                    for (int i = 0; i < taskRequest.length; i++) {
                        if (i == 0) {
                            taskDescription += taskRequest[i];
                        } else {
                            taskDescription += " " + taskRequest[i];
                        }
                    }
                    Task curr = new Task("todo", taskDescription);
                    arr.add(curr);
                    if (!userInput.equals("bye")) {
                        System.out.println("Got it. I've added this task:");
                        System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    }
                }
            }
            else if (userInput.split("\\s")[0].equals("deadline")) {
                if (userInput.split("\\s").length == 1) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                else {
                    String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. [return, book]
                    String taskDescriptionDate = "";
                    for (int i = 0; i < taskRequest.length; i++) {
                        if (i == 0) {
                            taskDescriptionDate += taskRequest[i];
                        } else {
                            taskDescriptionDate += " " + taskRequest[i];
                        }
                    }
                    String taskDescription = taskDescriptionDate.split("/")[0];
                    Task curr = new Task("deadline", taskDescription);
                    curr.addDate(taskDescriptionDate.split("/")[1]);
                    arr.add(curr);
                    if (!userInput.equals("bye")) {
                        System.out.println("Got it. I've added this task:");
                        System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    }
                }
            }
            else if (userInput.split("\\s")[0].equals("event")) {
                if (userInput.split("\\s").length == 1) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                }
                else {
                    String[] taskRequest = Arrays.copyOfRange(userInput.split("\\s"), 1, userInput.split("\\s").length);    // e.g. [return, book]
                    String taskDescriptionDate = "";
                    for (int i = 0; i < taskRequest.length; i++) {
                        if (i == 0) {
                            taskDescriptionDate += taskRequest[i];
                        } else {
                            taskDescriptionDate += " " + taskRequest[i];
                        }
                    }
                    String taskDescription = taskDescriptionDate.split("/")[0];
                    Task curr = new Task("event", taskDescription);
                    curr.addDate(taskDescriptionDate.split("/")[1]);
                    arr.add(curr);
                    if (!userInput.equals("bye")) {
                        System.out.println("Got it. I've added this task:");
                        System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    }
                }
            }
            else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }


        } while (!userInput.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}

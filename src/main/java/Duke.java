import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Duke {

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("./" + "data/duke.txt",true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void fileUpdate(File dataFile, ArrayList <Task> arr) {
        try {
            dataFile.delete();
            for (int i = 0; i < arr.size(); i++) {
                writeToFile(arr.get(i).getIcon() + " | " + arr.get(i).getStatusBinary() + " | " + arr.get(i).getDescription() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        ArrayList <Task> arr = new ArrayList<Task>();
        File dataFile = new File("./" + "data/duke.txt");

        do {
            userInput = sc.nextLine();

            // LIST
            if (userInput.equals("list")) {                                                  //Lists out the tasks
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i <= arr.size(); i++) {
                    System.out.println(i + "." + arr.get(i - 1).getIcon() + arr.get(i - 1).status + " " + arr.get(i - 1).getDescription());
                }
            }

            // DONE
            else if (userInput.split("\\s")[0].equals("done")) {
                int doneTask = Integer.parseInt(userInput.split("\\s")[1]);
                arr.get(doneTask - 1).setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(arr.get(doneTask - 1).status + " " + arr.get(doneTask - 1).getDescription());
                fileUpdate(dataFile, arr);
            }

            // TO DO
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
                    fileUpdate(dataFile, arr);
                    if (!userInput.equals("bye")) {
                        System.out.println("Got it. I've added this task:");
                        System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    }
                }
            }

            // DEADLINE
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
                    fileUpdate(dataFile, arr);
                    if (!userInput.equals("bye")) {
                        System.out.println("Got it. I've added this task:");
                        System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    }
                }
            }

            // EVENT
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
                    fileUpdate(dataFile, arr);
                    if (!userInput.equals("bye")) {
                        System.out.println("Got it. I've added this task:");
                        System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    }
                }
            }

            //DELETE
            else if (userInput.split("\\s")[0].equals("delete")) {
                int removedTask = Integer.parseInt(userInput.split("\\s")[1]);
                System.out.println("Noted. I've removed this task:");
                System.out.println(arr.get(removedTask - 1).getIcon() + arr.get(removedTask - 1).status + " " + arr.get(removedTask - 1).getDescription());
                arr.remove(removedTask - 1);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                fileUpdate(dataFile, arr);
            }
            else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }


        } while (!userInput.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}

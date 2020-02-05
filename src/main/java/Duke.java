import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private File dataFile;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        dataFile = new File(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (InstantiationError e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList <Task>());
        }
    }

    /**
     * Runs the Duke program by writing and loading from data.txt.
     *
     */

    public void run() {
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        this.ui.runGreeting();
        do {
            userInput = sc.nextLine();
            Parser parser = new Parser();
            String parserOutput = parser.parse(userInput);                // gives parser the user command
            ArrayList <Task> arr = tasks.getArraylist(); // returns the arraylist form of the tasklist
            switch (parserOutput) {

                case ("list"):
                    System.out.println("Here are the tasks in your list: ");
                    tasks.print();  // sysout all the items in the tasklist
                    break;

                case ("done"):
                    int doneTask = Integer.parseInt(userInput.split("\\s")[1]);
                    arr.get(doneTask - 1).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(arr.get(doneTask - 1).status + " " + arr.get(doneTask - 1).getDescription());

                    // write the new updated arr to the duke.txt
                    storage.fileUpdate(this.dataFile, arr);
                    break;
                case ("todo"):
                    if (userInput.split("\\s").length == 1) {
                        System.out.println("&#x2639; OOPS!!! The description of a todo cannot be empty.");
                    } else {
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
                        storage.fileUpdate(dataFile, arr);
                        if (!userInput.equals("bye")) {
                            System.out.println("Got it. I've added this task:");
                            System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
                            System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        }
                    }
                    break;
                case ("deadline"):
                    if (userInput.split("\\s").length == 1) {
                        System.out.println("&#x2639; OOPS!!! The description of a deadline cannot be empty.");
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
                        storage.fileUpdate(dataFile, arr);
                        if (!userInput.equals("bye")) {
                            System.out.println("Got it. I've added this task:");
                            System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
                            System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        }
                    }
                    break;
                case ("event"):
                    if (userInput.split("\\s").length == 1) {
                        System.out.println("&#x2639; OOPS!!! The description of an event cannot be empty.");
                    } else {
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
                        storage.fileUpdate(dataFile, arr);
                        if (!userInput.equals("bye")) {
                            System.out.println("Got it. I've added this task:");
                            System.out.println(curr.getIcon() + curr.status + " " + curr.getDescription());
                            System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        }
                    }
                    break;
                case ("delete"):
                    int removedTask = Integer.parseInt(userInput.split("\\s")[1]);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(arr.get(removedTask - 1).getIcon() + arr.get(removedTask - 1).status + " " + arr.get(removedTask - 1).getDescription());
                    arr.remove(removedTask - 1);
                    System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    storage.fileUpdate(dataFile, arr);
                    break;
                default:
                    System.out.println("&#x2639; OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } while (!userInput.equals("bye"));
    }

    public static void main(String[] args) {
        new Duke("./" + "data/duke.txt").run();
    }
}
/*
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
                    System.out.println("&#x2639; OOPS!!! The description of a todo cannot be empty.");
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
                    System.out.println("&#x2639; OOPS!!! The description of a deadline cannot be empty.");
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
                    System.out.println("&#x2639; OOPS!!! The description of an event cannot be empty.");
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
                System.out.println("&#x2639; OOPS!!! I'm sorry, but I don't know what that means :-(");
            }


        } while (!userInput.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}

*/
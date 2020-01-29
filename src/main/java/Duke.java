import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Scanner sc;
    private ArrayList<Task> tasks;
    private File f;

    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new ArrayList<>(100);
        this.f = new File("data/duke.txt");
    }

    private void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        loadFromFile(f);
    }

    private void echo(String input) {
        System.out.println("\tI'm sorry, but I don't know what that means!");
        this.waitInput();
    }

    private void printTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + this.tasks.get(i));
        }
        this.waitInput();
    }

    private void waitInput() {
        String input = sc.nextLine();
        String[] cmd = input.split("\\s", 2);
        switch (cmd[0].toLowerCase()) {
        case "todo":
            if (cmd.length < 2 || cmd[1].trim().equals("")) {
                System.out.println("\tOOPS! The description of a todo cannot be empty.");
                this.waitInput();
            } else {
                Task newTask = new Todo(cmd[1].trim());
                try {
                    FileWriter append = new FileWriter("data/duke.txt", true);
                    if (tasks.size() == 0) {
                        append.write("T | 0 | " + cmd[1]);
                    } else {
                        append.write("\nT | 0 | " + cmd[1]);
                    }
                    append.close();
                    this.addTask(newTask);
                } catch (FileNotFoundException e) {
                    System.out.println("\tFile not found!");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
        case "event":
            if (cmd.length < 2 || cmd[1].trim().equals("")) {
                System.out.println("\tOOPS! The description of a event cannot be empty.");
                this.waitInput();
            } else {
                String[] info = cmd[1].split("/at");
                if (info.length < 2 || info[1].trim().equals("")) {
                    System.out.println("\tOOPS! Please input /at Date Time (e.g. /at Mon 2-4pm)");
                    this.waitInput();
                } else {
                    Task newTask = new Event(info[0].trim(), info[1].trim());
                    try {
                        FileWriter append = new FileWriter("data/duke.txt", true);
                        if (tasks.size() == 0) {
                            append.write("E | 0 | " + info[0].trim() + " | " + info[1].trim());
                        } else {
                            append.write("\nE | 0 | " + info[0].trim() + " | " + info[1].trim());
                        }
                        append.close();
                        this.addTask(newTask);
                    } catch (FileNotFoundException e) {
                        System.out.println("\tFile not found!");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            break;
        case "deadline":
            if (cmd.length < 2 || cmd[1].trim().equals("")) {
                System.out.println("\tOOPS! The description of a deadline cannot be empty.");
                this.waitInput();
            } else {
                String[] info = cmd[1].split("/by");
                if (info.length < 2 || info[1].trim().equals("")) {
                    System.out.println("\tOOPS! Please input /by Date (e.g. /by Feb 3rd)");
                    this.waitInput();
                } else {
                    Task newTask = new Deadline(info[0].trim(), info[1].trim());
                    try {
                        FileWriter append = new FileWriter("data/duke.txt", true);
                        if (tasks.size() == 0) {
                            append.write("D | 0 | " + info[0].trim() + " | " + info[1].trim());
                        } else {
                            append.write("\nD | 0 | " + info[0].trim() + " | " + info[1].trim());
                        }
                        append.close();
                        this.addTask(newTask);
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found!");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            break;
        case "delete":
            if (cmd.length < 2 || cmd[1].trim().equals("")) {
                System.out.println("\tOOPS! Please enter an index to delete!");
                this.waitInput();
            } else {
                deleteTask(cmd[1]);
            }
            break;
        case "list":
            printTasks();
            break;
        case "done":
            if (cmd.length < 2 || cmd[1].trim().equals("")) {
                System.out.println("\tOOPS! Please enter an index to mark as completed!");
                this.waitInput();
            } else {
                markAsDone(cmd[1]);
            }
            break;
        case "bye":
            System.out.println("\tBye. Hope to see you again soon!");
            break;
        default:
            echo(input);
            break;
        }
    }

    private void markAsDone(String index) {
        int num = Integer.parseInt(index.trim());
        if (num < 1 || num > this.tasks.size()) {
            System.out.println("\tThe index inputted is not in the list!");
        } else {
            Task toBeDone = this.tasks.get(num - 1);
            if (toBeDone.isDone == true) {
                System.out.println("\tThis task is already completed! Please do other tasks!");
            } else {
                toBeDone.toggleIsDone();
                System.out.println("\tGood job! You have completed this task!");
                System.out.println("\t\t" + toBeDone);
                String str = "";
                try {
                    Scanner scanFile = new Scanner(f);
                    for (int i = 1; i <= tasks.size(); i++) {
                        if (i == num) {
                            if (i == tasks.size()) {
                                str += scanFile.nextLine().replaceFirst("0", "1");
                            } else {
                                str += scanFile.nextLine().replaceFirst("0", "1") + System.lineSeparator();
                            }
                        } else {
                            if (i == tasks.size()) {
                                str += scanFile.nextLine();
                            } else {
                                str += scanFile.nextLine() + System.lineSeparator();
                            }
                        }
                    }
                    FileWriter fileWriter = new FileWriter("data/duke.txt");
                    fileWriter.write(str);
                    fileWriter.close();
                } catch (FileNotFoundException e) {
                    System.out.println("\tFile not found!");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        this.waitInput();
    }

    private void addTask(Task task) {
        System.out.println("\tGot it! I've added this task:");
        this.tasks.add(task);
        System.out.println("\t\t" + task);
        System.out.println("\tYou have total of " + this.tasks.size() + " tasks in the list.");
        this.waitInput();
    }

    private void deleteTask(String index) {
        int num = Integer.parseInt(index.trim());
        if (num < 1 || num > this.tasks.size()) {
            System.out.println("\tThe index inputted is not in the list!");
        } else {
            try {
                String str = "";
                Scanner scanFile = new Scanner(f);
                for (int i = 1; i <= tasks.size(); i++) {
                    if (i == num) {
                        scanFile.nextLine();
                    } else if (i == tasks.size()) {
                        str += scanFile.nextLine();
                    } else if (i == tasks.size() - 1 && tasks.size() == num) {
                        str += scanFile.nextLine();
                    } else {
                        str += scanFile.nextLine() + System.lineSeparator();
                    }
                }
                FileWriter fileWriter = new FileWriter("data/duke.txt");
                fileWriter.write(str);
                fileWriter.close();
                Task deleted = this.tasks.get(num - 1);
                System.out.println("\tOkay! I've removed this task!");
                System.out.println("\t\t" + deleted);
                this.tasks.remove(num - 1);
                System.out.println("\tYou have total of " + this.tasks.size() + " tasks in the list.");
            } catch (FileNotFoundException e) {
                System.out.println("\tFile not found!");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        this.waitInput();
    }

    private void loadFromFile(File f) {
        try {
            Scanner scanFile = new Scanner(f);
            while (scanFile.hasNext()) {
                String eventType = scanFile.next();
                switch (eventType) {
                case "T":
                    String todo = scanFile.nextLine().trim();
                    String[] arr = todo.split("\\|");
                    Task todoTask = new Todo(arr[2].trim());
                    if (arr[1].trim().equals("1")) {
                        todoTask.toggleIsDone();
                    }
                    tasks.add(todoTask);
                    break;
                case "D":
                    String deadline = scanFile.nextLine().trim();
                    String[] arr2 = deadline.split("\\|");
                    Task deadlineTask = new Deadline(arr2[2].trim(), arr2[3].trim());
                    if (arr2[1].trim().equals("1")) {
                        deadlineTask.toggleIsDone();
                    }
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    String event = scanFile.nextLine().trim();
                    String[] arr3 = event.split("\\|");
                    Task eventTask = new Event(arr3[2].trim(), arr3[3].trim());
                    if (arr3[1].trim().equals("1")) {
                        eventTask.toggleIsDone();
                    }
                    tasks.add(eventTask);
                    break;
                default:
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\tFile not found!");
        }
    }

    public void run() {
        this.intro();
        this.waitInput();
    }
}
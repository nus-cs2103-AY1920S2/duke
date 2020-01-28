import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Task> list = new ArrayList<>();
    static File file;
    static BufferedReader br;
    static FileWriter fw;

    public static void main(String[] args) {
        //Greeting message
        printIntro();
        findFile();

        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    printBye();
                    break;
                } else if (input.equals("list")) {
                    printList(list);
                } else if (input.contains("done")) {
                    String[] inputArr = input.split(" ");
                    int num = Integer.parseInt(inputArr[1]);
                    if (num > list.size()) {
                        throw new DukeException("Sorry! That is an invalid task number.");
                    } else {
                        doneAction(list, num);
                    }
                } else if (input.contains("todo")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("Sorry! Description of todo cannot be empty.");
                    } else {
                        String name = input.substring(5);
                        Task todo = new ToDos(name);
                        addToList(todo, list);
                    }
                } else if (input.contains("deadline")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("Sorry! Description of deadline cannot be empty.");
                    } else {
                        int index = input.indexOf("/");
                        if (index == -1) {
                            throw new DukeException("Sorry! Please enter a deadline.");
                        } else {
                            String by = input.substring(index + 4);
                            String name = input.substring(9, index);
                            Task deadline = new Deadline(name, by);
                            addToList(deadline, list);
                        }
                    }
                } else if (input.contains("event")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("Sorry! Description of event cannot be empty.");
                    } else {
                        int index = input.indexOf("/");
                        if (index == -1) {
                            throw new DukeException("Sorry! Please enter a date and time.");
                        } else {
                            String by = input.substring(index + 4);
                            String name = input.substring(6, index);
                            Task event = new Event(name, by);
                            addToList(event, list);
                        }
                    }
                } else if (input.contains("delete")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("Sorry! Please specify a task number.");
                    } else {
                        String[] inputArr = input.split(" ");
                        int num = Integer.parseInt(inputArr[1]);
                        if (num > list.size()) {
                            throw new DukeException("Sorry! That is an invalid task number.");
                        } else {
                            removeAction(list, num);
                        }
                    }
                } else {
                    throw new DukeException("Sorry! I dont know what that means.");
                }

                //Write the list into output
                writeList();

            } catch (DukeException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    static void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello, I'm Duke \n"
                + "How can I help you today?");
    }

    static void findFile() {
        file = new File("./data/duke.txt");
        try {
            if (file.exists()) { //file exists
                br = new BufferedReader(new FileReader(file));
                String st;
                while ((st = br.readLine()) != null) {
                    String[] split = st.split(",");
                    for (int i = 0; i < split.length; i++) {
                        String currString = split[i];
                        split[i] = currString.trim();
                    }
                    switch (split[0]) {
                        case "E":
                            list.add(new Event(split[2], split[3], split[1]));
                            break;
                        case "D":
                            list.add(new Deadline(split[2], split[3], split[1]));
                            break;
                        case "T":
                            list.add(new ToDos(split[2], split[3]));
                            break;
                        default:
                            break;
                    }
                }
            } else { //file doesn't exist
                file.getParentFile().mkdirs();
                file.createNewFile();
                file = new File("./data/duke.txt");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void writeList() throws IOException {
        fw = new FileWriter(file);
        for (Task t : list) {
            fw.write(t.save() + "\n");
        }
        fw.flush();
    }

    public static void addToList(Task task, ArrayList<Task> list) {
        list.add(task);
        System.out.println("Okay. I added the task:");
        System.out.println(task);
        System.out.println("You now have " + list.size() + " tasks in the list.");
    }

    public static void printList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println("You have 0 outstanding tasks!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (Task s : list) {
                int listNo = list.indexOf(s) + 1;
                System.out.println(listNo + "." + s);
            }
        }

    }

    public static void doneAction(ArrayList<Task> list, int num) {
        Task doneTask = list.get(num - 1);
        doneTask.mark();
        System.out.println("Nicely done! I've marked this task as completed:");
        System.out.println(doneTask);
    }

    public static void removeAction(ArrayList<Task> list, int num) {
        Task removed = list.get(num - 1);
        list.remove(num - 1);
        System.out.println("Done! I have removed this task:");
        System.out.println(removed);
        System.out.println("You now have " + list.size() + " tasks in the list.");

    }

    public static void printBye() {
        System.out.print("Bye. Hope to see you again soon!");
    }
}

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class Duke {
    public static String filePath = "../../../data/duke.txt";
    public static void main(String[] args) {
        String logo = " ____        _        \n\t"
                + "|  _ \\ _   _| | _____ \n\t"
                + "| | | | | | | |/ / _ \\\n\t"
                + "| |_| | |_| |   <  __/\n\t"
                + "|____/ \\__,_|_|\\_\\___|\n\t";

        //Greeting
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello from\n\t" + logo);
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        ArrayList<Task> tasks;

        Scanner in = new Scanner(System.in);

        try {
            tasks = getTasksFromFile(filePath);
        } catch(FileNotFoundException e) {
            tasks = new ArrayList<>(100);
        }

        //accepting input
        String input = in.nextLine();

            while (!input.equalsIgnoreCase("Bye")) {
                try {
                    System.out.println("\t____________________________________________________________");
                    //checking input and giving appropriate responses
                    if (input.equalsIgnoreCase("list")) {
                        int size = tasks.size();
                        System.out.println("\tHere are the tasks in your list:");
                        for (int i = 0; i < size; ++i) {
                            System.out.println("\t" + (i + 1) + ". " + tasks.get(i));
                        }
                    } else if (input.startsWith("done")) {
                        String[] tempArray = input.split(" ");
                        int n = Integer.parseInt(tempArray[1]);
                        if (n > tasks.size()) {
                            System.out.println("\tThere is no such task");
                        } else {
                            System.out.println("\tNice! I've marked this task as done:");
                            tasks.get(n - 1).markAsDone();
                            System.out.println("\t" + tasks.get(n - 1));
                        }
                    } else if (input.startsWith("delete")) {
                        String[] tempArray = input.split(" ");
                        int n = Integer.parseInt(tempArray[1]);
                        if (n > tasks.size()) {
                            System.out.println("\tThere is no such task");
                        } else {
                            System.out.println("\tNoted. I have removed this task:");
                            System.out.println("\t" + tasks.get(n - 1));
                            tasks.remove(n-1);
                            System.out.println("\tYou now have " + tasks.size() + " tasks in the list.");
                        }
                    } else {

                        Task temp;
                        String[] splitInput = input.split(" ", 2);
                        if (input.startsWith("todo")) {
                            if (splitInput.length < 2) {
                                throw new EmptyTaskException("todo");
                            }
                            temp = new ToDo(splitInput[1]);
                            tasks.add(temp);
                        } else if (input.startsWith("deadline")) {
                            if (splitInput.length < 2) {
                                throw new EmptyTaskException("deadline");
                            }
                            temp = new Deadline((splitInput[1]).split("/", 2));
                            tasks.add(temp);
                        } else if (input.startsWith("event")) {
                            if (splitInput.length < 2) {
                                throw new EmptyTaskException("event");
                            }
                            temp = new Event((splitInput[1]).split("/", 2));
                            tasks.add(temp);
                        } else {
                            throw new InvalidRequestException();
                        }
                        System.out.println("\tGot it. I've added this task:");
                        System.out.println("\t" + temp);
                        System.out.println("\tNow you have " + tasks.size() + " tasks in your list.");
                    }
                    System.out.println("\t____________________________________________________________");

                    input = in.nextLine();


                } catch (InvalidRequestException e1) {
                    System.out.println("\t" + e1.toString());
                    System.out.println("\t____________________________________________________________");
                    input = in.nextLine();
                } catch (EmptyTaskException e2) {
                    System.out.println("\t" + e2.toString());
                    System.out.println("\t____________________________________________________________");
                    input = in.nextLine();
                }
            }

        //Exit
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        try {
            addTasksToFile(tasks);
        } catch(IOException e) {
            System.out.println("Error in saving to file");
        }
        System.out.println("\t____________________________________________________________");

    }

    public static ArrayList<Task> getTasksFromFile(String path) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        File file = new File(path);
        Scanner in = new Scanner(file);
        while(in.hasNextLine()) {
            String line = in.nextLine();
            String taskComponents[] = line.split(" \\| ");
            Task t;
            switch(taskComponents[0]) {
                case "T":
                    t = new ToDo(taskComponents[2], stringToBoolean(taskComponents[1]));
                    tasks.add(t);
                    break;
                case "D":
                    t = new Deadline(taskComponents[2], taskComponents[3], stringToBoolean(taskComponents[1]));
                    tasks.add(t);
                    break;
                case "E":
                    t = new Event(taskComponents[2], taskComponents[3], stringToBoolean(taskComponents[1]));
                    tasks.add(t);
                    break;
            }
        }
        return tasks;
    }

    public static void addTasksToFile(ArrayList<Task> tasks) throws IOException{
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        int size = tasks.size();
        String str = "";
        Task t;
        for(int i = 0; i < size; ++i) {
            t = tasks.get(i);
            str = str + t.addToFile() + "\n";
        }
        fw.write(str);
        fw.close();
    }

    public static boolean stringToBoolean(String str) {
        if(str.equals("0")) {
            return false;
        } else {
            return true;
        }
    }
}

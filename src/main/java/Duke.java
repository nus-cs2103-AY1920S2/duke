import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String machine = "Dude: ";
    public static String user = "dude: ";
    public static String dataFileName = "./data/duke.txt";
    public static String[] commandArray = new String[] {"list", "done", "delete", "todo", "event", "deadline"};
    public static ArrayList<String> commandList = new ArrayList<>();
    public static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        loadData();
        Collections.addAll(commandList, commandArray);
        greeting();

        String commandLine = sc.nextLine();
        String[] commands = commandLine.split(" ", 2);
        while (!commands[0].equals("bye")) {
            try{
                checkCommand(commands);
                try {
                    checkDetails(commands);
                } catch (EmptyDescriptionException ex) {
                    System.out.println(ex.getMessage());
                }
            } catch (InvalidCommandException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.print(user);
            commandLine = sc.nextLine();
            commands = commandLine.split(" ", 2);
        }
        System.out.println(machine + "Okay see ya dude!");
    }

    public static void loadData() {
        BufferedReader reader = null;
        try {
            String line = "";
            reader = new BufferedReader(new FileReader(dataFileName));
            line = reader.readLine();

            while (line != null) {
                String[] tokens = line.split(" \\| ");
                String type = tokens[0];
                boolean isDone = Boolean.parseBoolean(tokens[1]);
                String description = tokens[2];
                if (type.equals("T")) {
                    addTodo(isDone, description);
                } else {
                    String time = tokens[3];
                    if (type.equals("D")) {
                        addDeadline(isDone, description, time);
                    } else {
                        addEvent(isDone, description, time);
                    }
                }
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateData() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(dataFileName, false));
            for (Task task: list) {
                String[] tokens = task.toDataTokens();
                writer.append(String.join(" | ", tokens));
                writer.append("\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void greeting() {
        String welcome = "\n"
                + machine + "Hi dude! I'm your Dude\n"
                + "      What do you want dude?\n"
                + user;
        System.out.print(welcome);
    }

    public static void list() {
        if (list.isEmpty()) {
            System.out.println(machine + "Your list is currently empty dude.");
        } else {
            System.out.println(machine + "Here's your list of tasks dude:");
            for (int i = 1; i <= list.size(); i++) {
                Task task = list.get(i-1);
                System.out.println("      " + i + ". " + task);
            }
        }
    }

    public static void markAsDone(String details) {
        int i = Integer.parseInt(details);
        Task task = list.get(i-1);
        task.markAsDone();
        System.out.println(machine + "Alright dude this task is marked as done:");
        System.out.println("      " + i + ". " + task);
    }

    public static void deleteTask(String details) {
        int i = Integer.parseInt(details);
        Task task = list.get(i-1);
        list.remove(task);
        System.out.println(machine + "Got it dude! I've removed this task:");
        System.out.println("      " + i + ". " + task);
        System.out.println("      Now you have " + list.size() + " tasks in the list.");
    }

    public static void checkCommand(String[] commands) throws InvalidCommandException {
        if (!commandList.contains(commands[0])) {
            throw new InvalidCommandException("      Sorry dude but that won't command me!");
        }
    }

    public static void checkDetails(String[] commands) throws EmptyDescriptionException{
        if (!commands[0].equals("list") && commands.length < 2) {
            throw new EmptyDescriptionException("      Wait dude your task is...?");
        }
        String[] arr;
        Task task;
        switch (commands[0]) {
        case "list":
            list();
            break;
        case "done":
            markAsDone(commands[1]);
            break;
        case "delete":
            deleteTask(commands[1]);
            break;
        case "todo":
            task = addTodo(false, commands[1]);
            printConfirmAddMessage(task);
            break;
        case "event":
            arr = commands[1].split(" /at ");
            task = addEvent(false, arr[0], arr[1]);
            printConfirmAddMessage(task);
            break;
        case "deadline":
            arr = commands[1].split(" /by ");
            task = addDeadline(false, arr[0], arr[1]);
            printConfirmAddMessage(task);
            break;
        }
        updateData();
    }

    public static Task addTodo(boolean isDone, String details) {
        Task task = new Todo(isDone, details);
        list.add(task);
        return task;
    }

    public static Task addDeadline(boolean isDone, String description, String by) {
        Task task = new Deadline(isDone, description, by);
        list.add(task);
        return task;
    }

    public static Task addEvent(boolean isDone, String description, String at) {
        Task task = new Event(isDone, description, at);
        list.add(task);
        return task;
    }

    public static void printConfirmAddMessage(Task task) {
        System.out.println(machine + "Got it dude! I've added this task:");
        System.out.println("      " + task);
        System.out.println("      Now you have " + list.size() + " task(s) in the list.");
    }
}

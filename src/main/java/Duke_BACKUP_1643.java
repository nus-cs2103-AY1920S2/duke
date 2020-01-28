<<<<<<< HEAD
import java.io.*;
=======
import java.time.LocalDate;
>>>>>>> branch-Level-8
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        File database = new File("./src/main/java/database.txt");

        BufferedReader br = new BufferedReader(new FileReader(database));
        ArrayList<Task> added = new ArrayList<Task>();

        load(added, br);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            try {
                String firstWord = getFirstWord(input);
            }
            catch (DukeException e) {
                System.out.println("Oops!! I'm sorry, but I don't know what that means :(");
                continue;
            }

            if (input.equals("bye")) {
                break;
            }

            else if (input.equals("list")) {
                list(added);
            }

            // To Do
            else if (input.length() > 5 && input.substring(0, 5).equals("todo ")) {
                added.add(new ToDo(input.substring(5), false));
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + added.get(added.size() - 1).toString());
                System.out.println("Now you have " + added.size() + "tasks in the list.");

                BufferedWriter bw = new BufferedWriter(new FileWriter(database));
                save(added, bw);
            }

            // Deadline, date must be yyyy-mm-dd format
            else if (input.length() > 9 && input.substring(0, 9).equals("deadline ")) {
                try {
                    int slashIndex = getSlash(input);
<<<<<<< HEAD
                    added.add(new Deadline(input.substring(9, slashIndex - 1), false, input.substring(slashIndex + 4)));
=======
                    added.add(new Deadline(input.substring(9, slashIndex - 1), LocalDate.parse(input.substring(slashIndex + 4))));
>>>>>>> branch-Level-8
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + added.get(added.size() - 1).toString());
                    System.out.println("Now you have " + added.size() + "tasks in the list.");

                    BufferedWriter bw = new BufferedWriter(new FileWriter(database));
                    save(added, bw);
                }
                catch (DukeException e) {
                    System.out.println("Error: incorrect format.");
                }
            }

            // Event
            else if (input.length() > 6 && input.substring(0, 6).equals("event ")) {
                try {
                    int slashIndex = getSlash(input);
                    added.add(new Event(input.substring(6, slashIndex - 1), false, input.substring(slashIndex + 4)));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + added.get(added.size() - 1).toString());
                    System.out.println("Now you have " + added.size() + "tasks in the list.");

                    BufferedWriter bw = new BufferedWriter(new FileWriter(database));
                    save(added, bw);
                }
                catch (DukeException e) {
                    System.out.println("Error: incorrect format.");
                }
            }

            // Delete
            else if (input.length() > 7 && input.substring(0, 7).equals("delete ")) {
                String num = input.substring(7);
                int index;

                try {
                    index = Integer.parseInt(num) - 1;
                }
                catch (NumberFormatException e){
                    System.out.println("Error: must input a number after 'done'.");
                    continue;
                }

                try {
                    Task rm = added.remove(index);
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println("\t" + rm.toString());
                    System.out.println("Now you have " + added.size() + " tasks in the list.");
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: index out of bounds.");
                }
            }

            // Done
            else if (input.length() > 5 && input.substring(0, 5).equals("done ")) {
                String num = input.substring(5);
                int index;

                try {
                    index = Integer.parseInt(num) - 1;
                }
                catch (NumberFormatException e){
                    System.out.println("Error: must input a number after 'done'.");
                    continue;
                }

                try {
                    added.get(index).markAsDone();
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: index out of bounds.");
                    continue;
                }

                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("\t" + added.get(index).toString());
            }

            else {
                System.out.println("Oops!! I'm sorry, but I don't know what that means :(");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list(ArrayList<Task> added) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < added.size(); i++) {
            System.out.println((i + 1) + ". " + added.get(i).toString());
        }
    }

    public static int getSlash(String input) throws DukeException{
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '/') {
                return i;
            }
        }

        throw new DukeException();
    }

    // trying custom DukeException
    public static String getFirstWord(String input) throws DukeException{
        int firstSpaceIndex = -1;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                firstSpaceIndex = i;
                break;
            }
        }

        String firstWord;
        if (firstSpaceIndex == -1) {
            firstWord = input;
        }
        else {
            firstWord = input.substring(0, firstSpaceIndex);
        }


        if (!firstWord.equals("todo") &&
                !firstWord.equals("deadline") &&
                !firstWord.equals("event") &&
                !firstWord.equals("list") &&
                !firstWord.equals("done") &&
                !firstWord.equals("delete") &&
                !firstWord.equals("bye")) {
            throw new DukeException();
        }
        else {
            return firstWord;
        }
    }

    public static void load(ArrayList<Task> list, BufferedReader br) throws IOException{
        String str;
        while ((str = br.readLine()) != null) {
            char type = str.charAt(0);
            boolean done = Boolean.parseBoolean(str.substring(4, 5));
            String description;
            String byAt;

            if (type == 'T') {
                description = str.substring(8);
                list.add(new ToDo(description, done));
            }
            else {
                int lastIndex = findThirdStrike(str) - 1;
                description = str.substring(8, lastIndex);
                byAt = str.substring(lastIndex + 3);

                if (type == 'D') {
                    list.add(new Deadline(description, done, byAt));
                }

                else if (type == 'E') {
                    list.add(new Event(description, done, byAt));
                }
            }
        }
    }

    public static void save(ArrayList<Task> list, BufferedWriter bw) throws IOException {
        for (int i = 0; i < list.size(); i++) {
            bw.write(list.get(i).convert() + "\n");
        }

        bw.flush();
    }

    public static int findThirdStrike(String str) {
        int count = 3;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '|') {
                count--;
            }

            if (count == 0) {
                return i;
            }
        }

        return -1;
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String convert() {
        int done = isDone ? 1 : 0;
        return " | " + done + " | " + description;
    }
}

class ToDo extends Task {
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String convert() {
        return "T" + super.convert();
    }
}

class Deadline extends Task {
    protected LocalDate by;

<<<<<<< HEAD
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
=======
    public Deadline(String description, LocalDate by) {
        super(description);
>>>>>>> branch-Level-8
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String convert() {
        return "D" + super.convert() + " | " + by;
    }
}

class Event extends Task {
    protected String at;

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String convert() {
        return "E" + super.convert() + " | " + at;
    }
}

class DukeException extends Exception {
    DukeException() {
        super();
    }
}

enum Op {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    DONE,
    DELETE,
    BYE
}
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// These are not Strings; they are constant variables
/* enum Op {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    DONE,
    DELETE,
    BYE
} */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("./src/main/java/database.txt");
        duke.run();
    }

    public void run() throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ui.prompt(storage, tasks);
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

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
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
    protected LocalDate at;

    public Event(String description, boolean isDone, LocalDate at) {
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

class Storage {
    private File database;

    public Storage(String fileName) {
        database = new File(fileName);
    }

    // returns an array of String
    public ArrayList<String> load() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(database));
        ArrayList<String> contents = new ArrayList<>();
        String str;

        while ((str = br.readLine()) != null) {
            contents.add(str);
        }

        return contents;
    }

    public void save(ArrayList<Task> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(database));

        for (Task task : list) {
            bw.write(task.convert() + "\n");
        }

        bw.flush();
    }
}

class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<String> contents) throws DukeException {
        list = new ArrayList<>();
        load(contents);
    }

    private void load(ArrayList<String> contents) throws DukeException {
        for (String str : contents) {
            char type = str.charAt(0);
            boolean done = Boolean.parseBoolean(str.substring(4, 5));
            String description;
            String byAt;

            // To Do
            if (type == 'T') {
                description = str.substring(8);
                list.add(new ToDo(description, done));
            } else {
                int lastIndex = findThirdStrike(str) - 1;
                description = str.substring(8, lastIndex);
                byAt = str.substring(lastIndex + 3);

                if (type == 'D') {
                    list.add(new Deadline(description, done, LocalDate.parse(byAt)));
                } else if (type == 'E') {
                    list.add(new Event(description, done, LocalDate.parse(byAt)));
                }
            }
        }

        throw new DukeException();
    }

    public void save(Storage storage) throws IOException {
        storage.save(list);
    }

    private int findThirdStrike(String str) {
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

    public void display() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
    }

    public void add(String description, boolean isDone) {
        list.add(new ToDo(description, isDone));
    }

    public void add(char type, String description, boolean isDone, LocalDate date) {
        if (type == 'D') {
            list.add(new Deadline(description, isDone, date));
        } else if (type == 'E') {
            list.add(new Event(description, isDone, date));
        } else {
            System.out.println("Error: type must be 'D' or 'E'");
        }
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public void markAsDone(int index) {
        list.get(index).markAsDone();
    }

    public Task delete(int index) {
        return list.remove(index);
    }
}

class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLoadingError() {
        System.out.println("Loading Error");
    }

    private void printAdded(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + "tasks in the list.");
    }

    public void prompt(Storage storage, TaskList tasks) throws IOException {
        while (true) {
            boolean isBye = false;
            String input = sc.nextLine();
            String firstWord;

            // Guarantees 1st word is legitimate
            try {
                firstWord = Parser.getFirstWord(input);
            } catch (DukeException e) {
                System.out.println("Oops!! I'm sorry, but I don't know what that means :(");
                continue;
            }

            switch (firstWord) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                isBye = true;
                break;
            case "list":
                tasks.display();
                break;
            case "todo":
                if (input.length() > 5) {
                    tasks.add(input.substring(5), false);

                    printAdded(tasks);
                    tasks.save(storage);
                } else {
                    System.out.println("No todo task specified!");
                }
                break;
            case "deadline":    // deadline description /yyyy-mm-dd
                if (input.length() > 9) {
                    try {
                        String description = Parser.getDescription(input, 9);
                        LocalDate date = Parser.getDate(input);

                        tasks.add('D', description, false, date);
                        printAdded(tasks);
                        tasks.save(storage);

                    } catch (Exception e) {
                        System.out.println("Error: incorrect format to add deadline task");
                    }
                } else {
                    System.out.println("No deadline task specified!");
                }
                break;
            case "event":   // event description /yyyy-mm-dd
                if (input.length() > 6) {
                    try {
                        String description = Parser.getDescription(input, 6);
                        LocalDate date = Parser.getDate(input);

                        tasks.add('E', description, false, date);
                        printAdded(tasks);
                        tasks.save(storage);

                    } catch (Exception e) {
                        System.out.println("Error: incorrect format to add deadline task");
                    }
                } else {
                    System.out.println("Error: no deadline task specified");
                }
                break;
            case "done":
                try {
                    int index = Parser.getIndex(input, "done");
                    tasks.markAsDone(index);

                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("\t" + tasks.get(index).toString());
                } catch (Exception e) {
                    System.out.println("Error: invalid (out of bounds) or non-integer entered");
                }
                break;
            case "delete":
                try {
                    int index = Parser.getIndex(input, "delete");
                    Task rm = tasks.delete(index);

                    System.out.println("Noted. I've removed this task: ");
                    System.out.println("\t" + rm.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (Exception e) {
                    System.out.println("Error: invalid (out of bounds) or non-integer entered");
                }
                break;
            default:
                System.out.println("Oops!! I'm sorry, but I don't know what that means :(");
                break;
            }

            if (isBye) {
                break;
            }
        }
    }
}

class Parser {
    public Parser() {
    }

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
        } else {
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

    public static String getDescription(String input, int start) throws DukeException {
        int slashIndex = Parser.getSlash(input);
        return input.substring(start, slashIndex - 1);
    }

    public static LocalDate getDate(String input) throws DukeException {
        int slashIndex = Parser.getSlash(input);
        return LocalDate.parse(input.substring(slashIndex + 1));
    }

    private static int getSlash(String input) throws DukeException {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '/') {
                return i;
            }
        }

        throw new DukeException();
    }

    public static int getIndex(String input, String type) throws DukeException {
        int startPos = type.equals("done") ? 5: 7; // type will only either be "done" or "delete"

        try {
            String num = input.substring(startPos);
            return Integer.parseInt(num) - 1;
        } catch (Exception e) {
            throw new DukeException();
        }
    }
}

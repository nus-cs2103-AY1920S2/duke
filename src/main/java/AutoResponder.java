import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Main driver class of AutoResponder chat.
 */
public class AutoResponder {
    private final List<Task> taskList;
    private StringBuilder toPrint;
    private final Ui ui;
    static Pattern PATTERN_DEADLINE = Pattern.compile("^deadline (.+) /by (.+)");
    static Pattern PATTERN_EVENT = Pattern.compile("^event (.+) /at (.+)");
    static Pattern PATTERN_TODO = Pattern.compile("^todo (.+)");
    static Pattern PATTERN_DONE = Pattern.compile("^done (\\d+)");
    static Pattern PATTERN_DELETE = Pattern.compile("^delete (\\d+)");
    static Pattern PATTERN_EMPTY_COMMAND = Pattern.compile("^(todo|event|deadline|find|done|delete)\\s*$");
    static Pattern PATTERN_LIST = Pattern.compile("^list\\s*$");
    static Pattern PATTERN_SAVE = Pattern.compile("^save\\s*$");
    static Pattern PATTERN_FIND = Pattern.compile("^find (.+)");
    static Pattern PATTERN_BYE = Pattern.compile("^bye\\s*$");

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    /**
     * Initialiser for empty AutoResponder object.
     */
    public AutoResponder() {
        this.taskList = new ArrayList<>();
        this.toPrint = new StringBuilder();
        this.ui = new Ui();
    }

    private AutoResponder(List<Task> taskList, StringBuilder toPrint) {
        this.taskList = taskList;
        this.toPrint = toPrint;
        this.ui = new Ui();
    }

    private String printToConsole() {
        String output = toPrint.toString();
        this.toPrint = new StringBuilder();
        return output;
    }

    /**
     * Reads input from UI, and processes commands accordingly.
     *
     * @param input String to be processed from System.in
     * @return new AutoResponder object with corresponding command to be processed
     */
    public String readInput(String input) {
        String lowerInput = input.toLowerCase();
        if (PATTERN_LIST.matcher(lowerInput).find()) {
            return this.processList();
        } else if (PATTERN_DONE.matcher(lowerInput).find()) {
            Matcher m = PATTERN_DONE.matcher(lowerInput);
            m.find();
            int index = Integer.parseInt(m.group(1)) - 1;
            return this.markTaskDone(index);
        } else if (PATTERN_DELETE.matcher(lowerInput).find()) {
            Matcher m = PATTERN_DELETE.matcher(lowerInput);
            m.find();
            int index = Integer.parseInt(m.group(1)) - 1;
            return this.deleteTask(index);
        } else if (PATTERN_SAVE.matcher(lowerInput).find()) {
            return this.saveList();
        } else if (PATTERN_EMPTY_COMMAND.matcher(lowerInput).find()) {
            Matcher m = PATTERN_EMPTY_COMMAND.matcher(lowerInput);
            m.find();
            throw new IllegalArgumentException("OOPS!!! The description of a "
                    + m.group(1) + " cannot be empty.");
        } else if (PATTERN_FIND.matcher(input).find()) {
            Matcher m = PATTERN_FIND.matcher(input);
            m.find();
            return this.findTask(m.group(1));
        } else if (PATTERN_DEADLINE.matcher(input).find()) {
            Matcher m = PATTERN_DEADLINE.matcher(input);
            m.find();
            return this.addDeadline(m.group(1), m.group(2));
        } else if (PATTERN_EVENT.matcher(input).find()) {
            Matcher m = PATTERN_EVENT.matcher(input);
            m.find();
            return this.addEvent(m.group(1), m.group(2));
        } else if (PATTERN_TODO.matcher(input).find()) {
            Matcher m = PATTERN_TODO.matcher(input);
            m.find();
            return this.addTodo(m.group(1));
        } else if (PATTERN_BYE.matcher(input).find()) {
            System.exit(0);
            return "";
        } else {
            throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private String processList() {
        for (int i = 1; i <= taskList.size(); ++i) {
            toPrint.append(i).append(". ").append(taskList.get(i - 1)).append("\n");
        }
        return printToConsole();
    }

    private String saveList() {
        StringBuilder writeContents = new StringBuilder();
        for (Task v : taskList) {
            writeContents.append(v.writeFormat());
            writeContents.append("\n");
        }
        if (!Files.exists(Paths.get("./data"))) {
            boolean ok = new File("./data").mkdir();
            if (!ok) {
                System.err.println("Error in creating directory.\n");
            }
        }
        File f = new File("./data/duke.txt");
        String path = f.getAbsolutePath();
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(writeContents.toString());
            fw.close();
            toPrint.append("Tasks saved successfully.\n");
        } catch (IOException e) {
            toPrint.append("Tasks not loaded");
        }
        return this.printToConsole();
    }

    private String loadList() {
        try {
            File f = new File("./data/duke.txt");
            Scanner sc = new Scanner(f);
            String s;
            while (sc.hasNextLine()) {
                s = sc.nextLine();
                switch (s.charAt(0)) {
                case 'T':
                    taskList.add(Todo.readFormat(s));
                    break;
                case 'D':
                    taskList.add(Deadline.readFormat(s));
                    break;
                case 'E':
                    taskList.add(Event.readFormat(s));
                    break;
                default:
                    throw new IllegalArgumentException("No corresponding command found");
                }
            }
            toPrint.append("Task file loaded successfully!\n");
        } catch (Exception e) {
            toPrint.append("Task file not loaded. Check if file exists or is corrupted.\n");
        }
        return this.printToConsole();
    }

    private String markTaskDone(int index) {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException("Index of " + (index + 1)
                    + " does not correspond to task list of size " + taskList.size());
        } else {
            taskList.get(index).makeCompleted();
            toPrint.append("Nice! I've marked this task as done:\n\t");
            toPrint.append(taskList.get(index)).append("\n");
        }
        return printToConsole();
    }

    private String deleteTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException("Index of " + (index + 1)
                    + " does not correspond to task list of size " + taskList.size());
        } else {
            toPrint.append("Noted! I've removed this task:\n\t");
            toPrint.append(taskList.get(index)).append("\n");
            taskList.remove(index);
        }
        return printToConsole();
    }

    private String findTask(String description) {
        String s = taskList.stream().map(Object::toString)
                .filter(string -> string.contains(description))
                .collect(Collectors.joining());
        if (s.length() > 0) {
            toPrint.append("Here are the matching tasks in your list:\n");
            toPrint.append(s);
            toPrint.append("\n");
        } else {
            toPrint.append("There are no matching tasks in your list.\n");
        }
        return printToConsole();
    }

    private String addDeadline(String name, String date) {
        taskList.add(new Deadline(name, Parser.parseDateTime(date)));
        return taskAdded();
    }

    private String addEvent(String name, String date) {
        taskList.add(new Event(name, Parser.parseDateTime(date)));
        return taskAdded();
    }

    private String addTodo(String name) {
        taskList.add(new Todo(name));
        return taskAdded();
    }

    private String taskAdded() {
        toPrint.append("Got it. I've added this task:\n\t");
        toPrint.append(taskList.get(taskList.size() - 1));
        toPrint.append("\nNow you have ").append(taskList.size()).append(" tasks in the list.\n");
        return printToConsole();
    }
}

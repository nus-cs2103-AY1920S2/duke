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
    private final StringBuilder toPrint;
    private final Ui ui;
    static Pattern PATTERN_DEADLINE = Pattern.compile("^deadline (.+) /by (.+)");
    static Pattern PATTERN_EVENT = Pattern.compile("^event (.+) /at (.+)");
    static Pattern PATTERN_TODO = Pattern.compile("^todo (.+)");
    static Pattern PATTERN_DONE = Pattern.compile("^done (\\d+)");
    static Pattern PATTERN_DELETE = Pattern.compile("^delete (\\d+)");
    static Pattern PATTERN_EMPTY_COMMAND = Pattern.compile("^(todo|event|deadline|find|done|delete)\\s*$");
    static Pattern PATTERN_LIST = Pattern.compile("^list\\s*$");
    static Pattern PATTERN_FIND = Pattern.compile("^save\\s*$");
    static Pattern pFind = Pattern.compile("^find (.+)");


    private AutoResponder() {
        this.taskList = new ArrayList<>();
        this.toPrint = new StringBuilder();
        this.ui = new Ui();
    }

    private AutoResponder(List<Task> taskList, StringBuilder toPrint) {
        this.taskList = taskList;
        this.toPrint = toPrint;
        this.ui = new Ui();
    }

    private AutoResponder printToConsole() {
        ui.printToConsole(toPrint.toString());
        return new AutoResponder(taskList, new StringBuilder());
    }

    /**
     * Reads input from UI, and processes commands accordingly.
     * @param input String to be processed from System.in
     * @return new AutoResponder object with corresponding command to be processed
     */
    public AutoResponder readInput(String input) {
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
        } else if (PATTERN_FIND.matcher(lowerInput).find()) {
            return this.saveList();
        } else if (PATTERN_EMPTY_COMMAND.matcher(lowerInput).find()) {
            Matcher m = PATTERN_EMPTY_COMMAND.matcher(lowerInput);
            m.find();
            throw new IllegalArgumentException("☹ OOPS!!! The description of a "
                    + m.group(1) + " cannot be empty.");
        } else if (pFind.matcher(input).find()) {
            Matcher m = pFind.matcher(input);
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
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private AutoResponder processList() {
        StringBuilder sb = new StringBuilder(toPrint);
        for (int i = 1; i <= taskList.size(); ++i) {
            sb.append(i).append(". ").append(taskList.get(i - 1)).append("\n");
        }
        return new AutoResponder(taskList, sb).printToConsole();
    }

    private AutoResponder saveList() {
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

    private AutoResponder loadList() {
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

    private AutoResponder markTaskDone(int index) {
        StringBuilder sb = new StringBuilder(toPrint);
        List<Task> tl = new ArrayList<>(taskList);
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException("Index of " + (index + 1)
                    + " does not correspond to task list of size " + taskList.size());
        } else {
            tl.set(index, taskList.get(index).makeCompleted());
            sb.append("Nice! I've marked this task as done:\n\t");
            sb.append(tl.get(index)).append("\n");
        }
        return new AutoResponder(tl, sb).printToConsole();
    }

    private AutoResponder deleteTask(int index) {
        StringBuilder sb = new StringBuilder(toPrint);
        List<Task> tl = new ArrayList<>(taskList);
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException("Index of " + (index + 1)
                    + " does not correspond to task list of size " + taskList.size());
        } else {
            sb.append("Noted! I've removed this task:\n\t");
            sb.append(tl.get(index)).append("\n");
            tl.remove(index);
        }
        return new AutoResponder(tl, sb).printToConsole();
    }

    private AutoResponder findTask(String description) {
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
        return this.printToConsole();
    }

    private AutoResponder addDeadline(String name, String date) {
        List<Task> tl = new ArrayList<>(taskList);
        tl.add(new Deadline(name, Parser.parseDateTime(date)));
        return new AutoResponder(tl, toPrint).taskAdded();
    }

    private AutoResponder addEvent(String name, String date) {
        List<Task> tl = new ArrayList<>(taskList);
        tl.add(new Event(name, Parser.parseDateTime(date)));
        return new AutoResponder(tl, toPrint).taskAdded();
    }

    private AutoResponder addTodo(String name) {
        List<Task> tl = new ArrayList<>(taskList);
        tl.add(new Todo(name));
        return new AutoResponder(tl, toPrint).taskAdded();
    }

    private AutoResponder taskAdded() {
        StringBuilder sb = new StringBuilder(toPrint);
        sb.append("Got it. I've added this task:\n\t");
        sb.append(taskList.get(taskList.size() - 1));
        sb.append("\nNow you have ").append(taskList.size()).append(" tasks in the list.\n");
        return new AutoResponder(taskList, sb).printToConsole();
    }

    /**
     * Initialises a new AutoResponder to be created, printing its landing page and loading a list
     * from the usual location (./data/duke.txt).
     * @return AutoResponder object being run
     */
    public static AutoResponder initialise() {
        AutoResponder ar = new AutoResponder();
        ar.ui.printLandingPage();
        ar = ar.loadList();
        return ar.run();
    }

    /**
     * Starts the AutoResponder from accepting input via UI and processing commands.
     * @return the state of each AutoResponder after each run, or shutdown if the bye
     *     command is passed into it.
     */
    public AutoResponder run() {
        AutoResponder ar = this;
        while (ui.hasCommand()) {
            String input = ui.receiveCommand();
            if (input.matches("bye\\s*")) {
                return ar.shutdown();
            }

            try {
                ar = ar.readInput(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return ar.run();
    }

    /**
     * Shuts AutoResponder down and prints goodbye screen.
     * @return AutoResponder in shut down mode
     */
    public AutoResponder shutdown() {
        ui.printGoodbye();
        return this;
    }

}


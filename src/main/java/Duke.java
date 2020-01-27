import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    private static ArrayList<Task> mainList;
    private static int maxLength;
    private static String LIST_FILEPATH = "../data/duke.txt";
    private static String ARRAY_FILEPATH = "../data/mainList.txt";

    public static void main(String[] args) {
        try {
            mainList = readMainList(); // retrieve saved data for main list
            maxLength = 60;
            String logo = " _________                        \n" +
                    " __  ____/_______________________ \n" +
                    " _  / __ _  __ \\  __ \\_  ___/  _ \\  >(o )___\n" +
                    " / /_/ / / /_/ / /_/ /(__  )/  __/   ( ._> /\n" +
                    " \\____/  \\____/\\____//____/ \\___/     `---'";

            System.out.println("Hello it's\n" + logo);

            String greeting = "Honk, this Goose. Do you need help with something?";
            System.out.println("\n" + wrapLine(greeting));
        } catch (FileNotFoundException e) {
            System.out.println(wrapLine("Honk! File not found..."));
        }

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputArr;

        while (!input.equalsIgnoreCase("bye")) {
            try {
                inputArr = input.split(" ");
                checkInput(inputArr);

                if (inputArr[0].equals("list")) {
                    try {
                        // print list
                        System.out.println(wrapLine("Honk! Here's your task list: \n" + printList()));
                        input = scanner.nextLine();
                    } catch (FileNotFoundException e) {
                        System.out.println(wrapLine("Honk! File not found..."));
                    }

                } else if (inputArr[0].equals("done")) {
                    // extract list number of task to be marked as done
                    int taskIndex = Integer.parseInt(inputArr[1]) - 1;
                    markDone(taskIndex);

                    try {
                        saveList();
                    } catch (IOException e) {
                        System.out.println(wrapLine("Honk! Something went wrong: " + e.getMessage()));
                    }

                    input = scanner.nextLine();
                } else if (inputArr[0].equals("delete")) {
                    // extract list number of task to delete
                    int taskIndex = Integer.parseInt(inputArr[1]) - 1;
                    deleteTask(taskIndex);

                    try {
                        saveList();
                    } catch (IOException e) {
                        System.out.println(wrapLine("Honk! Something went wrong: " + e.getMessage()));
                    }

                    input = scanner.nextLine();
                } else {
                    try {
                        Task inputTask;

                        if (inputArr[0].equals("event")) {
                            inputTask = createEvent(input);

                        } else if (inputArr[0].equals("deadline")) {
                            inputTask = createDeadline(input);

                        } else {
                            inputTask = createTodo(inputArr);
                        }

                        mainList.add(inputTask);
                        if (inputTask.toString().length() > maxLength) {
                            maxLength = inputTask.toString().length() + 2;
                        }

                        String count = mainList.size() == 1
                                ? "\n\n          Now you have " + mainList.size() + " task in the list."
                                : "\n\n          Now you have " + mainList.size() + " tasks in the list.";

                        try {
                            saveList();
                        } catch (IOException e) {
                            System.out.println(wrapLine("Honk! Something went wrong: " + e.getMessage()));
                        }

                        System.out.println("\n" + wrapLine("Honk! Okay added the task:\n            " +
                                inputTask +
                                count));
                        input = scanner.nextLine();

                    } catch (GooseEmptyDescriptionException | GooseIllegalFormatException e) {
                        System.err.println(wrapLine(e.getMessage()));
                        input = scanner.nextLine();
                    }
                }

            } catch (GooseUnrecognisedException | GooseTaskExistenceException e) {
                System.err.println(wrapLine(e.getMessage()));
                input = scanner.nextLine();
            }
        }
        // when "bye"
        System.out.println(wrapLine("Honk honk!"));
    }

    public static Event createEvent(String input) throws GooseEmptyDescriptionException, GooseIllegalFormatException {
        String[] eventArr = input.split(" /at ");
        String[] descriptionSplit = eventArr[0].split(" ");
        String description = "";
        for (int i = 1; i < descriptionSplit.length; i++) {
            if (i == descriptionSplit.length - 1) {
                description += descriptionSplit[i];
            } else {
                description += descriptionSplit[i] + " ";
            }
        }

        if (description.isEmpty()) {
            throw new GooseEmptyDescriptionException("Honk! Description of an event cannot be empty.");
        } else {
            if (eventArr.length == 1) {
                throw new GooseIllegalFormatException("Honk! No event date specified.");
            }

            eventArr = eventArr[1].split(" ");
            String date = parseDate(eventArr[0]);
            String time = eventArr[1];
            return new Event(description, date, time);
        }
    }

    public static Deadline createDeadline(String input) throws GooseEmptyDescriptionException, GooseIllegalFormatException {
        String[] deadlineArr = input.split(" /by ");
        String[] descriptionSplit = deadlineArr[0].split(" ");
        String description = "";
        for (int i = 1; i < descriptionSplit.length; i++) {
            if (i == descriptionSplit.length - 1) {
                description += descriptionSplit[i];
            } else {
                description += descriptionSplit[i] + " ";
            }
        }

        if (description.isEmpty()) {
            throw new GooseEmptyDescriptionException("Honk! Description of a deadline cannot be empty.");
        } else {
            if (deadlineArr.length == 1) {
                throw new GooseIllegalFormatException("Honk! No deadline specified.");
            }

            deadlineArr = deadlineArr[1].split(" ");
            String date = parseDate(deadlineArr[0]);
            String time = deadlineArr[1];
            return new Deadline(description, date, time);
        }
    }

    public static Todo createTodo(String[] inputArr) throws GooseEmptyDescriptionException {
        String description = "";
        for (int i = 1; i < inputArr.length; i++) {
            description += " " + inputArr[i];
        }

        if (description.isEmpty()) {
            throw new GooseEmptyDescriptionException("Honk! Description of a todo cannot be empty.");
        } else {
            return new Todo(description);
        }
    }

    public static void deleteTask(int index) throws GooseTaskExistenceException {
        if (index >= mainList.size() || index < 0) {
            throw new GooseTaskExistenceException("You trick Goose? Task " + mainList.size() + " doesn't exist. Honk...");
        }

        Task selected = mainList.get(index);
        mainList.remove(index);
        String count = mainList.size() == 1
            ? "\n\n          Now you have " + mainList.size() + " task in the list."
            : "\n\n          Now you have " + mainList.size() + " tasks in the list.";
        System.out.println(wrapLine("Honk! Removed this task from the list:\n" + "           " +
                selected + count));
    }

    public static void markDone(int index) throws GooseTaskExistenceException {
        if (index >= mainList.size() || index < 0) {
            throw new GooseTaskExistenceException("You trick Goose? This task doesn't exist. Honk...");
        }

        Task selected = mainList.get(index);
        selected.markAsDone();

        String reply = "Good job! I've honked it as done:\n";
        System.out.println(wrapLine(reply + "           " + selected));
    }

    public static void checkInput(String[] inputArr) throws GooseUnrecognisedException {
        if (!inputArr[0].equals("list") && !inputArr[0].equals("done") && !inputArr[0].equals("deadline") &&
                !inputArr[0].equals("event") && !inputArr[0].equals("todo") && !inputArr[0].equals("delete") ||
                    inputArr[0].equals("list") && inputArr.length > 1) {
            throw new GooseUnrecognisedException("Honk honk??");
        }
    }

    public static String printList() throws FileNotFoundException {
        File f = new File(LIST_FILEPATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        if (f.length() == 0) { // if the file is empty or doesn't exist
            return "           You haven't added any tasks. Honk...";
        }
        String printedList = "";
        while (s.hasNextLine()) {
            printedList += s.nextLine() + "\n";
        }

        return printedList;
    }

    private static void saveList() throws IOException { // saves to both duke.txt and array.txt
        // save mainList array
        FileWriter af = new FileWriter(ARRAY_FILEPATH);
        String stringList = "";
        for (Task task : mainList) {
            if (task instanceof Event) {
                Event e = (Event) task;
                String doneStr = e.isDone ? "0" : "1";
                String eventString = "event|" + doneStr + "|" + e.description + "|" + e.atDate + "|" +
                        e.time24Hr + System.lineSeparator();
                stringList += eventString;
            } else if (task instanceof  Deadline) {
                Deadline d = (Deadline) task;
                String doneStr = d.isDone ? "0" : "1";
                String deadlineString = "deadline|" + doneStr + "|" + d.description + "|" + d.byDate + "|"
                        + d.time24Hr + System.lineSeparator();
                stringList += deadlineString;
            } else if (task instanceof Todo ){
                Todo t = (Todo) task;
                String doneStr = t.isDone ? "0" : "1";
                String todoString = "todo|" + doneStr + "|" + t.description + System.lineSeparator();
                stringList += todoString;
            }
        }
        af.write(stringList);
        af.close();

        // save list
        FileWriter fw = new FileWriter(LIST_FILEPATH);
        String text = "";
        if (mainList.size() != 0) {
            for (int i = 0; i < mainList.size(); i++) {
                if (mainList.get(i) == null) {
                    break;
                }
                int indexNum = i + 1;
                String item = "           " + indexNum + "." + mainList.get(i).toString();
                if (i != mainList.size() - 1) {
                    item += "\n";
                }
                text += item;
            }
        }
        fw.write(text);
        fw.close();
    }

    private static ArrayList<Task> readMainList() throws FileNotFoundException {
        File f = new File(ARRAY_FILEPATH);
        Scanner s = new Scanner(f);
        if (f.length() == 0) {
            return new ArrayList<>();
        } else {
            ArrayList<Task> ml = new ArrayList<>();
            while (s.hasNextLine()) {
                String[] taskArr = s.nextLine().split("|");
                String type = taskArr[0];
                boolean isDone = Integer.parseInt(taskArr[1]) != 0;
                String description = taskArr[2];

                switch (type) {
                    case "todo":
                        Todo addTodo = new Todo(description, isDone);
                        ml.add(addTodo);
                        break;
                    case "deadline":
                        String byDate = taskArr[3];
                        String deadlineTime = taskArr[4];
                        Deadline addDeadline = new Deadline(description, byDate, deadlineTime, isDone);
                        ml.add(addDeadline);
                        break;
                    case "event":
                        String atDate = taskArr[3];
                        String eventTime = taskArr[4];
                        Event addEvent = new Event(description, atDate, eventTime, isDone);
                        ml.add(addEvent);
                        break;
                    default:
                        System.out.println(wrapLine(type + "Format error! Honk!"));
                }
            }
            return ml;
        }
    }

    public static String parseDate(String date) {
        String[] dateArr = date.split("/");
        String day = dateArr[0];
        if (Integer.parseInt(day) < 10) {
            day = "0" + day;
        }
        String month = dateArr[1];
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        String year = dateArr[2];

        return year + "-" + month + "-" + day;
    }

    public static String wrapLine(String msg) {
        String top = "         ";
        for (int i = 0; i < maxLength; i++) {
            top += "_";
        }
        top += "\n";

        String bottom = "         ";
        String bottomArrow = "         ";
        for (int i = 0; i < maxLength - 4; i++) {
            bottom += "_";
            bottomArrow += " ";
        }
        bottomArrow += "\\ \n";
        bottom += "  __" + "\n" + bottomArrow;

        return top + "\n" + "          " + msg + "\n" + bottom;
    }
}



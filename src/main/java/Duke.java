import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    enum Command {
        BYE, DEADLINE, DELETE, DONE, EVENT, LIST, TODO, DEFAULT, CALENDAR
    }
    static Scanner scanner = new Scanner(System.in);
    static String buffer;
    static String input;
    static String[] tokens;
    static ArrayList<Task> list;
    static String path = "./data/duke.txt";


    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        boolean done = false;
        list = new ArrayList<>();
        String param = "";
        String[] params;
        Command command;
        int index;
        Task t;
        loadFile();
        while (true) {
            try {
                input = scanner.nextLine();
                tokens = input.split(" ", 2);
                command = Command.valueOf(tokens[0].toUpperCase());
                if (tokens.length > 1) {
                    param = tokens[1];
                } else {
                    param = "";
                }
                switch (command) {
                    case TODO:
                        if (param.equals("")) {
                            throw new MissingDescriptionException();
                        }
                        t = new ToDo(param);
                        addTask(t);
                        break;
                    case DEADLINE:
                        if (param.equals("")) {
                            throw new MissingDescriptionException();
                        } else if (!param.contains(" /by ")) {
                            throw new MissingDeadlineParamException();
                        }
                        params = param.split(" /by ");
                        t = new Deadline(params[0], params[1]);
                        addTask(t);
                        break;
                    case EVENT:
                        if (param.equals("")) {
                            throw new MissingDescriptionException();
                        } else if (!param.contains(" /at ")) {
                            throw new MissingEventParamException();
                        }
                        params = param.split(" /at ");
                        t = new Event(params[0], params[1]);
                        addTask(t);
                        break;
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        done = true;
                        break;
                    case LIST:
                        if (list.size() == 0) {
                            System.out.println("You have no tasks in your list.");
                        } else {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < list.size(); i++) {
                                System.out.println((i + 1) + "." + list.get(i));
                            }
                        }
                        break;
                    case DONE:
                        try {
                            index = Integer.parseInt(tokens[1]);
                            list.get(index - 1).markAsDone();
                        } catch (NumberFormatException ex) {
                            throw new InvalidArgumentException();
                        } catch (IndexOutOfBoundsException ex) {
                            throw new InvalidArgumentException();
                        }
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(index - 1));
                        break;
                    case DELETE:
                        try {
                            index = Integer.parseInt(tokens[1]);
                            t = list.remove(index - 1);
                        } catch (NumberFormatException ex) {
                            throw new InvalidArgumentException();
                        } catch (IndexOutOfBoundsException ex) {
                            throw new InvalidArgumentException();
                        }
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(t);
                        System.out.println("Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
                        break;
                    case CALENDAR:
                        showCalendar(param);
                        break;
                    default:
                        throw new UnknownCommandException();
                }
                if (done)
                    break;
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }

    }

    static void showCalendar(String calendarDateString) {
        LocalDate calendarDate = LocalDate.parse(calendarDateString, DateTimeFormatter.ofPattern("d/M/yyyy"));
        List<String> calendarList = new ArrayList<>();
        for (Task task : list) {
            if (task.getClass().equals(Deadline.class)) {
                LocalDate taskDate = ((Deadline) task).by.toLocalDate();
                if (taskDate.equals(calendarDate)) {
                    calendarList.add(task.toString());
                }
            } else if (task.getClass().equals(Event.class)) {
                LocalDate taskDate = ((Event) task).at.toLocalDate();
                if (taskDate.equals(calendarDate)) {
                    calendarList.add(task.toString());
                }
            }
        }
        if (calendarList.size() == 0) {
            System.out.println("No matching events/deadlines found.");
        } else {
            System.out.println("Here are the events/deadlines) in your list on " + calendarDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + ":");
            for (String task : calendarList) {
                System.out.println(task);
            }
        }
    }


    private static void addTask(Task t) {
        list.add(t);
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
        saveFile();
    }

    private static void loadFile() {
        FileInputStream fi = null;
        ObjectInputStream oi = null;
        File file = null;
        try {
            file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            fi = new FileInputStream(file);
            oi = new ObjectInputStream(fi);
            while (true) {
                Task t = (Task) oi.readObject();
                list.add(t);
            }
        } catch (EOFException e) {

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (oi != null) {
                    oi.close();
                }
                if (fi != null) {
                    fi.close();
                }
            } catch (IOException ex) {

            }
        }
    }

    private static void saveFile() {
        FileOutputStream fi = null;
        ObjectOutputStream oi = null;
        File file = null;
        try {
            file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            fi = new FileOutputStream(file);
            oi = new ObjectOutputStream(fi);
            for (Task t : list) {
                oi.writeObject(t);
            }
        } catch (EOFException e) {

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream: " + e.getMessage());
            try {
                if (oi != null) {
                    oi.close();
                }
                if (fi != null) {
                    fi.close();
                }
            } catch (IOException ex) {

            }
        }
    }

}

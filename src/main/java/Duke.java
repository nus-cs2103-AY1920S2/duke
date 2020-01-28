import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    static String space = "     ";
    static String line = space + "_____________________________________________";
    static List<Task> tasks;
    static int count = 0;

    public static void main(String[] args) {

        try {
            tasks = loadTask();
            count = tasks.size();
        } catch (FileNotFoundException f) {
            System.out.println("DATA FILE NOT FOUND");
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        introduce();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String next = scanner.nextLine();
            if (next.equals("bye")) {
                respond("Bye. Hope to see you again soon!");
                break;
            } else {
                try {
                    process(next);
                    saveTask();
                } catch (DukeException dd) {
                    System.out.println(dd.getMessage());
                } catch (FileNotFoundException e) {
                    System.out.println("File Saving failed");
                } catch (IOException e) {
                    System.out.println("File Saving failed");
                }
            }
        }
    }




    public static void process(String next) throws DukeException {
        StringTokenizer st = new StringTokenizer(next);
        String first_token = st.nextToken();


        if (next.equals("list")){
            print_list();
        } else if (first_token.equals("delete")) {
            try {
                int index = Integer.parseInt(next.substring(7)) - 1;
                markRemove(index);
            } catch (DukeException d) {
                throw d;
            } catch (Exception e) {
                throw new DukeException("OOPS! delete should follow by a number");
            }
        }

        else if (first_token.equals("done")) {
            try {
                int index = Integer.parseInt(next.substring(5)) - 1;
                Task temp = tasks.get(index);
                markDone(temp);
            } catch (Exception e) {
                throw new DukeException("OOPS! done should follow by a number");
            }

        } else {
            Task itemToAdd = null;

            if (first_token.equals("deadline")) {
                try {
                    next = next.substring(9);
                    String[] temp = next.split(" /by ");
                    itemToAdd = new Deadline(temp[0], temp[1]);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! Wrong input format for deadline");
                } catch (DateTimeParseException e) {
                    throw new DukeException("OOPS!!! Wrong format of time, try yyyy-mm-dd");
                }


            } else if (first_token.equals("event")) {
                try {
                    next = next.substring(6);
                    String[] temp = next.split(" /at ");
                    itemToAdd = new Event(temp[0], temp[1]);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! Wrong input format for event");
                } catch (DateTimeParseException e) {
                    throw new DukeException("OOPS!!! Wrong format of time, try yyyy-mm-dd");
                }
            } else if (first_token.equals("todo")) {
                try {
                    next = next.substring(5);
                    itemToAdd = new Todo(next);
                    if (next.equals("")) {
                        throw new Exception("empty todo");
                    }
                } catch (Exception e) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
            }

            if (itemToAdd != null) {
                tasks.add(itemToAdd);
                String out = line + "\n" + space + "Got it. I've added this task: " + "\n" + space
                        + "  " + itemToAdd + "\n" + space + "Now you have " + (count+1) +
                        " tasks in your list." + "\n" + line;
                System.out.println(out);
                count++;
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void respond(String in) {
        String output = line + "\n" + space + in + "\n" + line;
        System.out.println(output);
    }

    public static void introduce() {
        String intro_message = "Hello! I'm Duke" + "\n" + space + "What can I do for you?";
        respond(intro_message);
    }
    public static void print_list() {
        String output = line + "\n" + space + "Here are the tasks in your list: ";
        for (int i = 0; i < count; i++) {
            int index = i+1;
            output += "\n" + space + index + ". " + tasks.get(i);
        }
        output += "\n" + line;
        System.out.println(output);
    }

    public static void markDone(Task t) {
        t.markAsDone();
        String output = line + "\n" + space + "Nice! I've marked this task as done: "
                + "\n" + space + t + "\n" + line;
        System.out.println(output);
    }

    public static void markRemove(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("OOPS!!! Invalid task index to delete");
        }
        Task i = tasks.remove(index);
        count--;
        String output = line + "\n" + space + "Noted. I've removed this task: "
                + "\n" + space + "  " + i + "\n" + space + "Now you have " + count +
                " tasks in your list." + "\n" + line;
        System.out.println(output);
    }

    public static ArrayList<Task> loadTask() throws FileNotFoundException {
        File f = new File("../duke/data/duke.txt");
        Scanner s = new Scanner(f);
        if (!s.hasNext()) {
            return new ArrayList<>();
        } else {
            ArrayList<Task> out = new ArrayList<>();
            while (s.hasNext()) {
                StringTokenizer st = new StringTokenizer(s.nextLine(),"|");
                String type = st.nextToken();
                int status = Integer.parseInt(st.nextToken());
                Task tba;
                if (type.equals("T")) {
                    tba = new Todo(st.nextToken());
                } else if (type.equals("D")) {
                    tba = new Deadline(st.nextToken(), st.nextToken());
                } else {
                    tba = new Event(st.nextToken(), st.nextToken());
                }
                if (status == 1) {
                    tba.isDone = true;
                }
                out.add(tba);
            }
            return out;
        }
    }

    public static void saveTask() throws FileNotFoundException, IOException {
        FileWriter fw = new FileWriter("../duke/data/duke.txt");
        if (count == 0) {
            fw.write("");
        } else {
            for (int i = 0; i < count - 1; i++) {
                fw.write(tasks.get(i).generateWriteFormat());
                fw.write('\n');
            }
            fw.write(tasks.get(count - 1).generateWriteFormat());

            fw.close();
        }
    }
}

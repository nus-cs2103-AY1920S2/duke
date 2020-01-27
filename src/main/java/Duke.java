import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        output(new String[]{"Hello! I'm Duke\n     What can I do for you?"});
        Scanner sc = new Scanner(System.in);

        ArrayList<Task> list = null;
        try {
            list = loadFileContents();
        } catch(FileNotFoundException e) {
            list = new ArrayList<>();
            createNewFile();
        } catch(DukeException e) {
            list = new ArrayList<>();
            output(new String[]{e.getMessage()});
        }
        String op = sc.nextLine();
        while(!op.equals("bye")) {
            if (op.equals("list")) {
                show_list(list);
            } else {
                try {
                    String[] temp = op.split(" ", 2);
                    String cmd = temp[0];
                    switch (cmd) {
                    case "delete":
                        delete(temp, list);
                        break;
                    case "done":
                        done(temp, list);
                        break;
                    case "todo":
                        todo(temp, list);
                        break;
                    case "deadline":
                        deadline(temp, list);
                        break;
                    case "event":
                        event(temp, list);
                        break;
                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException e) {
                    output(new String[]{e.getMessage()});
                }

            }
            op = sc.nextLine();
        }
        saveTasksToFile(list);
        output(new String[]{"Bye. Hope to see you again soon!"});
    }

    private static void createNewFile() {
        try {
            Path path = Paths.get("data/duke.txt");
            Path parent = path.getParent();
            Files.createDirectories(parent);
            Files.createFile(path);
        } catch (IOException e) {
            output(new String[] {"Could no create file!"});
        }
    }

    private static ArrayList<Task> loadFileContents() throws FileNotFoundException, DukeException {
        File f = new File("data/duke.txt");
        Scanner s = new Scanner(f);
        ArrayList<Task> list = new ArrayList<>();
        while (s.hasNextLine()) {
            String[] task = s.nextLine().split(" \\| ");
            Task temp = null;
            switch (task[0]) {
                case "T":
                    temp = new ToDo(task[2]);
                    break;
                case "D":
                    temp = new Deadline(task[2], task[3]);
                    break;
                case "E":
                    temp = new Event(task[2], task[3]);
                    break;
                default:
                    break;
            }
            if (task[1].equals("1")) {
                temp.markAsDone();
            }
            list.add(temp);
        }
        s.close();
        return list;
    }

    private static void saveTasksToFile(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task: list) {
                fw.write(task.toSaveName());
            }
            fw.close();
        } catch (IOException e) {
            output(new String[] {"OOPS!!! Could not save data."});
        }

    }

    private static void delete(String[] temp, ArrayList<Task> list) throws DukeException {
        try {
            if (temp[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description of delete cannot be empty.");
            }
            String body = temp[1];
            Task task = list.remove(Integer.parseInt(body) - 1);
            int size = list.size();
            output(new String[]{"Noted. I've removed this task:", " " + task.toString(),
                "Now you have " + size + " tasks in the list."});
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of delete cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Index is out of bounds.");
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of delete have to be a number.");
        }
    }

    private static void done(String[] temp, ArrayList<Task> list) throws DukeException {
        try {
            if (temp[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description of done cannot be empty.");
            }
            String body = temp[1];
            Task task = list.get(Integer.parseInt(body) - 1);
            task.markAsDone();
            output(new String[]{"Nice! I've marked this task as done:",
                    " " + task.toString()});
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of done cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Index is out of bounds.");
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of done have to be a number");
        }
    }
    private static void todo(String[] temp, ArrayList<Task> list) throws DukeException {
        try {
            String body = temp[1];
            if (body.trim().equals("")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            Task task = new ToDo(body);
            int size = list.size();
            output(new String[]{"Got it. I've added this task:", " " + task.toString(),
                    "Now you have " + (size + 1) + " tasks in the list."});
            list.add(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
    private static void deadline(String[] temp, ArrayList<Task> list) throws DukeException {
        try {
            String body = temp[1];
            String[] task_date = body.split(" /by ");
            if (body.trim().equals("") || task_date[0].trim().equals("") ||
                    task_date[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description or by of a deadline cannot be empty.");
            }
            Task task = new Deadline(task_date[0], task_date[1]);
            int size = list.size();
            output(new String[]{"Got it. I've added this task:", " " + task.toString(),
                    "Now you have " + (size + 1) + " tasks in the list."});
            list.add(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description or by of a deadline cannot be empty.");
        }
    }
    private static void event(String[] temp, ArrayList<Task> list) throws DukeException {
        try {
            String body = temp[1];
            String[] task_date = body.split(" /at ");
            if (body.trim().equals("") || task_date[0].trim().equals("") ||
                    task_date[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description or at of an event cannot be empty.");
            }
            Task task = new Event(task_date[0], task_date[1]);
            int size = list.size();
            output(new String[]{"Got it. I've added this task:", " " + task.toString(),
                    "Now you have " + (size + 1) + " tasks in the list."});
            list.add(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description or at of an event cannot be empty.");
        }
    }

    private static void output(String[] op) {
        String divider = "    ----------------------------------------------------------";
        System.out.println(divider);
        for (String s: op) {
            System.out.println("     " + s);
        }
        System.out.println(divider);
    }
    private static void show_list(ArrayList<Task> list) {
        String divider = "    ----------------------------------------------------------";
        System.out.println(divider);
        System.out.println("     Here are the tasks in your list:");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println("     " + (i+1) + ". " + list.get(i).toString());
        }
        System.out.println(divider);
    }
}

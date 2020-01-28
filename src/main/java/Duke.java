import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        // get file ready to save to file.
        String home = System.getProperty("user.dir");
        // inserts correct file path separator on *nix and Windows
        java.nio.file.Path path = java.nio.file.Paths.get(home, "data", "duke.txt");
//        System.out.println(path);
//        System.out.println(java.nio.file.Files.exists(path));


        String divider = "____________________________________________________________";
        System.out.println("My name is Jarvis!\nHow may I provide my services on this fine day?\n" + divider);

        ArrayList<Task> tasks = new ArrayList<>();
        int i = 0;
        // Load data from file
        try {
            List<String> loadedTasks = Files.readAllLines(path);
            i = Math.max(loadedTasks.size(), 0);
            for (String s : loadedTasks) {
                if(!s.equals("")) {
                    tasks.add(Task.load(s));
                }
            }
            if (!tasks.isEmpty()){
                System.out.println("Welcome back, you have the following tasks on hand:");
                for(Task t:tasks){
                    System.out.println(t);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext() /*!nextLine.equals("bye")*/) {
            String nextLine = sc.nextLine();
            try {
                validate(nextLine);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Please try again!");
                System.out.println(divider);
                continue;
            }

            // action: DELETE
            if(nextLine.contains("delete")) {
                String[] substrings = nextLine.split(" ");
                System.out.println("Successfully deleted the following task:\n"
                        + tasks.get(Integer.parseInt(substrings[1])-1));
                tasks.remove(Integer.parseInt(substrings[1])-1);
                i--;
                System.out.println("You have " + i + " tasks in your list currently.");
                System.out.println(divider);
                String s = "";
                for (Task t: tasks) {
                    s = s + t.format() + "\n";
                }
                write(s, path);
                continue;
            }

            // to check if 'list' service is called
            if (nextLine.equals("list")) {
                System.out.println("Here are the current tasks in your list:");
                int listStart = 1;
                for (Task task : tasks) {
                    System.out.println(listStart + ". " + task);
                    listStart++;
                }
                System.out.println(divider);
                continue;
            } else if (nextLine.contains("bye")) {
                break;
            }

            // if the action is done
            if (nextLine.contains("done")) {
                String[] substrings = nextLine.split(" ");
                int taskNum = Integer.parseInt(substrings[1]);
                tasks.get(taskNum - 1).taskDone();
                System.out.println("Alright! You have successfully completed:");
                System.out.println(tasks.get(taskNum - 1));
                System.out.println(divider);
                String s = "";
                for (Task t: tasks) {
                    s = s + t.format() + "\n";
                }
                write(s, path);
                continue;
            }

            // different Task additions
            if (nextLine.contains("todo")) {
                String[] substrings = nextLine.split(" ",2);
                tasks.add(new Todo(substrings[1]));
            } else if (nextLine.contains("event")) { // event creation
                String[] substrings = nextLine.split(" ",2);
                tasks.add(new Event(substrings[1].split(" /at")[0], substrings[1].split("/at ")[1]));
            } else if (nextLine.contains("deadline")) {            // deadline creation
                String[] substrings = nextLine.split(" ",2);
                tasks.add(new Deadline(substrings[1].split(" /by")[0], substrings[1].split("/by ")[1]));
            }
            System.out.println("Successfully added:\n" + tasks.get(i).toString());
            // If the number of tasks differs in the loop, needa update the Hard disk file
            String s = "";
            for (Task t: tasks) {
                s = s + t.format() + "\n";
            }
            write(s, path);

            i++;
            System.out.println("You now have " + i + " number of tasks in the list");
            System.out.println(divider);


        }
        System.out.println("Hope my service has been of great help! See you again!");
    }

    public static void validate(String s) throws DukeException {
        String action = s.split(" ")[0];
        if (!action.equals("list") && !action.equals("done") && !action.equals("todo")
                && !action.equals("event") && !action.equals("deadline") && !action.equals("bye")
                && !action.equals("delete")) {
            throw new DukeException("This is not a valid action you may take sir.");
        } else if ((s.contains("todo") || s.contains("event") || s.contains("deadline")
                || s.contains("done") || s.contains("delete")) && s.split(" ").length == 1) {
            throw new DukeException("Your description may not be empty");
        }
    }

    public static void write(String s, Path path) {
        if(!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (FileAlreadyExistsException e) {

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            writer.write(s);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}



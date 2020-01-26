import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        // Initialize Duke from file
        TaskList tasks = new TaskList();
        Path saveDir = getSaveDirectory();
        boolean directoryExists = java.nio.file.Files.exists(saveDir);

        if (directoryExists) {
            try {
                boolean loadSuccessful = tasks.loadFromFile(saveDir);
                if (loadSuccessful) {
                    System.out.println("    Tasklist loaded!");
                }
            } catch (FileNotFoundException e) {
                System.out.println("    No prior tasklist found...");
            } catch (IOException e) {
                System.out.println("    Existing tasklist cannot be read...");
            } catch (ClassNotFoundException e) {
                System.out.println("    Existing tasklist cannot be read...");
            }
        }

        out("Hello! I'm Duke", "What can I do for you?");
        input: while (in.hasNext()) {
            String next = in.next();
            try {
                switch (next.toLowerCase()) {
                case "bye":
                    out("Bye. Hope to see you again soon!");
                    break input;
                case "list":
                    out(tasks.list());
                    break;
                case "done":
                    out(tasks.done(in.nextInt()));
                    tasks.saveToFile(getSaveDirectory());
                    break;
                case "todo":
                    out(tasks.addTodo(in.nextLine().split("/")));
                    tasks.saveToFile(getSaveDirectory());
                    break;
                case "deadline":
                    out(tasks.addDeadline(in.nextLine().split(" /by ")));
                    tasks.saveToFile(getSaveDirectory());
                    break;
                case "event":
                    out(tasks.addEvent(in.nextLine().split(" /at ")));
                    tasks.saveToFile(getSaveDirectory());
                    break;
                case "delete":
                    out(tasks.delete(in.nextInt()));
                    tasks.saveToFile(getSaveDirectory());
                    break;
                default:
                    out("invalid command:", echo(next), "please try again");
                    break;
                }
            } catch(IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        in.close();
    }

    private static void out(String... ss) {
        String border = "    ____________________________________________________________";
        System.out.println(border);
        for (String s : ss) {
            System.out.println("    " + s);
        }
        System.out.println(border);
    }

    private static Path getSaveDirectory() {
        String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir, "data");
        return path;
    }

    private static String echo(String s) {
        return s;
    }
}

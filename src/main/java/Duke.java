import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    public static void main(String[] args) {
        
        // Initialize Duke from file
        Ui ui = new Ui();
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
        input: while (ui.hasNext()) {
            String command = ui.getCommand();
            try {
                switch (command.toLowerCase()) {
                case "bye":
                    out("Bye. Hope to see you again soon!");
                    break input;
                case "list":
                    out(tasks.list());
                    break;
                case "done":
                    out(tasks.done(Integer.parseInt(ui.getArguments())));
                    tasks.saveToFile(getSaveDirectory());
                    break;
                case "todo":
                    out(tasks.addTodo(ui.getArguments().split("/")));
                    tasks.saveToFile(getSaveDirectory());
                    break;
                case "deadline":
                    out(tasks.addDeadline(ui.getArguments().split(" /by ")));
                    tasks.saveToFile(getSaveDirectory());
                    break;
                case "event":
                    out(tasks.addEvent(ui.getArguments().split(" /at ")));
                    tasks.saveToFile(getSaveDirectory());
                    break;
                case "delete":
                    out(tasks.delete(Integer.parseInt(ui.getArguments())));
                    tasks.saveToFile(getSaveDirectory());
                    break;
                default:
                    out("invalid command:", "  " + command + " " + ui.getArguments() +  "please try again");
                    break;
                }
            } catch(IncorrectArgumentException e) {
                out(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        Path path = Paths.get(dir, "data.duke");
        return path;
    }
}

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;


public class Duke {
    private static void hLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        hLine();
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        hLine();
    }

    private static void printWithHLine(String message) {
        hLine();
        System.out.println(message);
        hLine();
    }

    private static void saveData(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks) {
            fw.write(t.toDatabaseString());
        }
        fw.close();
    }

    private static Task databaseStringToTask(String s) {
        String[] split = s.split("\\|");
        System.out.println(s);
        System.out.println(Arrays.toString(split));
        Task newTask = new Todo("error in decoding database string");

        switch (split[0]) {
        case "T":
            newTask = new Todo(split[2]);
            break;
        case "D":
            newTask = new Deadline(split[2], split[3]);
            break;
        case "E":
            newTask = new Event(split[2], split[3]);
            break;
        default:
            System.err.println("default case hit in decoding database string to task");
        }

        // set completion status
        if (split[1].equals("1")) {
            newTask.markAsDone();
        }
        return newTask;
    }

    private static void loadData(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            tasks.add(databaseStringToTask(s.nextLine()));
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        // load data upon startup
        try {
            loadData("C:\\Users\\Pang Jia Da\\Desktop\\CS2103\\duke\\data\\duke.txt", tasks);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.err.println("error loading saved data from file");
            e.printStackTrace();
        }

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = scanner.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
                // save data
                saveData("C:\\Users\\Pang Jia Da\\Desktop\\CS2103\\duke\\data\\duke.txt", tasks);
            } catch (DukeException e) {
                System.out.println(e.getErrorMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                // do nothing
            }
        }

        printWithHLine("Bye. Hope to see you again soon!");
    }

}
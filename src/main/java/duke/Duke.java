package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.io.FileNotFoundException;

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.commands.CommandHandler;
import duke.exceptions.DukeException;

public class Duke {
    public static void main(String[] args) {
        System.out.print(createGreeting());
        Scanner sc = new Scanner(System.in);
        String filePath = "data/tasks.txt";
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = load(filePath);
            System.out.print(formatReply("Save file loaded!"));
        } catch (FileNotFoundException e) {
            System.out.print(formatReply("Save file not found!"));
        } catch (DukeException e) {
            System.out.print(formatReply(e.getMessage()));
        }
        CommandHandler handler = new CommandHandler(tasks);
        while (handler.isActive()) {
            handler.executeCmd(sc.nextLine());
        }
        try {
            save(filePath, tasks);
            System.out.print(formatReply("Save Success! See you next time!"));
        } catch (IOException e) {
            System.out.print(formatReply("Save Failure :-(. Try again next time!"));
        }
        sc.close();
    }

    private static String createGreeting() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append(logo);
        sb.append("\n");
        sb.append(formatReply("Hello! I'm Duke\nGive me a moment while I locate your save file..."));
        return sb.toString();
    }

    private static String formatReply(String str) {
        String[] lines = str.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        String lineBreak = "===========================================================\n";
        for (String line : lines) {
            sb.append("> ");
            sb.append(line);
            sb.append("\n");
        }
        sb.append(lineBreak);
        return sb.toString();
    }

    private static List<Task> load(String filePath) throws FileNotFoundException, DukeException {
        List<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(new File(filePath));
        while (s.hasNextLine()) {
            Task task;
            switch (s.nextLine()) {
            case "todo":
                task = new Todo(s.nextLine());
                break;
            case "deadline":
                task = new Deadline(s.nextLine(), LocalDateTime.parse(s.nextLine()));
                break;
            case "event":
                task = new Event(s.nextLine(), LocalDateTime.parse(s.nextLine()), LocalDateTime.parse(s.nextLine()));
                break;
            default:
                throw new DukeException("Save file corrupt!");
            }
            if (s.nextLine().equals("true")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        s.close();
        return tasks;
    }

    private static void save(String filePath, List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.toSaveable() + System.lineSeparator());
        }
        fw.close();
    }

}

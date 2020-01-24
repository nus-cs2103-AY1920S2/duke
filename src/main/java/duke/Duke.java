package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import duke.tasks.Task;
import duke.commands.CommandHandler;

public class Duke {
    public static void main(String[] args) {
        System.out.print(createGreeting());
        Scanner sc = new Scanner(System.in);
        String filePath = "data/tasks.txt";
        List<Task> tasks;
        try {
            tasks = load(filePath);
        } catch (FileNotFoundException e) {
            new File(filePath);
            tasks = new ArrayList<>();
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
            System.out.print(formatReply(e.getMessage()));
        }
        sc.close();
    }

    private static String createGreeting() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append(logo);
        sb.append("\n");
        sb.append(formatReply("Hello! I'm Duke\nWhat can I do for you?"));
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

    private static List<Task> load(String filePath) throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    private static void save(String filePath, List<Task> tasks) throws IOException {        
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.toSaveable() + System.lineSeparator());
        }
        fw.close();
    }

}

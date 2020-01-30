import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        TaskHandler taskHandler = new TaskHandler();
        // Saver saver = new Saver();
        taskHandler.greet();
        taskHandler.handle();
        // saver.fullSaver(taskHandler.taskList);
    }
}

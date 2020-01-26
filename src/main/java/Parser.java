import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private static Scanner sc = new Scanner(System.in);
    private static TaskList tasks = new TaskList();


    public static void parse() {
        while(true) {
            String command = sc.next();


            boolean wantToQuit = false;
            boolean nothingToAdd = false;
            boolean invalidIndex = false;

            switch (command) {
                case "bye":
                    wantToQuit = true;
                    break;

                case "list":
                    nothingToAdd = true;
                    tasks.list();
                    break;

                case "done":
                    int index = sc.nextInt();
                    try {
                        tasks.done(index);
                    } catch (InvalidIndexException e) {
                        System.out.println(e);
                    }
                    nothingToAdd = true;
                    break;

                case "delete":
                    int indice = sc.nextInt();
                    try {
                        tasks.delete(indice);
                    } catch (InvalidIndexException e) {
                        System.out.println(e);
                    }
                    nothingToAdd = true;
                    break;


            }

            if (wantToQuit) {
                break;
            } else if (nothingToAdd || invalidIndex) {
                continue;
            }

            try {
                String taskDescription = sc.nextLine();
                Task currentTask = TaskHandler.taskHandler(command, taskDescription);
                tasks.add(currentTask);
            } catch (InvalidInputException e) {
                System.out.println(e);
            }
        }

        tasks.saveToDisk();
    }
}

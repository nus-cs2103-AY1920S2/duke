import java.util.Scanner;

public class TaskHandler {

    // Serves the user to do the bulk of the work
    public void serveUser() {

        // Scanner object to take in user input
        Scanner io = new Scanner(System.in);

        while (io.hasNextLine()) {

            String command = io.nextLine();

            if (command.equals("bye")) {
                return;
            } else {
                Task currentTask = new Task(command);
                currentTask.print();
            }

        }
    }

}

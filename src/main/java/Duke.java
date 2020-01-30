import java.util.Scanner;
import exceptions.BaseException;
import exceptions.WrongCommandException;

public class Duke {
    public static final int MAX_TASKS = 100;
    public static final String WELCOME_MESSAGE = "Wussup Dawggg! I'm Dukeee\nWhat you want me do?";
    public static final String GOODBYE_MESSAGE = "Bye!\nStay cool bruh! (((:";

    private UserInterface userInterface;
    public Duke() {
        this.userInterface = new UserInterface();
    }
    
    public void start() {
        this.userInterface.show(WELCOME_MESSAGE);

        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager(MAX_TASKS);
          
        while (scanner.hasNext()) {
            try {
                String command = scanner.next();
                String nextArgs = scanner.nextLine().trim();

                if      (command.equals("bye"))                 break;
                else if (command.equals("list"))                this.userInterface.show(taskManager.toString());                
                else if (command.equals("done"))                this.userInterface.show(taskManager.setTaskDone(nextArgs));
                else if (command.equals("todo"))                this.userInterface.show(taskManager.addTodoTask(nextArgs));
                else if (command.equals("deadline"))            this.userInterface.show(taskManager.addDeadlineTask(nextArgs));
                else if (command.equals("event"))               this.userInterface.show(taskManager.addEventTask(nextArgs));
                else if (command.equals("delete"))              this.userInterface.show(taskManager.deleteTask(nextArgs));
                else                                            throw new WrongCommandException(String.format("The command '%s' is not supported", command));
            } catch (BaseException e) {
                this.userInterface.show(e.getMessage());
            } catch (Exception e) {
                this.userInterface.show("Caught some other exception! Notify developer!");
                this.userInterface.show(e.getMessage());
            }
        }

        scanner.close();
        this.userInterface.show(GOODBYE_MESSAGE);
    }
}

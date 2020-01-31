package app;

import java.util.Scanner;

import app.exceptions.BaseException;
import app.exceptions.WrongCommandException;
import app.core.UserInterface;
import app.core.TaskManager;

public class Duke {
    public static final int MAX_TASKS = 100;
    public static final String WELCOME_MESSAGE = "Wussup Dawggg! I'm Dukeee\nWhat you want me do?";
    public static final String GOODBYE_MESSAGE = "Bye!\nStay cool bruh! (((:";

    private UserInterface userInterface;
    public Duke() {
        this.userInterface = new UserInterface();
    }
    
    public void start() {
        this.userInterface.render(WELCOME_MESSAGE);

        TaskManager taskManager = new TaskManager(MAX_TASKS);
          
        while (!this.userInterface.isClosed()) {
            try {
                String input = this.userInterface.listen();

                if      (command.equals("bye"))                 this.userInterface.close();
                else if (command.equals("list"))                this.userInterface.render(taskManager.toString());                
                else if (command.equals("done"))                this.userInterface.render(taskManager.setTaskDone(nextArgs));
                else if (command.equals("todo"))                this.userInterface.render(taskManager.addTodoTask(nextArgs));
                else if (command.equals("deadline"))            this.userInterface.render(taskManager.addDeadlineTask(nextArgs));
                else if (command.equals("event"))               this.userInterface.render(taskManager.addEventTask(nextArgs));
                else if (command.equals("delete"))              this.userInterface.render(taskManager.deleteTask(nextArgs));
                else                                            throw new WrongCommandException(String.format("The command '%s' is not supported", command));
            } catch (BaseException e) {
                this.userInterface.render(e.getMessage());
            } catch (Exception e) {
                this.userInterface.render("Caught some other exception! Notify developer!");
                this.userInterface.render(e.getMessage());
            }
        }

        this.userInterface.render(GOODBYE_MESSAGE);
    }
}

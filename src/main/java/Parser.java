import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private Scanner sc = GlobalScanner.sc;
    private TaskList tasks = new TaskList();
    private ActionHandler actions = new ActionHandler();


    public void parse() {
        boolean hasNextAction = true;
        while(hasNextAction) {
            String command = sc.next();
            Action currentAction = actions.decideAction(command);
            currentAction.doSomething(tasks);
            hasNextAction = currentAction.hasNextAction();
        }

        tasks.saveToDisk();
    }
}

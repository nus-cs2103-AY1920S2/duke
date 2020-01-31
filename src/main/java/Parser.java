import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private Scanner sc = GlobalScanner.sc;
    private TaskList tasks = new TaskList();
    private ActionHandler actions = new ActionHandler();

    /**
     * As long as the user hasn't said bye. The parser will continue to read inputs from the user and
     * attempt to decipher the commands of the user. After the user is done, parser will save the TaskList
     * into a txt file
     *
     */
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

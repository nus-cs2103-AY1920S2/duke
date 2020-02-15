import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private static TaskList tasks = new TaskList();
    private ActionHandler actions = new ActionHandler();
    private boolean isExiting = false;

    /**
     * As long as the user hasn't said bye. The parser will continue to read inputs from the user and
     * attempt to decipher the commands of the user. After the user is done, parser will save the TaskList
     * into a txt file
     */
    public String parse(String input) {
        Action currentAction = actions.decideAction(input);
        String myResponse = currentAction.doSomething(tasks);
        this.isExiting = !currentAction.hasNextAction();

        return myResponse;
    }

    public boolean getIsExiting(){
        return this.isExiting;
    }
}

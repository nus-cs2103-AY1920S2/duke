import java.util.NoSuchElementException;
import java.util.Scanner;


public class ActionHandler {
    enum allowedTask {todo, event, deadline}

    /**
     * Returns an action determined by the String command. By default, if the command is not any of the keywords
     * returns a newTaskAction
     *
     * @param input specifies the type of action to return
     * @return Action
     */
    public Action decideAction(String input) {
        Scanner sc = new Scanner(input);
        String command;
        try {
            command = sc.next();
        } catch (NoSuchElementException e) {
            return new EmptyAction();
        }

        Action myAction = new EmptyAction();
        switch (command) {
        case "bye":
            myAction = new byeAction();
            break;
        case "list":
            myAction = new listAction();
            break;
        case "done":
            int index = -1;
            try {
                index = sc.nextInt();
            } catch (NoSuchElementException e) {

            }
            myAction = new doneAction(index);
            break;
        case "delete":
            int indix = -1;
            try {
                indix = sc.nextInt();
            } catch (NoSuchElementException e) {

            }
            myAction = new deleteAction(indix);
            break;
        case "find":
            String keyword = "";
            try {
                keyword = sc.next();
            } catch (NoSuchElementException e) {
                System.out.println("Ketword cannot be empty");
            }
            myAction = new findAction(keyword);
            break;
        case "tag":
            String tagDescription;
            try {
                try {
                    tagDescription = sc.next();
                } catch (NoSuchElementException e) {
                    System.out.println("Tag description cannot be empty");
                    break;
                }
                int tagIndex = sc.nextInt();
                myAction = new tagAction(tagIndex, tagDescription);
            } catch(Exception e){
                return new EmptyAction();
            }
        }


        for (allowedTask a : allowedTask.values()) {
            if (a.toString().equals(command)) {
                return new newTaskAction(input);
            }
        }
        return myAction;
    }
}

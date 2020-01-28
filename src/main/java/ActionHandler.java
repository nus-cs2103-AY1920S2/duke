public class ActionHandler {
    /**
     * Returns an action determined by the String command. By default, if the command is not any of the keywords
     * returns a newTaskAction
     * @param command specifies the type of action to return
     * @return Action
     */
    public Action decideAction(String command) {
        Action myAction = new newTaskAction(command);
        switch(command){
            case "bye":
                myAction = new byeAction();
                break;
            case "list":
                myAction = new listAction();
                break;
            case "done":
                myAction = new doneAction();
                break;
            case "delete":
                myAction = new deleteAction();
                break;
        }
        return myAction;
    }
}

public class ActionHandler {
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

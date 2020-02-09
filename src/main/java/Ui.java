
public class Ui {

    public void showStartMessage() {
        System.out.println("Hello Sir\nWhat can I do for you today?");
    }

    public void showGoodbyeMessage() {
        System.out.println("Have a nice day sir!");
    }

    public void showTaskDoneMessage(Task task, int taskNumber) {
        System.out.println("I shall mark task " + taskNumber + " as completed");
        System.out.println(task.toString());
    }

    public void showTaskList(MyList taskList) {
        System.out.println("Here are the list of task:");
        taskList.printList();
        System.out.println("You have " + taskList.getArraySize() + " task");
    }

    public void showTaskAddedMessage(Task task, MyList taskList) {
        System.out.println("I have added the following task:");
        System.out.println(task.toString());
        System.out.println("You now have " + taskList.getArraySize() + " task");
    }

    public void showTaskDeletedMessage(int taskNumber) {
        System.out.println("I shall delete task " + taskNumber);
    }

    public String showWrongCommandError() {
        return "You have entered a command I do not understand";
    }

    public String showDeadlineDescriptionError() {
        return "Sorry, the description of a deadline cannot be empty";
    }

    public String showEventDescriptionError() {
        return "Sorry, the description of an event cannot be empty";
    }

    public String showTodoDescriptionError() {
        return "Sorry, the description of todo cannot be empty";
    }
}

import java.io.IOException;

public class AddCommand implements Command {

    @Override
    public void execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException {

        if (task.contains("deadline")) {
            String[] retrieveDateArray = task.split(" /by ");
            if (retrieveDateArray.length < 2) {
                throw new DukeException(ui.showDeadlineDescriptionError());
            }
            String[] retrieveTaskArray = retrieveDateArray[0].split(" ", 2);
            Deadline deadline = new Deadline(retrieveTaskArray[1], retrieveDateArray[1]);
            taskList.setListArray(deadline);
            ui.showTaskAddedMessage(deadline, taskList);
            storage.saveToFile(deadline.toString());
        }

        else if(task.contains("event")){
            String[] retrieveDateArray = task.split(" /at ");
            if(retrieveDateArray.length < 2){
                throw new DukeException(ui.showEventDescriptionError());
            }
            String[] retrieveTaskArray = retrieveDateArray[0].split(" ", 2);
            Event event = new Event(retrieveTaskArray[1], retrieveDateArray[1]);
            taskList.setListArray(event);
            ui.showTaskAddedMessage(event, taskList);
            storage.saveToFile(event.toString());
        }

        else {
            String[] retrieveTaskArray = task.split(" ", 2);
            if(retrieveTaskArray.length < 2){
                throw new DukeException(ui.showTodoDescriptionError());
            }
            Todo todo = new Todo(retrieveTaskArray[1]);
            taskList.setListArray(todo);
            ui.showTaskAddedMessage(todo, taskList);
            storage.saveToFile(todo.toString());
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }

}

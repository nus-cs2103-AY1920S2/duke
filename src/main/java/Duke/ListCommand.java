package Duke;

public class ListCommand extends Command {
    public ListCommand() {
    }
    public void execute(Ui ui, Storage storage, TaskList tasklist) {
        try {
            if (tasklist.getTaskListSize() == 0) {
                throw new EmptyListException();
            } else {
                System.out.println("Here are the task in your list:");
                for (int i = 0; i < tasklist.getTaskListSize(); i++) {
                    System.out.println((i + 1) + ". " + tasklist.getTask(i).toString());
                }
            }
        }catch (DukeException ex){
            System.out.print(ex);
        }
    }
}

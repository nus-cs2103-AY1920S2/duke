public class Help extends Command {
    private int index;

    Help(int index) {
        this.index = index;
    }

    String execute(TaskList tasks, Storage storage) {
        switch (this.index) {
        case 1:
            return "This function can be used to add a task to the list.\n"
                    + "The task you add can be of type todo, event or deadline\n\n"
                    + "Format for todo is \"todo task_name\"\n"
                    + "Format for event is \"event task_name /at yyyy-MM-dd HHmm\"\n"
                    + "Format for deadline is \"deadline task_name /by yyyy-MM-dd HHmm\"\n";
        case 2:
            return "This function can be used to delete a task.\n"
                    + "Format for delete is \"delete task_number\"\n";
        case 3:
            return "This function can be used to find a task in the list.\n"
                    + "It will display all tasks that contain the keyword specified.\n"
                    + "Format for find is \"find key_word\"\n";
        case 4:
            return "This function marks a task in your list as complete by changing [N] to [Y].\n"
                    + "Format for done \"done task_number\"\n";
        case 5:
            return "This functions displays a list of all the tasks in your list.\n"
                    + "It uses T for todo, D for deadline and E for event.\n"
                    + "Format for list is \"list\"\n";
        case 6:
            return "This function will let you exit Duke\n"
                    + "Format for exit is \"bye\"\n";
        default:
            return "This is not a valid function! Please try again.";
        }
    }
}

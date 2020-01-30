public class ExitCommand extends Command {

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Bye-bye! See you again, my friend!");
    }
}

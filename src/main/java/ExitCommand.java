public class ExitCommand extends Command {

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(Duke.LINE
                + "\n"
                + "Bye-bye! See you again, my friend!"
                + "\n"
                + Duke.LINE);
        storage.save(tasks);
    }
}

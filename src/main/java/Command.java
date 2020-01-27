import java.io.IOException;

abstract class Command {

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}

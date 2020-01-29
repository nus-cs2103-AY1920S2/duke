public class ByeCommand extends Command {
    public static boolean run() {
        Ui.end();
        return true;
    }
}

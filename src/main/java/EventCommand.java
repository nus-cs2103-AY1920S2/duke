public class EventCommand extends Command {

    public EventCommand(Event e) {
        super();
        this.command = "event";
        this.task = e;
    }
}
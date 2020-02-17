/**
 * Represent a "fixedTask" command given to TaskList.
 */
public class FixedTaskCommand extends Command {

    public FixedTaskCommand(FixedDurationTask fdt) {
        super();
        this.command = "fixedTask";
        this.task = fdt;
    }
}
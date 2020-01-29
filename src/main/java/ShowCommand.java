public class ShowCommand extends Command{
    private String instruction;
    private String[] replyArr;

    public ShowCommand(String instruction, String[] replyArr) {
        this.instruction = instruction;
        this.replyArr = replyArr;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        switch (instruction) {
            case "date":
                taskList.dateInstruction(replyArr);
                break;
            case "list":
                taskList.listInstruction();
                break;
            case "done":
                taskList.doneInstruction(replyArr);
                break;
            default:
                System.out.println("    Sorry! I don't understand what is " + instruction);
        }
    }
}

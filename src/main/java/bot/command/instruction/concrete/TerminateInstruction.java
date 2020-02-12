package bot.command.instruction.concrete;

import bot.Ui;

import bot.command.Command;
import bot.command.instruction.execute.NotStorable;
import bot.command.instruction.parse.OneWordInstruction;

public class TerminateInstruction extends OneWordInstruction
        implements NotStorable {

    public TerminateInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public void printUiMessage(Ui ui) {
        ui.showGoodbye();
    }
}

package bot.command.instruction.execute;

import bot.Ui;

/**
 * An interface that represents Instructions
 * that do not need to access the Storage
 */
public interface NotStorable {
    /**
     * When given a UI, prints the message that
     * this Instruction would generate
     *
     * @param ui The UI to print to
     */
    void printUiMessage(Ui ui);
}

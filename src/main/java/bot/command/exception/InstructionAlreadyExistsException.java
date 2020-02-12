package bot.command.exception;

/**
 * An Exception to be generated when a user tries
 * to alias a word that already refers to
 * an existing Instruction
 */
public class InstructionAlreadyExistsException extends Exception {
    /**
     * Constructor for a InstructionAlreadyExistsException
     *
     * @param instWord The word that already referred to
     *                 another Instruction, such that
     *                 it could not be aliased
     */
    public InstructionAlreadyExistsException(String instWord) {
        super("Conflict detected! " + instWord
                + " is already used!");
    }
}

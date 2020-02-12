package bot.command.instruction;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing an Instruction together with
 * the information it requires. Used to transfer
 * information between the CommandParser and
 * the Executor class
 */
public class ParsedInstruction {
    private final Instruction mainInstruction;
    private final ArrayList<String> requiredArguments;

    /**
     * Constructor for a new ParsedInstruction
     *
     * @param inst The main Instruction of the ParsedInstruction
     * @param reqs The Strings that correspond to the arguments
     *             that are used by the main Instruction
     */
    public ParsedInstruction(Instruction inst, String... reqs) {
        this.mainInstruction = inst;
        this.requiredArguments = new ArrayList<String>(Arrays.asList(reqs));
    }

    /**
     * Constructor for a new ParsedInstruction that
     * does not have any required arguments
     *
     * @param inst The main Instruction of the ParsedInstruction
     */
    public ParsedInstruction(Instruction inst) {
        this.mainInstruction = inst;
        this.requiredArguments = new ArrayList<String>();
    }

    /**
     * Gets the main Instruction that this ParsedInstruction
     * hold the information for
     *
     * @return Returns the main Instruction for this
     *     ParsedInstruction
     */
    public Instruction getInstruction() {
        return this.mainInstruction;
    }

    /**
     * Gets an ArrayList of Strings, containing the
     * arguments that the Instruction requires to
     * execute, in the correct order
     *
     * @return Returns an ArrayList of Strings containing
     *     arguments required by the main Instruction of
     *     this ParsedInstruction
     */
    public ArrayList<String> getArguments() {
        return this.requiredArguments;
    }
}

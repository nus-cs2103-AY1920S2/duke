import java.time.LocalDate;
import java.util.Arrays;

/**
 * Parser decodes user given instruction to determine the appropriate action to be taken by Duke.
 */
public class Parser {
    private Command command;
    private String parameters;
    private LocalDate date;

    /**
     * Constructs a Parser object. Attempts to extract the different sections of the specified instruction
     * into the command, parameters, and date (if any).
     * 
     * @param instruction Input from user as instructions to Duke.
     * @throws InvalidInstructionException If specified instruction is invalid.
     */
    public Parser(String instruction) throws InvalidInstructionException {
        this.command = extractCommand(instruction);

        if (this.command == Command.DONE || this.command == Command.DELETE) {
            try {
                this.parameters = String.valueOf(Integer.parseInt(extractFirstParam(instruction)));
            } catch (NumberFormatException e) {
                throw new InvalidInstructionException("Task number given is not an integer");
            }
        } else if (this.command == Command.TODO) {
            this.parameters = extractDescription(instruction);
        } else if (this.command == Command.DEADLINE || this.command == Command.EVENT) {
            this.parameters = extractDescription(instruction);
            this.date = extractDate(instruction);
        } else if (this.command == Command.FIND) {
            this.parameters = extractFirstParam(instruction);
        }
    }

    /**
     * Gets the command from the instruction.
     * 
     * @return Instruction command.
     */
    public Command getCommand() {
        return this.command;
    }

    /**
     * Gets the parameters from the instruction [after command, before time flag (if any)].
     * 
     * @return Instruction parameters.
     */
    public String getParameters() {
        return this.parameters;
    }

    /**
     * Gets the date from the instruction. 
     * 
     * @return Instruction date.
     */
    public LocalDate getDate() {
        return this.date;
    }
    
    private static Command extractCommand(String input) throws InvalidInstructionException {
        String command = (input.split(" ")[0]).toLowerCase();
        switch (command) {
        case "bye":
            return Command.BYE;
        case "done":
            return Command.DONE;
        case "todo":
            return Command.TODO;
        case "deadline":
            return Command.DEADLINE;
        case "event":
            return Command.EVENT;
        case "list":
            return Command.LIST;
        case "delete":
            return Command.DELETE;
        case "find":
            return Command.FIND;
        default:
            throw new InvalidInstructionException(
                    String.format("Command \"%s\" is not recognized", command));
        }
    }

    private static String extractDescription(String input) throws InvalidInstructionException {
        String[] inputArr = input.split(" ");

        if (inputArr.length <= 1) {
            throw new InvalidInstructionException("No description given");
        }

        String descPortion = input.split(" /")[0];
        String[] descPortionArr = descPortion.split(" ");
        String[] descArr = Arrays.copyOfRange(descPortionArr, 1, descPortionArr.length);

        return String.join(" ", descArr);
    }

    private static LocalDate extractDate(String input) throws InvalidInstructionException {
        String[] inputArr = input.split(" /");

        if (inputArr.length <= 1) {
            throw new InvalidInstructionException("No time flag (/by /at) given");
        }

        String datePortion = inputArr[1];
        String[] datePortionArr = datePortion.split(" ");

        if (datePortionArr.length <= 1) {
            throw new InvalidInstructionException("No timing given");
        }

        return LocalDate.parse(datePortionArr[1]);
    }

    private static String extractFirstParam(String input) throws InvalidInstructionException {
        String[] inputArr = input.split(" ");

        if (inputArr.length <= 1) {
            throw new InvalidInstructionException("No parameters given");
        }

        return input.split(" ")[1];
    }
}

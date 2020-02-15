package duke.util;

import duke.command.Mode;
import duke.command.Operation;
import duke.expense.Category;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EnumUtil {
    static Logger logger = Logger.getLogger(EnumUtil.class.getName());

    /**
     * Checks if the command exists in enum.
     *
     * @param command command
     */
    public static boolean isValidOperation(String command) {
        for (Operation o : Operation.values()) {
            if (o.name().equals(command.toUpperCase())) {
                logger.log(Level.INFO, "operation is " + o.name() + "\n" + "command is " + command);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the command exists in enum.
     *
     * @param command command
     */
    public static boolean isValidMode(String command) {
        for (Mode o : Mode.values()) {
            if (o.name().equals(command.toUpperCase())) {
                logger.log(Level.INFO, "mode is " + o.name());
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if the command exists in enum.
     *
     * @param command command
     */
    public static boolean isValidCategory(String command) {
        for (Category o : Category.values()) {
            if (o.name().equals(command.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

}

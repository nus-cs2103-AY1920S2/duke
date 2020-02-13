package duke.util;

import duke.command.Mode;
import duke.command.Operation;
import duke.expense.Category;

public class EnumUtil {
    public static boolean isValidOperation(String command) {
        for (Operation o : Operation.values()) {
            if (o.name().equals(command.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidMode(String command) {
        for (Mode o : Mode.values()) {
            if (o.name().equals(command.toUpperCase())) {
                return true;
            }
        }
        return false;
    }


    public static boolean isValidCategory(String command) {
        for (Category o : Category.values()) {
            if (o.name().equals(command.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

}

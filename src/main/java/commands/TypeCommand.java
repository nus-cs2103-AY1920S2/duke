package commands;

import duke.UI;
import resources.Statistics;

/**
 * This class is command to invoke part of the statistics class
 *
 * */
public class TypeCommand extends UserCommand {
    private String type;

    /**
     * Instantiates a new User command.
     * Set the ID
     * @param type Takes in a String of Type
     */
    TypeCommand(String type) {
        super(9);
        this.type = type;
    }

    @Override
    public String reply() {
        return UI.getReply("gettingTypes\n") + Statistics.getTypes(type);
    }

}

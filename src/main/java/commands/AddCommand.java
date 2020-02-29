package commands;

import database.MyList;

import duke.UI;

import resources.Task;
import resources.ToDos;
import resources.Deadline;
import resources.Event;

import exceptions.DukeException;

/**
 * Commands.AddCommand is a Commands.Command that handles the adding of items
 * into the list when user calls.
 */
public class AddCommand extends UserCommand {

    private static String desc;
    private static String type;
    private Task t;

    /**
     * Instantiates a new Add command.
     */
    AddCommand() {
        super(2);
    }

    /**
     * Instantiates a new Add command.
     *
     * @param type the type
     * @param desc the desc
     */
    AddCommand(String type, String desc) {
        super(2);
        this.desc = desc;
        this.type = type;
    }

    @Override
    public String reply() {
        return UI.getReply("add") +"\nYou have a total of " + (MyList.printCount()) +" things in your list\n";
    }

    @Override
    public void action() throws DukeException {
        if (type.equals("todo")) {
            t = new ToDos(desc);
        } else if (type.equals("event")) {
            t = new Event(desc);
        } else {
            t = new Deadline(desc);
        }

        MyList.addItem(t);
        MyList.printCount();
    }

}

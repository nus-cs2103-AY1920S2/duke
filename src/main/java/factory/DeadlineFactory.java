package factory;

import command.DukeException;
import tasks.Deadline;
import command.Parser;

import java.time.LocalDate;

/**
 * Generates deadline objects based on user input.
 */
public class DeadlineFactory implements Factory<Deadline> {
    private Parser parser;

    public DeadlineFactory() {
        parser = new Parser();
    }

    /**
     * Creates a deadline object from user input.
     *
     * @param input input given by user
     * @return deadline object with the specified parameters
     */
    public Deadline create(String input) {
        String[] by = input.split("/");
        if (parser.checkForTags(input)) {
            String[] tags = by[1].split("/t", 2);
            return new Deadline(by[0], LocalDate.parse(by[1]), tags[1]);
        } else {
            return new Deadline(by[0], LocalDate.parse(by[1]));
        }
    }
}

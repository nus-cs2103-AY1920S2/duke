package factory;

import command.DukeException;
import tasks.Deadline;
import command.Parser;

import java.time.LocalDate;

public class DeadlineFactory implements Factory<Deadline> {
    private Parser parser;

    public DeadlineFactory() {
        parser = new Parser();
    }

    public Deadline create(String input) {
        String[] by = input.split("/");
        if (by.length < 2) {
            throw new DukeException("\t☹ OOPS!!! The date of a deadline cannot be empty.");
        }
        if (parser.checkForTags(input)) {
            String[] tags = by[1].split(" ", 2);
            return new Deadline(by[0], LocalDate.parse(tags[0]), tags[1]);
        } else {
            return new Deadline(by[0], LocalDate.parse(by[1]));
        }
    }
}

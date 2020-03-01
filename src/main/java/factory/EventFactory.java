package factory;

import logic.Parser;
import tasks.Event;

import java.time.LocalDate;

/**
 * Generates event objects based on user input.
 */
public class EventFactory implements Factory<Event> {
    private Parser parser;

    public EventFactory() {
        parser = new Parser();
    }

    /**
     * Creates a event object from user input.
     *
     * @param input input given by user
     * @return event object with the specified parameters
     */
    public Event create(String input) {
        String[] at = input.split("/");
        if (parser.checkForTags(input)) {
            String[] tags = at[1].split(" ", 2);
            return new Event(at[0], LocalDate.parse(tags[0]), tags[1]);
        } else {
            return new Event(at[0], LocalDate.parse(at[1]));
        }
    }
}

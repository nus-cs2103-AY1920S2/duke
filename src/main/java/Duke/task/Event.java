package Duke.task;

import java.time.LocalDate;

public class Event extends AbstractTask {

    /**
     * Constructor for Event class with additional info provided after date taken to be time.
     * @param taskName Name or description of the event
     * @param preposition English word string to indicate the date clearly (eg. at, by, before, between)
     * @param date Date of event
     * @param time Time of event
     */
    public Event(String taskName, String preposition, LocalDate date, String... time) {
        super(taskName, preposition, date, time);
    }

    @Override
    protected String taskType() {
        return "[E]";
    }

}
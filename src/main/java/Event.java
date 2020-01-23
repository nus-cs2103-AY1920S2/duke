/**
 * Class that represents
 * "Event" type Tasks
 */
public class Event extends Task {
    public static final String AT = "/at ";

    public Event(String td) {
        super(Event.descMaker(td.substring(Command.EVENT.word.length())),
                Event.timeMaker(td.substring(Command.EVENT.word.length())));
    }

    @Override
    public String type() {
        return "E";
    }

    /**
     * Method to format the input String
     * properly for an Event's description
     *
     * @param t The input String without
     *          the "event" word
     *
     * @return The formatted String description
     */
    private static String descMaker(String t) {
        int indexFirst = t.indexOf(Event.AT);
        return t.substring(0, indexFirst).stripTrailing();
    }

    /**
     * Method to format the input String
     * properly for an Event's time
     *
     * @param t The input String without
     *          the "event" word
     *
     * @return The formatted String time
     */
    private static String timeMaker(String t) {
        int indexLast = t.lastIndexOf(Event.AT);
        return "(at: " + t.substring(
            indexLast + Event.AT.length()
        ).stripLeading() + ")";
    }
}

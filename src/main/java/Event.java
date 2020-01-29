/**
 * Class that represents
 * "Event" type Tasks
 */
public class Event extends Task {
    public static final String AT = "/at ";
    public static final String TYPE = "E";

    /**
     * Constructor for a Event Task
     *
     * @param td String representing the lines after
     *           the event instruction
     *
     * @throws InadequateArgumentsException When String
     * td is supplied without the word "/at"
     */
    public Event(String td) throws InadequateArgumentsException {
        super(Event.descMaker(td.substring(Command.EVENT.word.length())),
                Event.timeMaker(td.substring(Command.EVENT.word.length())));
    }

    /**
     * Constructor for a Event Task
     *
     * @param taskDesc Description of the Event
     * @param taskTime Time of the Event
     */
    public Event(String taskDesc, String taskTime) {
        super(taskDesc, taskTime);
    }

    @Override
    public String type() {
        return Event.TYPE;
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
    private static String descMaker(String t) throws InadequateArgumentsException {
        int indexFirst = t.indexOf(Event.AT);
        if (indexFirst == -1) {
            throw new InadequateArgumentsException(Command.EVENT.word);
        }
        return t.substring(0, indexFirst).stripTrailing().stripLeading();
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

import java.time.LocalDate;

/**
 * Class that represents
 * "Deadline" type Tasks
 */
public class Deadline extends Task {
    public static final String BY = "/by ";
    public static final String TYPE = "D";

    /**
     * Constructor for a Deadline Task
     *
     * @param td String representing the lines after
     *           the deadline instruction
     *
     * @throws InadequateArgumentsException When String
     * td is supplied without the word "/by"
     */
    public Deadline(String td) throws InadequateArgumentsException {
        super(Deadline.descMaker(td.substring(Command.DEADLINE.word.length())),
            Deadline.timeMaker(td.substring(Command.DEADLINE.word.length())));
    }

    /**
     * Constructor for a Deadline Task
     *
     * @param taskDesc Description of the Deadline
     * @param taskTime Time of the actual Deadline
     */
    public Deadline(String taskDesc, String taskTime) {
        super(taskDesc, taskTime);
    }

    @Override
    public String type() {
        return Deadline.TYPE;
    }

    @Override
    public String timeVerb(String rawTime) {
        return "(by: " + rawTime + ")";
    }

    /**
     * Method to format the input String
     * properly for a Deadline's description
     *
     * @param t The input String without
     *          the "deadline" word
     *
     * @return The formatted String description
     */
    private static String descMaker(String t) throws InadequateArgumentsException {
        int indexFirst = t.indexOf(Deadline.BY);
        if (indexFirst == -1) {
            throw new InadequateArgumentsException(Command.DEADLINE.word);
        }
        return t.substring(0, indexFirst).stripTrailing().stripLeading();
    }

    /**
     * Method to format the input String
     * properly for a Deadline's time
     *
     * @param t The input String without
     *          the "deadline" word
     *
     * @return The formatted PrettyTime
     */
    private static PrettyTime timeMaker(String t) {
        int indexLast = t.lastIndexOf(Deadline.BY);
        return new PrettyTime(t.substring(
            indexLast + Deadline.BY.length()
        ).stripLeading());
    }
}

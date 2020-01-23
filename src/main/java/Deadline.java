/**
 * Class that represents
 * "Deadline" type Tasks
 */
public class Deadline extends Task {
    public static final String BY = "/by ";

    public Deadline(String td) {
        super(Deadline.descMaker(td.substring(Command.DEADLINE.word.length())),
            Deadline.timeMaker(td.substring(Command.DEADLINE.word.length())));
    }

    @Override
    public String type() {
        return "D";
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
    private static String descMaker(String t) {
        int indexFirst = t.indexOf(Deadline.BY);
        return t.substring(0, indexFirst).stripTrailing();
    }

    /**
     * Method to format the input String
     * properly for a Deadline's time
     *
     * @param t The input String without
     *          the "deadline" word
     *
     * @return The formatted String time
     */
    private static String timeMaker(String t) {
        int indexLast = t.lastIndexOf(Deadline.BY);
        return "(by: " + t.substring(
            indexLast + Deadline.BY.length()
        ).stripLeading() + ")";
    }
}

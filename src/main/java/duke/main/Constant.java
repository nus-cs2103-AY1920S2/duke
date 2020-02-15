package duke.main;

import java.time.format.DateTimeFormatter;


public class Constant {
    public static final String SPACE = "    ";
    public static final DateTimeFormatter FORMATTER_INPUT_DATE_TIME = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    public static final DateTimeFormatter FORMATTER_INPUT_DATE = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static final String LINE = "------------------------------------------------------------";
    public static final DateTimeFormatter FORMATTER_OUTPUT_DATE = DateTimeFormatter.ofPattern("dd MMM yyyy");
    public static final DateTimeFormatter FORMATTER_OUTPUT_DATE_TIME = DateTimeFormatter
            .ofPattern("dd MMM yyyy hh:mm a");
    public static final String CHECK_DONE = "[✓] ";
    public static final String CHECK_NOTDONE = "[✗] ";
    public static final int MAX_INPUT_SIZE = 2;
}
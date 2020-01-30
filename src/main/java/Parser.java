import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Parser {
    static Pattern pDeadline = Pattern.compile("deadline (.+) /by (.+)");
    static Pattern pEvent = Pattern.compile("event (.+) /at (.+)");
    static Pattern pTodo = Pattern.compile("todo (.+)");
    static Pattern pDone = Pattern.compile("done (\\d+)");
    static Pattern pDelete = Pattern.compile("delete (\\d+)");
    static Pattern pEmptyCommand = Pattern.compile("(todo|event|deadline)\\s*$");
    static Pattern pList = Pattern.compile("list\\s*$");
    static Pattern pSave = Pattern.compile("save\\s*$");

    public static LocalDateTime parseDateTime(String s) {
        Pattern patternDateTime = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s+\\d{4}");
        Pattern patternDate = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

        if (patternDateTime.matcher(s).find()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(s, formatter);
        } else if (patternDate.matcher(s).find()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            return LocalDate.parse(s, formatter).atStartOfDay();
        }
        throw new UnsupportedOperationException("Supplied string is not of proper format.");
    }
}

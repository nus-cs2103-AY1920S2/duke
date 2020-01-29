import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exceptions.WrongDateTimeFormatException;

public class Date {
    private LocalDateTime date;
    private String outputFormat = "MMM d yyyy hh:mma";
    
    final String INPUT_FORMAT = "yyyy-MM-dd HHmm";

    public Date(String dateStr) throws WrongDateTimeFormatException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_FORMAT);
            this.date = LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException(String.format("Accepted date time format: %s", INPUT_FORMAT));
        }
    }

    private Date(LocalDateTime date, String outputFormat) {
        this.date = date;
        this.outputFormat = outputFormat;
    }

    public Date withFormat(String outputFormat) {
        return new Date(this.date, this.outputFormat);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.outputFormat);
        return this.date.format(formatter);
    }
}
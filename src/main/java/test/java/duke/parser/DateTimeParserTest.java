package test.java.duke.parser;

import duke.parser.DateTimeParser;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeParserTest {

    @Test
    void parseDate_20200201_success() throws ParseException {
        assertEquals(new DateTimeParser().parseDate("2020-02-01").getTime(), new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-01").getTime());
    }

    @Test
    void parseTime() throws ParseException {
        assertEquals(new DateTimeParser().parseTime("18:00").getTime(), new SimpleDateFormat("HH:mm").parse("18:00").getTime());
    }
}
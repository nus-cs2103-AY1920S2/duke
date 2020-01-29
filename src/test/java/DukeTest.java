import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void test1() {
        LocalDate date = LocalDate.parse("2020-02-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Task deadLine = new Deadline(" return book ", date, "1800");
        String expectedFormat = "[D][X] return book (by: Feb 2 2020 1800)";
        assertEquals(deadLine.toString(), expectedFormat);
    }

    @Test
    public void test2() {
        Task toDo = new ToDo(" read book");
        String expectedFormat = "[T][X] read book";
        assertEquals(toDo.toString(), expectedFormat);
    }

    @Test
    public void test3() {
        LocalDate date = LocalDate.parse("2020-02-05", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Task event = new Event(" project meeting ", date, "2-4pm");
        String expectedFormat = "[E][X] project meeting (at: Feb 5 2020 2-4pm)";
        assertEquals(event.toString(), expectedFormat);
    }

}
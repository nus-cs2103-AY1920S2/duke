import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    public static void main(String[] args) {
        Deadline deadline = new Deadline("return book", LocalDate.parse("2020-02-02"));
        assertEquals(deadline.toString().trim(),"[D][\u2718] return book (by: Feb 2 2020)");
        assertEquals(deadline.tobeReplaced().trim(),"[D][\u2718] return book (by: 2020-02-02)");
    }
}

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
class DeadlineTest {
    public void test() {
        Deadline deadline = new Deadline("return book", LocalDate.parse("2020-02-02"));
        assertEquals(deadline.toString().trim(),"[D][✗] return book (by: Feb 2 2020)");
        assertEquals(deadline.replace().trim(),"[D][✗] return book (by: 2020-02-02)");
    }
}

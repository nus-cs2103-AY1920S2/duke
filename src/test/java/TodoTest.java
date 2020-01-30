import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void TodoTest(){
        Todo task = new Todo("finish CS2103T IP");
        assertEquals("[T][X] finish CS2103T IP", task.toString());
    }
}
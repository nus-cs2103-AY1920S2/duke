import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskTest{
    @Test
    public void check(){
        assertEquals("T | 0 | 123", new Task(" 123").readyToSave());
    }
}
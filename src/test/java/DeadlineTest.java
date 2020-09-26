import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest{
    @Test
    public void check(){
        assertEquals("D | 0 | 123 | abc", new Deadline(" 123"," (by: abc)").readyToSave());
    }
}
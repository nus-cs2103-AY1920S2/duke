import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void saveFileTest() {
        assertEquals ("T|0||Play basketball", new Todo ("Play basketball").saveFile());
    }

    @Test
    public void stringConversionTest() {
        assertEquals ("[T][Not Done] Finish Homework",new Todo ("Finish Homework").toString());
    }

}
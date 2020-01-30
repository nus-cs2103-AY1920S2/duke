import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void getStatusTest() {
        assertEquals ("Not Done", new Task ("Play basketball").getStatus());
    }

    @Test
    public void stringConversionTest() {
        assertEquals ("[Not Done] Practice music", new Task ("Practice music").toString());
    }

}
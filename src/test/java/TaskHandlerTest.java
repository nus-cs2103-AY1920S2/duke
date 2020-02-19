import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskHandlerTest {
    @Test
    public void taskHandlerTest() {
        try {
            Task task = TaskHandler.taskHandler("todo", "something");
            assertEquals("[T]" + "[" + (char) 10060 + "] something", task.toString());
        } catch(InvalidInputException e){
        
        }
    }
}

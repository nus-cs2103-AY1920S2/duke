package duke.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TaskTest { 
	@Test
	public void testParsing() {
		Task task = new Deadline("deadline doing something /by 1999-12-30");
		assertEquals("doing something  ", task.description, "must parse description correctly");
	}
}
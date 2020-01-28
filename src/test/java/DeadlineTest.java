import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
  @Test
  public void testDoneStatus() {
    Deadline deadlineTest = new Deadline("test", "2020-01-01");
    assertEquals(false, deadlineTest.getDoneStatus());
  }

  @Test
  public void testTaskName() {
    Deadline deadlineTest = new Deadline("test", "2020-01-01");
    assertEquals("test", deadlineTest.getTaskName());
  }
}

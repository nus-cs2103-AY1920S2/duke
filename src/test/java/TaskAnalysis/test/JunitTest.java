package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class JunitTest {
    @Test
    public void initiateTaskTest(){ test.Task sample = new test.Task("Code java");
        String expectedDescription = "Code java";
        String actualDescription = sample.getDescription();
        String expectedStatus = "No";
        String actualStatus = sample.checkDone();
        assertEquals(actualDescription, expectedDescription);
        assertEquals(actualStatus, expectedStatus);
    }
    @Test
    public void initiateEventTest(){ test.Events sample = new test.Events("Java for Life","2020-01-29");
        String expectedDate = "29 JANUARY 2020";
        String actualDate = sample.getTime();
        assertEquals(expectedDate,actualDate);
    }
    @Test
    public void initiateDeadlinesTest(){ test.Deadlines sample = new test.Deadlines("Java for Life","2020-01-30");
        String expectedDate = "30 JANUARY 2020";
        String actualDate = sample.getTime();
        assertEquals(expectedDate,actualDate);
    }

}

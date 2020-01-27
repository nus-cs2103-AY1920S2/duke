import core.Model;
import org.junit.jupiter.api.Test;
import task.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {

    @Test
    public void testGetSize(){
        Model model=new Model();
        model.load(new ArrayList<Task>());
        assertEquals(0,model.getSize());
    }
}

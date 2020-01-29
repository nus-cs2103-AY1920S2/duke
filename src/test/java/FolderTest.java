package packagedirectory.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FolderTest {
    @Test
    public void testStringConversion() {
        Tasks msg1 = new Tasks(new Message("read book"));
        Tasks msg2 = new Tasks(new Message("read story"));
        Tasks msg3 = new Tasks(new Message("read helloworld"));
        Folder fd = new Folder();
        fd.add(msg1);
        fd.add(msg2);
        fd.add(msg3);
        String result = "=[x]=read book\n=[x]=read story\n=[x]=read helloworld\n";
        assertEquals(result, fd.getText());
    }
}

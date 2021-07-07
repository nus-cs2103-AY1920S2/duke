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

    /*@Test
    public void testFindConversion() {
        Tasks msg1 = new ToDos(new Message("read book"));
        Tasks msg2 = new ToDos(new Message("read dog"));
        Tasks msg3 = new ToDos(new Message("read cat"));
        Tasks msg4 = new ToDos(new Message("play book "));
        Folder fd = new Folder();
        fd.add(msg1);
        fd.add(msg2);
        fd.add(msg3);
        fd.add(msg4);
        String result = "[T]=[x]=ressad book\n[T]=[x]=play book\n";
        assertEquals(result, fd.find("book"));
    }*/
}


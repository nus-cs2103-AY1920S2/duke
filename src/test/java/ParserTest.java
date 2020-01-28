import duke.Parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {

 @Test

    public void test() throws Exception {

     Parser parser = new Parser();
     String[] output = parser.parseUserInput("delete 3");
     String[] expected = new String[2];

     expected[0] = "delete";
     expected[1] = "3";

    for(int i = 0; i < 2; i++) {
     assertEquals(expected[i], output[i]);
    }
 }


}

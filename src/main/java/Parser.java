/*
Represents a Parser object that parses user info for Duke
 */
public class Parser {

    /*
    Parses given text upto limit and returns a String array
     */
    public String[] parse(String text, int limit) {
        return text.split(" ",  limit);
    }
}

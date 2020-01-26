
public class Parser {

    public String parse(String getInput) {
        if (getInput.equals("todo") || getInput.equals("deadline") || getInput.equals("event")) {
            return "add";
        } else {
            return getInput;
        }

    }

}

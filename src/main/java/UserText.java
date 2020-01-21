import java.util.ArrayList;
import java.util.List;

public class UserText {
    private List<String> userInput;

    public UserText() {
        userInput = new ArrayList<>();
    }

    public List<String> addInput(String s) {
        this.userInput.add(s);
        return this.userInput;
    }

    public void printInputs() {
        int count = 1;
        for (String s : userInput) {
            System.out.println(count + ". " + s);
            count++;
        }
    }
}

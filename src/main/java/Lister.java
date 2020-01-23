import java.util.ArrayList;
import java.util.Iterator;

public class Lister {
    private ArrayList<String> store;

    public Lister() {
        store = new ArrayList<>();
    }

    public void record(String command) {
        if (command.equals("list")) {
            Iterator<String> iter = store.iterator();
            int counter = 1;
            while (iter.hasNext()) {
                System.out.println(counter + ". " + iter.next());
                counter++;
            }
        } else {
            store.add(command);
            System.out.println("added: " + command);
        }
    }

}

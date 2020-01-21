import java.util.ArrayList;
import java.util.List;

public class Database {
    static List<String> records;

    /**
     * Constructor of database
     */
    public Database() {
        records = new ArrayList<>();
    }

    /**
     * Add data into database
     * @param data data required to add
     */
    public void addData(String data) {
        records.add(data);
    }

    /**
     * Get the listing in database
     * @return listing in database
     */
    public List<String> getListing() {
        return records;
    }
}

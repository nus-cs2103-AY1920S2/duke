import java.util.Comparator;

public class DateCompareAsc implements Comparator<Item> {
    public int compare(Item i2, Item i1) {
        return i1.getDate().compareTo(i2.getDate());
    }
}

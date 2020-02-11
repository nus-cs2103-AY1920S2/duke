import java.util.Comparator;

public class DateCompareDes implements Comparator<Item> {
    public int compare(Item i1, Item i2) {
        return i1.getDate().compareTo(i2.getDate());
    }
}

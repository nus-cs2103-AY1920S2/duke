import java.util.ArrayList;
import java.util.List;

public class Storage<T> {
	private List<T> containers;

	public Storage() {
		containers = new ArrayList<>();
	}

	public void addAction(T action) {
		containers.add(action);
	}

	List<T> getList() {
		return this.containers;
	} 
}
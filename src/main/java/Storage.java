import java.util.ArrayList;
import java.util.List;

public class Storage<T extends Task> {
	private List<T> containers;

	public Storage() {
		containers = new ArrayList<>();
	}

	public void addAction(T action) {
		containers.add(action);
	}

	public List<T> getList() {
		return this.containers;
	} 

	public void markAsDone(List<Integer> needy) {
		for (Integer index: needy) {
			this.containers.get(index).markAsDone();
		}
	}
}
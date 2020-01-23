import java.util.ArrayList;
import java.util.List;
import task.*;

public class Storage<T extends Task> {
	private List<T> containers;

	public Storage() {
		containers = new ArrayList<>();
	}

	public void addAction(T action) {
		containers.add(action);
	}

	public void deleteAction(int position) {
		containers.remove(position);
	}

	public List<T> getList() {
		return this.containers;
	} 

	public T getTask(int index) {
		return this.containers.get(index);
	}

	public int getNum() {
		return containers.size();
	}

	public void markAsDone(List<Integer> needy) {
		for (Integer index: needy) {
			this.containers.get(index).markAsDone();
		}
	}
}
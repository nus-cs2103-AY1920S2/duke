package com.duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import com.duke.task.Task;
import com.duke.task.Event;
import com.duke.task.ToDo;
import com.duke.task.Deadline;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/**
 * Store list of current tasks.
 */

public class TaskList {
	private List<Task> containers;

	public TaskList() {
		this.containers = new ArrayList<Task> ();
	}

	public TaskList(List<Task> containers) {
		this.containers = containers;
	}

	public void addAction(Task action) {
		containers.add(action);
	}

	public void deleteAction(int position) {
		containers.remove(position);
	}

	public List<Task> getList() {
		return this.containers;
	} 

	public Task getTask(int index) {
		assert(index >= 0);
		return this.containers.get(index);
	}

	public int getNum() {
		return containers.size();
	}

	public void markAsDone(List<Integer> needy) {
		for (Integer index: needy) {
			assert(index >= 0);
			this.containers.get(index).markAsDone();
		}
	}

	public void deleteTask(List<Integer> needy) {
		Collections.sort(needy, Collections.reverseOrder());
		for (Integer index: needy) {
			System.out.println(index);
			System.out.println(containers.size());
			try {
				this.deleteAction(index.intValue());
			} catch (Exception e) {
				System.out.println("Out of index");
			} 
		}
	}

	public List<Task> getSubset(List<Integer> needy) {
		List result = new ArrayList<Task>();
		for (Integer index: needy) {
			result.add(this.containers.get(index));
		}
		return result;
	}
}
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

	/**
	 * [TaskList get the contained containers]
	 * @return [description]
	 */
	public TaskList() {
		this.containers = new ArrayList<Task> ();
	}

	/**
	 * [TaskList constructors]
	 * @param  containers [a list of tasks]
	 * @return            [a new tasklist]
	 */
	public TaskList(List<Task> containers) {
		this.containers = containers;
	}

	/**
	 * [addAction description]
	 * @param action [description]
	 */
	public void addAction(Task action) {
		containers.add(action);
	}

	/**
	 * [deleteAction delete a tasks]
	 * @param position [position of the task in the list]
	 */
	public void deleteAction(int position) {
		assert(position >= 0);
		containers.remove(position);
	}

	/**
	 * [getList get the contained containers]
	 * @return [returns a list of tasks]
	 */
	public List<Task> getList() {
		return this.containers;
	} 

	/**
	 * [getTask get a specific task]
	 * @param  index [the index of the task in the list]
	 * @return       [a task]
	 */
	public Task getTask(int index) {
		assert(index >= 0);
		return this.containers.get(index);
	}

	/**
	 * [getNum get the current number of tasks in the list]
	 * @return [an integer specifying it]
	 */
	public int getNum() {
		return containers.size();
	}

	/**
	 * [markAsDone marks a list of tasks as done]
	 * @param needy [list of indexes]
	 */
	public void markAsDone(List<Integer> needy) {
		for (Integer index: needy) {
			this.containers.get(index).markAsDone();
		}
	}

	/**
	 * [deleteTask deletes a list of tasks]
	 * @param needy [a list of indexes]
	 */
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

	/**
	 * [getSubset get subset of tasks]
	 * @param  needy [list of indexes]
	 * @return       [a list of tasks as the needed subset]
	 */
	public List<Task> getSubset(List<Integer> needy) {
		List result = new ArrayList<Task>();
		for (Integer index: needy) {
			result.add(this.containers.get(index));
		}
		return result;
	}
}
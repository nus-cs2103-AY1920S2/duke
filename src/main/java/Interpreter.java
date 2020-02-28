package com.duke;

import java.util.List;
import com.duke.task.Task;
import com.duke.task.Event;
import com.duke.task.Deadline;
import com.duke.task.ToDo;
import com.duke.dukeException.DukeException;
import com.duke.DukeResponse;
import java.util.Collections;

/**
 * 
 * Get responses 
 */
public class Interpreter {
	static private final String separation = "_________________________________________________";
	static private final String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
	
	/**
	 * [printGreeting description]
	 * @return [description]
	 */
	static public DukeResponse printGreeting() {
		return new DukeResponse("Hello from\n" + logo);
	}

	/**
	 * [printUsage of Duke]
	 * @return [description]
	 */
	static public DukeResponse printUsage() {
		StringBuilder sb = new StringBuilder();
		sb.append("Usage of DUKE: \n")
		  .append("1. todo ... ")  
		  .append("\n")
		  .append("2. deadline ... /by yyyy-mm-dd")
		  .append("\n")
		  .append("3. event ... /at .....")
		  .append("\n");
		return new DukeResponse(sb.toString());
	}

	/**
	 * [printMessage of a given message]
	 * @param  message [a String]
	 * @return         [description]
	 */
	static public DukeResponse printMessage(String message) {
		return new DukeResponse(message);
	}

	static public DukeResponse printList(List<Task> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("List of items: ").append("\n");
		for (int i = 0; i < list.size(); i++) {
			sb.append(Integer.toString(i + 1))
			  .append(". ")
			  .append(list.get(i).toString())
			  .append("\n");
		}
		return new DukeResponse(sb.toString());
	}

	/**
	 * [printAdd prints adding commands]
	 * @param  task                 [a specific task]
	 * @param  numberOfCurrentTasks [number of current tasks in the list]
	 * @return                      [description]
	 */
	static public DukeResponse printAdd(Task task, int numberOfCurrentTasks) {
		StringBuilder sb = new StringBuilder();
		sb.append("Got it. I've added this task: ").append("\n");
		sb.append(task.toString())
		  .append("Now you have ")
		  .append(Integer.toString(numberOfCurrentTasks))
		  .append(" in the list!")
		  .append("\n");
		return new DukeResponse(sb.toString());
	}

	/**
	 * [printDelete prints deleteting commands]
	 * @param  task                 [a specific task]
	 * @param  numberOfCurrentTasks [number of current tasks in the list]
	 * @return                      [description]
	 */
	static public DukeResponse printDelete(Task task, int numberOfCurrentTasks) {
		StringBuilder sb = new StringBuilder();
		sb.append("Noted. I've removed this task:").append("\n");
		sb.append(task.toString()).append("\n");
		sb.append("Now you have ")
		  .append(Integer.toString(numberOfCurrentTasks))
		  .append(" in the list!")
		  .append("\n");
		return new DukeResponse(sb.toString());
	}

	/**
	 * [printMultipleDelete]
	 * @param  tasks                [a list of tasks]
	 * @param  numberOfCurrentTasks [number of current tasks in the list]
	 * @return                      [description]
	 */
	static public DukeResponse printMultipleDelete(List<Task> tasks, int numberOfCurrentTasks) {
		StringBuilder sb = new StringBuilder();
		sb.append("Noted. I've removed these tasks:").append("\n");
		for (int i = 0; i < tasks.size(); i++) {
			sb.append("   ").append(tasks.get(i).toString()).append("\n");
		}
		sb.append("Now you have ")
		  .append(Integer.toString(numberOfCurrentTasks))
		  .append(" in the list!")
		  .append("\n");
		return new DukeResponse(sb.toString());
	}

	/**
	 * [printDoneList print done list]
	 * @param  list [description]
	 * @return      [description]
	 */
	static public DukeResponse printDoneList(List<Task> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("Nice! I've marked this task as done:").append("\n");
		for (int i = 0; i < list.size(); i++) {
			sb.append("   ").append(list.get(i).toString()).append("\n");
		}
		return new DukeResponse(sb.toString());
	}

	/**
	 * [printException print the exception throwed by Duke]
	 * @param  e [an exception]
	 * @return   [a duke response]
	 */
	static public DukeResponse printException(DukeException e) {
		return new DukeResponse(e.getMessage());
	}

	/**
	 * [printFind print finding command]
	 * @param  tasks   [a list of tasks]
	 * @param  indexes [list of pringing indexes]
	 * @return         [description]
	 */
	static public DukeResponse printFind(List<Task> tasks, List<Integer> indexes) {
		StringBuilder sb = new StringBuilder();
		sb.append("Here are the matching tasks in your list: \n");
		for (Integer idx: indexes) {
			sb.append(Integer.toString(idx + 1))
			  .append(". ")
			  .append(tasks.get(idx))
			  .append("\n");
		}
		return new DukeResponse(sb.toString());
	}
}
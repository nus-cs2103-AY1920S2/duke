package com.duke;

import java.util.List;
import com.duke.task.Task;
import com.duke.task.Event;
import com.duke.task.Deadline;
import com.duke.task.ToDo;
import com.duke.dukeException.DukeException;
import com.duke.DukeResponse;

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
	
	static public DukeResponse printGreeting() {
		return new DukeResponse("Hello from\n" + logo);
	}

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

	static public DukeResponse printDoneList(List<Task> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("Nice! I've marked this task as done:").append("\n");
		for (int i = 0; i < list.size(); i++) {
			sb.append("   ").append(list.get(i).toString()).append("\n");
		}
		return new DukeResponse(sb.toString());
	}


	static public DukeResponse printException(DukeException e) {
		return new DukeResponse(e.getMessage());
	}

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
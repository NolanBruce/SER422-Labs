package resources;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONTokener;

import resources.Task;

public class ToDoList {
	private ArrayList<Task> Tasks;
	
	/**
	 * Generic Constructor that creates an empty ToDoList
	 */
	public ToDoList() {
		Tasks = new ArrayList<Task>();
	}
	
	/**
	 * Constructor that initializes ToDoList with a Task
	 * @param task
	 */
	public ToDoList(Task task) {
		Tasks = new ArrayList<Task>();
		this.addTask(task);
	}

	/**
	 * Constructor that creates a ToDoList from a json file 
	 * @param jsonFile
	 */
	public ToDoList(String jsonFile) {
		try {
			FileInputStream in = new FileInputStream(jsonFile);
			JSONObject obj = new JSONObject(new JSONTokener(in));
			String[] tasks = JSONObject.getNames(obj);
			Tasks = new ArrayList<Task>();
			
			for(int i=0; i<tasks.length; i++) {
				Task task = new Task(obj.getJSONObject(tasks[i]));
				this.addTask(task);
			}
			
			in.close();
		} catch (Exception e) {
			System.out.println("Exception importing from json: " + e.getMessage());
		}
	}
	
	/**
	 *Adds task to Task List
	 * @param task	Task to be added	
	 */
	public void addTask(Task task) {
		Task temp;
		//check if task already exists with name
		for(int i=0; i<getSize(); i++) {
			temp = Tasks.get(i);
			//if so, replace task
			if (temp.getName().equals(task.getName())) {
				Tasks.remove(i);
				break;
			}
		}
		Tasks.add(task);
	}
	
	public boolean removeTask(String name) {
		for(int i=0; i<this.getSize(); i++) {
			Task temp = this.get(i);
			if(temp.getName().toLowerCase().equals(name)) {
				this.removeTask(i);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Removes Task at index i
	 * @param i	index of Task to be removed
	 */
	public void removeTask(int i) {
		Tasks.remove(i);
	}
	
	/**
	 * Gets size of Task List
	 * @return	Task List size as integer
	 */
	public int getSize() {
		return Tasks.size();
	}
	
	/**
	 * Returns Task by index
	 * @param i	index of task
	 * @return	task at index i
	 */
	public Task get(int i) {
		return Tasks.get(i);
	}
	
	/**
	 * Saves ToDoList as a JSONOjbect in a json file
	 * @param jsonFile	filepath to save at
	 */
	public void toJsonFile(String jsonFile) {
		try {
			String temp;
			Task task;
			JSONObject jsonList = new JSONObject();
			
			for(int i=0; i<getSize(); i++) {
				task = get(i);
				JSONObject obj = task.toJson();
				jsonList.put(task.getName(), obj);
			}
			
			temp = jsonList.toString(2);
			PrintWriter out = new PrintWriter(jsonFile);
			out.println(temp);
			out.close();
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

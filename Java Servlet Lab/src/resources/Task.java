package resources;

import org.json.JSONObject;

import servlets.TaskList;

public class Task {
	private String name;
	private String desc;
	private String days;
	private String dur;
	private String ex;
	
	/**
	 * Generic constructor for creating task from strings
	 * @param name	name
	 * @param desc	description
	 * @param days	days for task to be completed
	 * @param dur	duration
	 * @param ex		executor
	 */
	public Task(String name, String desc, String days, String dur, String ex) {
		this.name = name;
		this.desc = desc;
		this.days = days;
		this.dur = dur;
		this.ex = ex;
	}
	
	/**
	 * Constructor for creating Task from JSONObject
	 * @param obj	JSONObject to generate Task from
	 */
	public Task(JSONObject obj) {
		this.name = obj.getString("name");
		this.desc = obj.getString("desc");
		this.days = obj.getString("days");
		this.dur = obj.getString("dur");
		this.ex = obj.getString("ex");
	}

	/**
	 * @return	name of Task
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return	description of task
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return	days on which task occurs
	 */
	public String getDays() {
		return days;
	}

	/**
	 * @return	duration of task
	 */
	public String getDur() {
		return dur;
	}

	/**
	 * @return	executor of task
	 */
	public String getEx() {
		return ex;
	}
	
	/**
	 * Converts Task to JSONOjbect
	 * @return	Task as JSONObject
	 */
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		
		obj.put("name", this.name);
		obj.put("desc", this.desc);
		obj.put("days", this.days);
		obj.put("dur", this.dur);
		obj.put("ex", this.ex);
		
		return obj;
	}
	
	
}

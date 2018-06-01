package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resources.Task;
import resources.ToDoList;

@SuppressWarnings("serial")
public class TaskList extends HttpServlet {
	private ToDoList ToDo;
	private Task task;
	private String jsonFile;
	private String fullPath;
	
	
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		jsonFile = sc.getInitParameter("jsonList");
		ServletContext context = getServletContext();
		System.out.println (context.getRealPath(jsonFile));
        fullPath = context.getRealPath(jsonFile);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		
		StringBuilder sb = new StringBuilder("");
		String contentType = "text/plain"; //by default
		
		//process headers
		String userAgent = req.getHeader("User-Agent");
		String acceptsHeader = req.getHeader("Accept");
		String[] acceptsFormats = acceptsHeader.split(",");
		for (String s : acceptsFormats) {
			if(s.equals("text/html")) {
				sb.append("<HTML><TITLE>Tasks!</TITLE></HTML>\n<BODY");
				if (userAgent.toLowerCase().contains("applewebkit")) {
					sb.append(" style=\"background-color:grey;\"");
				}
				sb.append(">\n");
				contentType = "text/html";
				break;
			}
		}
		
		//process req parameters
		String name = req.getParameter("name");
		String desc = req.getParameter("description");
		String days = "";
		if(req.getParameter("Mon") != null) {
			days = days = "1";
		}
		if(req.getParameter("Tue") != null) {
			days = days.concat("2");
		}
		if(req.getParameter("Wed") != null) {
			days = days.concat("3");
		}
		if(req.getParameter("Thu") != null) {
			days = days.concat("4");
		}
		if(req.getParameter("Fri") != null) {
			days = days.concat("5");
		}
		String dur = req.getParameter("duration");
		String ex = req.getParameter("executor");
		task = new Task(name, desc, days, dur, ex);
		try {
			ToDo = new ToDoList(fullPath);
		} catch (Exception e) {
			// if file doesn't exist, create blank ToDoList
			ToDo = new ToDoList();
		}
		ToDo.addTask(task);
		
		//perform processing and assemble res payload
		try {
			if(task != null) {
				ToDo.toJsonFile("webapps/resources/lab1data.json");
				if(contentType.equals("text/html")) {
					sb.append("<button onclick=\"window.location.href='./index.html'\">Back</button>");
					sb.append("<BR>Added task " + name + " to task list");
					sb.append("<BR>Number of task in Task List: " + ToDo.getSize());
					sb.append("\n<script type=\"text/javascript\">");
					sb.append("\nconsole.log(\"User-Agent:  " + userAgent + "\")");
					sb.append("\nconsole.log(\"List size = " + ToDo.getSize() + "\")");
					sb.append("\nconsole.log(\"Added task " + name + "\")");
					sb.append("\n</script>");
				} else if (contentType.equals("text/plain")) {
					sb.append("Added task " + name + " to task list");
					sb.append("\nNumber of tasks in Task List: " + ToDo.getSize());
				}
			} else {
				if(contentType.equals("text/html")) {
					sb.append("<button onclick=\"window.location.href='./index.html'\">Back</button>");
					sb.append("<BR> FAILURE: TASK NOT RECEIVED!");
					sb.append("\n<script type=\"text/javascript\">");
					sb.append("\nconsole.log(\"Failed to add task\"");
					sb.append("\n</script>");
				} else if (contentType.equals("text/plain")) {
					sb.append("FAILURE: TASK NOT RECEIVED!");
				}
			}
			task = null;
		} catch (Exception e) {
			if(contentType.equals("text/html")) {
				sb.append("<BR><BR>ERROR WRITING TO FILE");
			} else if(contentType.equals("text/plain")) {
				sb.append("/n/nERROR WRITING TO FILE");
			}
		}
		
		//assign res headers
		res.setContentType(contentType);
		res.setStatus(res.SC_OK);
		
		//write out res
		if(contentType.equals("text/html")) {
			sb.append("\n</BODY>\n</HTML>");
		}
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
				
				StringBuilder sb = new StringBuilder("");
				String contentType = "text/plain"; //by default
				
				//process headers
				String userAgent = req.getHeader("User-Agent");
				String acceptsHeader = req.getHeader("Accept");
				String[] acceptsFormats = acceptsHeader.split(",");
				for (String s : acceptsFormats) {
					if(s.equals("text/html")) {
						sb.append("<HTML><TITLE>Tasks!</TITLE></HTML>\n<BODY");
						if (userAgent.toLowerCase().contains("applewebkit")) {
							sb.append(" style=\"background-color:grey;\"");
						}
						sb.append(">\n");
						contentType = "text/html";
						break;
					}
				}
				
				//process req parameters
				String desc = req.getParameter("description");
				String days = "";
				if(req.getParameter("Mon") != null) {
					days = days = "1";
				}
				if(req.getParameter("Tue") != null) {
					days = days.concat("2");
				}
				if(req.getParameter("Wed") != null) {
					days = days.concat("3");
				}
				if(req.getParameter("Thu") != null) {
					days = days.concat("4");
				}
				if(req.getParameter("Fri") != null) {
					days = days.concat("5");
				}
				
				//perform processing and assemble res payload
				try {
					try {
						ToDo = new ToDoList(fullPath);
					} catch (Exception e) {
						//if file doesn't exist, create new file
						ToDo = new ToDoList();
					}
	 				if(contentType.equals("text/html")) {
	 					//add back button and log some data in console.
						sb.append("<button onclick=\"window.location.href='./index.html'\">Back</button>");
						sb.append("\n<script type=\"text/javascript\">");
						sb.append("\nconsole.log(\"User-Agent:  " + userAgent + "\")");
						sb.append("\nconsole.log(\"Searching for task with description:  " + desc + "\")");
						sb.append("\n</script>");
						//
						boolean flag = false;
						for(int i =0; i<ToDo.getSize(); i++) {
							Task temp = ToDo.get(i);
							if(temp.getDesc().toLowerCase().contains(desc.toLowerCase())) {
								if(temp.getDays().equals(days)) {
									if(!flag) {
										sb.append("<BR>Found task(s) matching description:");
										flag = true;
									}
									sb.append("<BR>" + ToDo.get(i).getName());
								}
							}
						}
						if(!flag) {
							sb.append("<BR>No tasks found matching description");
						}
					} else if(contentType.equals("text/plain")) {
						boolean flag = false;
						for(int i =0; i<ToDo.getSize(); i++) {
							Task temp = ToDo.get(i);
							if(temp.getDesc().toLowerCase().contains(desc.toLowerCase())) {
								if(temp.getDays().equals(days)) {
									if(!flag) {
										sb.append("Found task(s) matching description:");
										flag = true;
									}
									sb.append("\n" + ToDo.get(i).getName());
								}
							}
						}
						if(!flag) {
							sb.append("\nNo tasks found matching description");
						}
					}
				} catch (Exception e) {
					if(contentType.equals("text/html")) {
						sb.append("<BR><BR>ERROR READING FROM FILE");
					} else if(contentType.equals("text/plain")) {
						sb.append("/n/nERROR READING FROM FILE");
					}
				}
				
				//assign res headers
				res.setContentType(contentType);
				res.setStatus(res.SC_OK);
				
				//write out res
				if(contentType.equals("text/html")) {
					sb.append("\n</BODY>\n</HTML>");
				}
				PrintWriter out = res.getWriter();
				out.print(sb.toString());
			}
}

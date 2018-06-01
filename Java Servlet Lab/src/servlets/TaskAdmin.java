package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resources.Task;
import resources.ToDoList;

@SuppressWarnings("serial")
public class TaskAdmin extends HttpServlet {
	private ToDoList ToDo;
	private Task task;
	private String jsonFile;
	
	
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		jsonFile = sc.getInitParameter("jsonList");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
				
				StringBuilder sb = new StringBuilder("");
				String contentType = "text/plain"; //by default
				
				//process headers
				//nothing to process				
				
				//process req parameters
				//nothing to process
				
				//perform processing and assemble res payload
				try {
					try {
						ToDo = new ToDoList("webapps/resources/lab1data.json");
					} catch (Exception e) {
						//if file doesn't exist, create new file
						ToDo = new ToDoList();
					}
					sb.append("Number of Tasks in List: " + ToDo.getSize());					
				} catch (Exception e) {
					sb.append("/n/n Error retrieving Task List");
				}
				
				//assign res headers
				res.setContentType(contentType);
				res.setStatus(res.SC_OK);
				
				//write out res
				PrintWriter out = res.getWriter();
				out.print(sb.toString());
			}
	public void doDelete(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		StringBuilder sb = new StringBuilder("");
		String contentType = "text/plain"; //by default
		
		//process headers
		//nothing to process				
		
		//process req parameters
		String name = req.getParameter("name");
		
		//perform processing and assemble payload
		try {
			ToDo = new ToDoList("webapps/resources/lab1data.json");
		} catch (Exception e) {
			//if file doesn't exist, create new file
			ToDo = new ToDoList();
		}
		boolean flag = ToDo.removeTask(name);
		if(flag) {
			sb.append("Removed task: " + name);
			sb.append("Number of Tasks in List: " + ToDo.getSize());	
		}
		
		//assign res headers
		res.setContentType(contentType);
		if(flag) {
			res.setStatus(res.SC_OK);
		} else {
			res.setStatus(res.SC_NOT_FOUND);
		}
		
		//write out res
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
	}
}

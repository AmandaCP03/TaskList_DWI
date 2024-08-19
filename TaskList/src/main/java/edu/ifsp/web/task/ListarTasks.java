package edu.ifsp.web.task;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.ifsp.modelo.Task;
import edu.ifsp.persistencia.TaskDAO;
import edu.ifsp.web.Command;
import edu.ifsp.web.Flash;
import edu.ifsp.web.templates.Template;

public class ListarTasks implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		TaskDAO dao = new TaskDAO();
		List<Task> tasks = dao.findAll();
		request.setAttribute("tasks", tasks);
		//Flash.move(request, "listar");
		Template.render("index", request, response);	
	}

}

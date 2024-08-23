<<<<<<< HEAD
package edu.ifsp.web.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.modelo.Task;
import edu.ifsp.persistencia.TaskDAO;
import edu.ifsp.web.Command;
import edu.ifsp.web.templates.Template;

public class EditarTask implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Task task = null;

		if (request.getParameter("id") == null) {
			task = new Task();			
			Template.render("cadastrar", request, response);
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			TaskDAO dao = new TaskDAO();
			task = dao.findById(id);
		
			request.setAttribute("task", task);
			Template.render("editar", request, response);
		}

	}

}
=======
package edu.ifsp.web.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.modelo.Task;
import edu.ifsp.persistencia.TaskDAO;
import edu.ifsp.web.Command;
import edu.ifsp.web.templates.Template;

public class EditarTask implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Task task = null;

		if (request.getParameter("id") == null) {
			task = new Task();			
			Template.render("cadastrar", request, response);
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			TaskDAO dao = new TaskDAO();
			task = dao.findById(id);
		
			request.setAttribute("task", task);
			Template.render("editar", request, response);
		}

	}

}
>>>>>>> 131c7e36f7c108e69118683abc5759de431948dd

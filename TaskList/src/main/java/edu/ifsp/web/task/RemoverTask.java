package edu.ifsp.web.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.modelo.Task;
import edu.ifsp.persistencia.TaskDAO;
import edu.ifsp.web.Command;
import edu.ifsp.web.Flash;
import edu.ifsp.web.templates.Template;

public class RemoverTask implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TaskDAO dao = new TaskDAO();
		Task task = new Task();
		
        if(request.getParameter("id") == null || request.getParameter("id").isBlank()) {
        	Template.render("listar", request, response);
		}else {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.delete(id);
			//request.setAttribute("tarefaExcluida", "Tarefa excluída com sucesso!");
			Flash.setAttribute(request, "listar", "tarefaExcluida", "Tarefa excluída com sucesso!");
		}
		
        
		response.sendRedirect(request.getContextPath() + "/tasks/listar");
	}

}

package edu.ifsp.web.task;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Task;
import edu.ifsp.modelo.Usuario;
import edu.ifsp.persistencia.TaskDAO;
import edu.ifsp.web.Command;
import edu.ifsp.web.Flash;
import edu.ifsp.web.templates.Template;

public class ListarTasks implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		TaskDAO dao = new TaskDAO();
		
		HttpSession session = request.getSession(false);
		int userId = 0;
		
    	if (session != null) {
    	    Usuario usuario = (Usuario) session.getAttribute("usuario");
    	    if (usuario != null) {
    	       userId = usuario.getId();
    	    } else {
    	        System.out.println("Nenhum usuário encontrado na sessão.");
    	    }
    	} else {
    	    System.out.println("Sessão não encontrada.");
    	}
		
		List<Task> tasks = dao.findAll(userId);
		request.setAttribute("tasks", tasks);
		//Flash.move(request, "listar");
		Template.render("index", request, response);	
	}

}

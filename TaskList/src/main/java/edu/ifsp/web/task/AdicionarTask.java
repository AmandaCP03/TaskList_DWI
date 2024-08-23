package edu.ifsp.web.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Task;
import edu.ifsp.modelo.Usuario;
import edu.ifsp.persistencia.TaskDAO;
import edu.ifsp.web.Command;

public class AdicionarTask implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TaskDAO dao = new TaskDAO();
		Task task = new Task();
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        
        task.setText(request.getParameter("text"));
 		task.setDeadline((Date) sdf.parse(request.getParameter("deadline")));
 		
		HttpSession session = request.getSession(false);
		int userId = 0;
		
        if(request.getParameter("id") == null || request.getParameter("id").isBlank()) {
        	task.setStatus("A iniciar");
        	
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
        			
        			
        			
        	System.out.println("SESSAO:" + request.getSession());
			dao.insert(task, userId);
		}else {
			task.setStatus(request.getParameter("status"));
			task.setId(Integer.parseInt(request.getParameter("id")));
			dao.update(task);
		}
		
        
		response.sendRedirect("listar");
	}
	
	

}

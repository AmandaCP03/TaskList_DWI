package edu.ifsp.web.task;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.modelo.Task;
import edu.ifsp.persistencia.TaskDAO;
import edu.ifsp.web.Command;

public class AdicionarTask implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TaskDAO dao = new TaskDAO();
		Task task = new Task();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        
        
        task.setText(request.getParameter("text"));
		task.setDeadline(sdf.parse(request.getParameter("deadline"), null) );
		task.setStatus("A iniciar");
		
		dao.insert(task);
		response.sendRedirect("/tasks/listar");
	}

}

package edu.ifsp.web.task;

import java.util.Date;
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
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        
        task.setText(request.getParameter("text"));
 		task.setDeadline((Date) sdf.parse(request.getParameter("deadline")));
		
		
        if(request.getParameter("id") == null || request.getParameter("id").isBlank()) {
        	task.setStatus("A iniciar");
			dao.insert(task);
		}else {
			task.setStatus(request.getParameter("status"));
			task.setId(Integer.parseInt(request.getParameter("id")));
			dao.update(task);
		}
		
        
		response.sendRedirect("listar");
	}
	
	

}

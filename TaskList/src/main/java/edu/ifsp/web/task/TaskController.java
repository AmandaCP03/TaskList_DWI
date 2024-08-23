package edu.ifsp.web.task;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.web.Command;
import edu.ifsp.web.ControllerHelper;
import edu.ifsp.web.LogCommand;

@WebServlet("/tasks/*")
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try {
			
			Command cmd = getCommand(request);
			cmd.execute(request, response);
			
		} catch (Exception e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
			throw new ServletException(e);
		}

	}
	
	private Command getCommand(HttpServletRequest request) {
		String operation = ControllerHelper.extractOperation(request);
		
		Command cmd = null;
		switch (operation) {
			case "/tasks/listar":
				cmd = new ListarTasks();
				break;
			case "/tasks/cadastrar":
				cmd = new CadastroTask();
				break;
			case "/tasks/adicionar":
				cmd = new AdicionarTask();
				break;
			case "/tasks/editar":
				cmd = new EditarTask();
				break;
			case "/tasks/salvar":
				cmd = new AdicionarTask();
				break;
			case "/tasks/excluir":
				cmd = new RemoverTask();
				break;
			default:
				cmd = new LogCommand();
		}
		
		return cmd;
	}


}

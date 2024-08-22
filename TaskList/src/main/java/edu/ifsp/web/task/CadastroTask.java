package edu.ifsp.web.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.web.Command;
import edu.ifsp.web.templates.Template;

public class CadastroTask implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Template.render("cadastrar", request, response);
	}

}

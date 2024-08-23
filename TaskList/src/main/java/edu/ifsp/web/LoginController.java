<<<<<<< HEAD
package edu.ifsp.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Usuario;
import edu.ifsp.persistencia.DatabaseConnector;
import edu.ifsp.persistencia.PersistenceException;
import edu.ifsp.persistencia.UsuarioDAO;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/templates/login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        try (Connection connection = DatabaseConnector.getConnection()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            Usuario usuario = usuarioDAO.autenticar(email, senha);

            if (usuario != null ) {
            	
            	HttpSession session = request.getSession(true);
				session.setAttribute("usuario", usuario);
				
                request.getSession().setAttribute("usuarioLogado", email);
                
                response.sendRedirect("/TaskList/tasks/listar");
            } else {
                request.setAttribute("erro", "E-mail ou senha incorretos");
                request.getRequestDispatcher("/WEB-INF/templates/login.html").forward(request, response);
            }
        } catch (SQLException | PersistenceException e) {
            throw new ServletException(e);
        }
    }
}

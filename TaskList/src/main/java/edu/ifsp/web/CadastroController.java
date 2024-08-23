package edu.ifsp.web;

import edu.ifsp.persistencia.DatabaseConnector;
import edu.ifsp.persistencia.UsuarioDAO;
import edu.ifsp.modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/cadastro")
public class CadastroController extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redireciona para a página de cadastro (HTML)
        request.getRequestDispatcher("/WEB-INF/templates/cadastro.html").forward(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        try (Connection connection = DatabaseConnector.getConnection()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            usuarioDAO.cadastrar(usuario);
            response.sendRedirect("login?cadastro=true");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

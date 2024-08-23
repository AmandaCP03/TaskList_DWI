package edu.ifsp.persistencia;

import edu.ifsp.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public Usuario autenticar(String email, String senha) throws PersistenceException {
    	Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                	usuario = new Usuario();
					usuario.setId(rs.getInt("id"));
                }
            }catch (SQLException e) {
    			throw new PersistenceException(e);
    		}
        }catch (SQLException e) {
			throw new PersistenceException(e);
		}
        
        return usuario;
    }



    public void cadastrar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (name, email, senha) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();
        }
    }
}

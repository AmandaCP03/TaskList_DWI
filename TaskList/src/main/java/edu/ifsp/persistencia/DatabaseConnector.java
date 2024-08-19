package edu.ifsp.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnector {
	private static final String JDBC_URL = "jdbc:h2:~/TaskList/schema;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    
    static {
    	try {
    		Class.forName("org.h2.Driver");
    	} catch (ClassNotFoundException e) {
    		throw new RuntimeException(e);
    	}
    }

    public static Connection getConnection() throws SQLException {
    	System.out.println("Conexao: " + DriverManager.getConnection(JDBC_URL, USER, PASSWORD));
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
    
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
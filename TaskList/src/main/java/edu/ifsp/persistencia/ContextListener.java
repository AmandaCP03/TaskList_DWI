package edu.ifsp.persistencia;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Inicializando o banco de dados.");

        try (Connection conn = DatabaseConnector.getConnection()) {
            System.out.println("Conexão estabelecida com sucesso!");

            // Executar o script SQL para criar a estrutura do banco de dados
            executeSqlScript(conn, "resources/schema.sql");

            System.out.println("Banco de dados inicializado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao conectar ou criar o banco de dados.");
        }
    }
	
	public static void executeSqlScript(Connection connection, String scriptPath) {
       
        try (Statement stmt = connection.createStatement()) {
            InputStream inputStream = DatabaseConnector.class.getClassLoader().getResourceAsStream(scriptPath);
            if (inputStream == null) {
                throw new RuntimeException("Arquivo de esquema não encontrado: " + scriptPath);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder sql = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sql.append(line).append("\n");
                }

                // Executar o script SQL
                stmt.execute(sql.toString());
                System.out.println("Script SQL executado com sucesso.");
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao executar o script SQL.", e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar o Statement.", e);
        }
    }
	
	@Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Limpeza, se necessário
        System.out.println("Destruindo o contexto da aplicação.");
    }
}

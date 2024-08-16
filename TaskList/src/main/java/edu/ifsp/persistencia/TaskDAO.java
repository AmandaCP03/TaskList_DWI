package edu.ifsp.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ifsp.modelo.Task;

public class TaskDAO {
	
	public void insert(Task t) throws PersistenceException {
		try (Connection conn = DatabaseConnector.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO task (text, deadline, status) VALUES (?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS
				);
			
			ps.setString(1, t.getText());
			ps.setDate(2, (Date) t.getDeadline());
			ps.setString(3, t.getStatus());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (!rs.next()) {
				throw new IllegalStateException("Expected key missing.");
			}
			
			int id = rs.getInt(1);
			t.setId(id);			
			
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}
	
	public List<Task> findAll() throws PersistenceException {
		List<Task> tasks = new ArrayList<>();
		
		try (Connection conn = DatabaseConnector.getConnection()) {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, text, deadline, status FROM task;");
			
			
		    while (rs.next()) {
				Task t = mapRow(rs);
		    	tasks.add(t);
		    }
			

		} catch (SQLException e) {
			throw new PersistenceException(e);			
		}
		
		return tasks;
	}
	
	private Task mapRow(ResultSet rs) throws SQLException {
		Task t = new Task();
		t.setId(rs.getInt("id"));
		t.setText(rs.getString("text"));
		t.setDeadline(rs.getDate("deadline"));
		t.setStatus(rs.getString("status"));
		return t;
	}
}

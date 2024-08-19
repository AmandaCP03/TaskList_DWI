package edu.ifsp.persistencia;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.ifsp.modelo.Task;

public class TaskDAO {
	//listener de contexto

	
	public void insert(Task t) throws PersistenceException {
		try (Connection conn = DatabaseConnector.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO task (text, deadline, status) VALUES (?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS
				);
			

			
			java.util.Date utilDate = t.getDeadline();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			ps.setString(1, t.getText());
			ps.setDate(2, sqlDate);
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
	
	public Task findById(int id) throws PersistenceException {
		Task t = null;
		
		try (Connection conn = DatabaseConnector.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("SELECT id, text, deadline, status FROM task WHERE id = ?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			
			if (rs.next()) {
				t = mapRow(rs);
			}
			
			
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		
		return t;
	}
	
	private Task mapRow(ResultSet rs) throws SQLException {
		Task t = new Task();
		t.setId(rs.getInt("id"));
		t.setText(rs.getString("text"));
		t.setDeadline(rs.getDate("deadline"));
		t.setStatus(rs.getString("status"));
		return t;
	}
	
	public void update(Task t) throws PersistenceException {
		try (Connection conn = DatabaseConnector.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("UPDATE task SET deadline = ?, text = ?, status = ? WHERE id = ?;");
			
			java.util.Date utilDate = t.getDeadline();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			ps.setDate(1, sqlDate);
			ps.setString(2, t.getText());
			ps.setString(3, t.getStatus());
			ps.setInt(4, t.getId());
			System.out.println(ps);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}		
	}
	
	public void delete(int id) throws PersistenceException {
		try (Connection conn = DatabaseConnector.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM task WHERE id = ?;");
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}		
	
	}

}

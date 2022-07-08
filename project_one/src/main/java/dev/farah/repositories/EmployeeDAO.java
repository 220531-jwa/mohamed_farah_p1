package dev.farah.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dev.farah.models.*;
import dev.farah.utils.ConnectionUtil;

public class EmployeeDAO {

	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	
public Employee createUser(Employee e) {
		
		String sql = "insert into employees values (default, ?, ?, ?) returning *";
		
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getUsername());
			ps.setString(2, e.getPassword());
			ps.setBoolean(3, e.getManager());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return new Employee(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("pass"),
						rs.getBoolean("manager")
						);	
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public List<Employee> getAllUsers() {
		// create an empty array list that will hold all the users returned from the database
		List<Employee> users = new ArrayList<>();
		
		// this is the sql statement that we'll be executing
		String sql = "select * from users";
		
		// try with resources - this will auto close any resources we need without a finally block
		try (Connection conn = cu.getConnection()) {
			// prepare our statement using the connection object
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// execute our statement and store the result set in a reference variable
			ResultSet rs = ps.executeQuery();
			
			// iterate over the result set, to get the values stored in each column and creating a Java Object with them
			while(rs.next()) {
				// use the getXXX() methods to retrieve the values stored in each column of this row of the result set
				
				int id =   rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("pass");
				boolean isManager = rs.getBoolean("manager");
				Employee e = new Employee(id, username, password, isManager);
				
				users.add(e);
			}
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();	
		} 
		return null;
	}
	
	public Employee getUserById(int id) {
		
		String sql = "select * from users where id = ?"; // this question mark symbolizes and IN parameter for our statement
		
		try (Connection conn = cu.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id); // here we are setting the the "?" in our sql string to be the int id that's passed in to this method as an argument
		
			ResultSet rs = ps.executeQuery();
			
			// if the result set has a row/record
			if (rs.next()) {
				return new Employee(
						
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("pass"),
						rs.getBoolean("isManager")
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null; // Optional Class -> can help avoid NullPointer Exceptions (if any one is curious)
	}
	
	public Employee getUserByUsername(String username) {
		String sql = "select * from employees where username = ?";
		
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return new Employee(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("pass"),
						rs.getBoolean("manager")
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateUser(Employee uChange) {
		
		String sql = "update users set first_name = ?, last_name = ?, username = ?, pass = ? where id = ?";
		
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
		
			ps.setString(3, uChange.getUsername());
			ps.setString(4, uChange.getPassword());
			
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteUser(int id) {
		String sql = "delete from users where id = ?";
		
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Employee updateUserPassword(int id, String password) {
		
		String sql = "update users set pass = ? where id = ? returning *";
		
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setInt(2, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return new Employee(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("pass"),
						rs.getBoolean("isManager")
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

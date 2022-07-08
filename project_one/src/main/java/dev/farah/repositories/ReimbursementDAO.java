package dev.farah.repositories;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Request;


import dev.farah.models.Reimbursement;
import dev.farah.utils.ConnectionUtil;


public class ReimbursementDAO {
	
private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
private List<Reimbursement> Reimbursement;
	
	// CRUD
	
	public List<Reimbursement> getReimbursementsByUserId(int id) {
		List<Reimbursement> Reimbursements = new ArrayList<>();
		
		String sql = "select * from users_Reimbursements ub"
				+ " left join users u on ub.user_id = u.id"
				+ " left join Reimbursements b on ub.Reimbursement_id = b.id"
				+ " where user_id = ?";
		
		try (Connection conn = cu.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Reimbursements.add(new Reimbursement(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("pass"),
						rs.getBoolean("manager")
						));
				
			}
			return Reimbursement;
			
			
			
		} catch (SQLException e) {
		}
		return null;
	}

	public Reimbursement getUserReimbursementById(int userId, int ReimbursementId) {
		String sql = "select * from users_Reimbursements ub"
				+ " left join users u on ub.user_id = u.id"
				+ " left join Reimbursements b on ub.Reimbursement_id = b.id"
				+ " where user_id = ?"
				+ " and Reimbursement_id = ?";
		
		try (Connection conn = cu.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, ReimbursementId);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return new Reimbursement (rs.getInt("id"), rs.getString("username"), rs.getString("pass"), rs.getBoolean("isManager"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateReimbursementMarkAsRead(int userId, int ReimbursementId) {
		
		String sql = "update users_Reimbursements set is_read = true where user_id = ? and Reimbursement_id = ?";
		
		try (Connection conn = cu.getConnection()) {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, ReimbursementId);
			
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	

	public List<dev.farah.models.Reimbursement> getAllReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteReimbursement(int reimbursementid) {
		// TODO Auto-generated method stub
		
	}

	public boolean Reimbursement(Request rChanged) {
		// TODO Auto-generated method stub
		return false;
	}
}

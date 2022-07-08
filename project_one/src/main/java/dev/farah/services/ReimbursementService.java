package dev.farah.services;

import java.util.List;

import org.junit.runner.Request;

import com.google.common.graph.Graph;

import dev.farah.models.Employee;
import dev.farah.models.Reimbursement;
import dev.farah.repositories.ReimbursementDAO;
import io.cucumber.java.Status;


public class ReimbursementService {
    private static ReimbursementDAO rd = new ReimbursementDAO();
	private Graph<?> g;

    // CREATE REIMBURSEMENT

    public boolean createReimbursement(Request r) {
        boolean createdReimbursement = rd.Reimbursement(r);
        return createdReimbursement;
    }

    // GET ALL REIMBURSEMENTS

    public List<Reimbursement> getAllReimbursements() {
        return rd.getAllReimbursements();
    }

    // GET REIMBURSEMENT ID

    public Reimbursement getReimbursementByReimbursementID(int reimbursementid) throws Exception {
        Reimbursement r = (Reimbursement) rd.getReimbursementsByUserId(reimbursementid);
        if (r == null) {
            throw new Exception("Reimbursement not found");
        }
        return r;
    }



    public boolean updateReimbursement1(Request r) {
        return rd.Reimbursement(r);
    }

    

    public void deleteReimbursement(int reimbursementid) {
        rd.deleteReimbursement(reimbursementid);
    }

	public List<Reimbursement> getReimbursementsByUser(Employee u) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean changeStatus(Status s) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean gradeRequest(Graph<?> g) {
		this.setG(g);
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		return false;
	}

	public Graph<?> getG() {
		return g;
	}

	public void setG(Graph<?> g) {
		this.g = g;
	}

	
}

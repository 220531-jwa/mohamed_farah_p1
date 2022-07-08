package dev.farah.controllers;

import java.util.List;

import com.google.common.graph.Graph;

import dev.farah.models.Employee;
import dev.farah.services.ReimbursementService;
import io.cucumber.java.Status;
import io.javalin.http.Context;

public class ReimbursementController<Request> {
	
	private static ReimbursementService rs;
	private Object Reimbursement;
	


	public ReimbursementController(ReimbursementService rs) {
		ReimbursementController.rs = rs;
	}

	public void getAllReimbursements(Context ctx) {
		List<dev.farah.models.Reimbursement> reqs = rs.getAllReimbursements();
		if (!reqs.isEmpty() && reqs != null) {
			for (dev.farah.models.Reimbursement q : reqs) {
				System.out.println(q);
			}
			ctx.json(reqs);
			ctx.status(200);
		}
		else {
			ctx.status(404);
		}
	}

	public void getRequests(Context ctx) {

		Employee u = ctx.bodyAsClass(Employee.class);

		


		@SuppressWarnings("unchecked")
		List<Request> reqs = (List<Request>) ((ReimbursementService) rs).getReimbursementsByUser(u);
		if (!reqs.isEmpty() && reqs != null) {
			for (Request q : reqs) {
				System.out.println(q);
			}
			ctx.json(reqs);
			ctx.status(200);
		}
		else {
			ctx.status(404);
		}
	}

	public void createReimbursement(Context ctx) {		
		if (rs.createReimbursement((org.junit.runner.Request) Reimbursement)) {
			ctx.status(200);
		}
		else {
			ctx.status(500);
		}




	}

	
	public void getReimbursementByReimbursementID(Context ctx) {
		
	}
	
	public void updateReimbursement (Context ctx) {
		
	}
	
	public void deleteReimbursement (Context ctx) {
		
	}
	
	
	public void gradeRequest(Context ctx) {
		Graph<?> g = ctx.bodyAsClass(Graph.class);
		System.out.println("into request: " + g);
		if (((ReimbursementService) rs).gradeRequest(g)) {
			ctx.status(200);
		}
		else {
			ctx.status(404);
		}
	}
	
	public void changeStatus(Context ctx) {
		Status s = ctx.bodyAsClass(Status.class);
		if (((ReimbursementService) rs).changeStatus(s)) {
			ctx.status(200);
		}
		else {
			ctx.status(404);
		}
	}

}

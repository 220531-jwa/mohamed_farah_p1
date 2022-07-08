package dev.farah.controllers;

import dev.farah.services.EmployeeService;
import io.javalin.http.Context;
import dev.farah.models.Employee;

public class EmployeeController {

private EmployeeService as;
	

	public EmployeeController(EmployeeService as) {
		
		this.as = as;
		
	}
	
	public  void loginEmployee(Context ctx){
		Employee u = ctx.bodyAsClass(Employee.class);
		Employee loggedIn = as.login(u.getUsername(), u.getPassword()); 
		

		if (loggedIn != null) {
			ctx.json(loggedIn);
			ctx.status(200);
		}
		else {
			ctx.status(404);
		}

	}


	public void loginManager(Context ctx) {
		Employee m = ctx.bodyAsClass(Employee.class);
		Employee loggedIn = as.loginManager(m.getUsername(), m.getPassword(), m.isEmployee());
		if (loggedIn != null) {
			ctx.json(loggedIn);
			ctx.status(200);
		}
		else {
			ctx.status(404);
		}

	}
	
}

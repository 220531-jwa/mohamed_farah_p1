package javalinpack;


import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Authentication.User;

import dev.farah.controllers.ReimbursementController;
import dev.farah.controllers.EmployeeController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import dev.farah.models.Employee;
import dev.farah.repositories.EmployeeDAO;
import dev.farah.services.ReimbursementService;
import dev.farah.services.EmployeeService;

public class javalintest {
	private static Logger log = LogManager.getLogger(javalintest.class);
	public static void main(String[] args) {
		EmployeeController as = new EmployeeController(new EmployeeService(new EmployeeDAO()));
		@SuppressWarnings("rawtypes")
		ReimbursementController rs = new ReimbursementController(new ReimbursementService());
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
			config.addStaticFiles("/HTML", Location.CLASSPATH);
		});
		app.start(8080);

		app.routes(() -> {
			// LOGIN
			path("/login", () -> {
				post(as::loginEmployee);
			});
			// GET SESSION
			path("/getSession", () -> {
				get(ctx -> {
					// once set, we can access that session attribute
					User loggedInEmployee = ctx.sessionAttribute("loggedInEmployee");
					System.out.println(loggedInEmployee);
				});
			});
			// LOGOUT
			path("/logout", () -> {
				delete(ctx -> {
					// invalidating session so loggedInUser is null
					ctx.req.getSession(false).invalidate();
				});
			});
			// CREATE & GET ALL USERS
			path("/user", () -> { // http://localhost:8080/user
				post(as::loginEmployee);
				get(as::loginEmployee);
				// GET UPDATE & DELETE USER ID
				path("/{id}", () -> { // http://localhost:8080/user/id#
					get(as::loginEmployee);
					put(as::loginEmployee);
					patch(as::loginEmployee);
					delete(as::loginEmployee);
					// CREATE & GET ALL REIMBURSEMENTS
					path("/request", () -> { // http://localhost:8080/request
						post(rs::createReimbursement);
						get(rs::getAllReimbursements);
						// GET UPDATE & DELETE REIMBURSEMENT
						path("/{reimbursementid}", () -> { // http://localhost:8080/request/reimbursementid#
							get(rs::getReimbursementByReimbursementID);
							put(rs::updateReimbursement);
							delete(rs::deleteReimbursement);
						});
					});
				});
			});
		});
//		app.exception(Exception.class, (e, ctx) -> {
//			ctx.status(404);
//			ctx.result("<h1>User not found</h1>");
//		});
//		// Error Mapping
//		app.error(404, ctx -> {
//			ctx.result("Wrong URL");
//		});
	}
}
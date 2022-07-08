package dev.farah.services;
import dev.farah.models.Employee;

import dev.farah.repositories.EmployeeDAO;

public class EmployeeService {

	private EmployeeDAO employeeDAO;

	public EmployeeService(EmployeeDAO edao) {
		this.employeeDAO = edao;
	}

	public Employee login(String uname, String pword) {

		Employee e = (Employee) employeeDAO.getUserByUsername(uname);
		if (e != null){
			if (e.getPassword().equals(pword)) {
				return e;
			}
		}
		return null;
	}

	public Employee loginManager(String uname, String pword, boolean Employee) {
		if (!Employee) {
			return null;
		}
		Employee pwordCheck = login( uname, pword);
		if (pwordCheck != null) {
			if ((boolean) pwordCheck.isEmployee()) {
				return pwordCheck;
			}
		}

		return null;

	}
	
}

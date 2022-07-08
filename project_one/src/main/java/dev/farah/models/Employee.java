package dev.farah.models;

public class Employee {
	
	private int id;
	private String username;
	private String password;
	private boolean isManager;
	
	public Employee () {}
	
	public Employee(int id, String username, String password, boolean isManager) {
		this.id = id; //passing id value
		this.username = username; //passing username to the object
		this.password = password;
		this.isManager = isManager;
	}

	public String getUsername() {
		
		return username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public boolean getManager() {
		
		return isManager;
	}
	
	public String getPassword() {
		
		return password;
	}

	public Object isFin_man1() {
		// TODO Auto-generated method stub
		return isManager;
	}

	public boolean isEmployee() {
		// TODO Auto-generated method stub
		return false;
	}

	




}

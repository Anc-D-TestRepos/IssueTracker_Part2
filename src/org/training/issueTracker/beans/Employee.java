package org.training.issueTracker.beans;

public class Employee {
	private final String PRINT_FIRST_NAME = "First name : ";
	private final String PRINT_LAST_NAME = "Last name  : ";
	private final String PRINT_EMAIL = "Email      : ";
	private final String PRINT_ROLE = "Role       : ";
	private final String PRINT_PASS = "Password   : ";
	private final String PRINT_NEW_LINE = "\n";
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String password;
	
	public Employee() {
		super();
	
	}
	
	public Employee(String firstName, String lastName, String email, String role,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
			
		result= result.append(PRINT_FIRST_NAME);
		result= result.append(firstName);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_LAST_NAME);
		result= result.append(lastName);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_EMAIL);
		result= result.append(email);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_ROLE);
		result= result.append(role);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_PASS);
		result= result.append(password);
		result= result.append(PRINT_NEW_LINE);

		return result.toString();
	}

}

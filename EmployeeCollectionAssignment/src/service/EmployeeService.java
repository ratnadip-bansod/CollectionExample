package service;

import java.util.ArrayList;

import model.Employee;

public interface EmployeeService {
	public int insertEmployee(Employee employee) throws Exception;
	
	public int insertEmployeeHistory(Employee employee);
	
	public int insertEmployeeQualification(Employee employee);
	
	public ArrayList<Employee> getDeveloperList() throws Exception;
	
	public ArrayList<Employee> getEmployeeList() throws Exception;
	
	public int getCountOrganization(int employeeID) throws Exception;
	
	public Employee getEmployee(int employeeID) throws Exception;
	
	public int getSumDuration(int employeeID) throws Exception;
}

package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Employee;
import service.EmployeeService;
import service.EmployeeServiceImpl;

public class EmployeeData {
	
	public static void addEmployee() throws Exception{
		Employee employee=new Employee();
		employee.setName("ratnadip");
		employee.setAge(30);
		employee.setDesignation("developer");
		
		EmployeeService EmployeeService=new EmployeeServiceImpl();	
		int employeeId=EmployeeService.insertEmployee(employee);
		
		employee.setEmployeeId(employeeId);
		employee.setOrganizationName("techior solution");
		employee.setDuration(3);
		employee.setProjectWorked("abc");
		int result=EmployeeService.insertEmployeeHistory(employee);
		
		employee.setDegreeName("BE");
		employee.setPercentage(61);
		result=EmployeeService.insertEmployeeQualification(employee);
	}
	
	public static void printDeveloperEmployee() throws Exception{
		EmployeeService EmployeeService=new EmployeeServiceImpl();	
		ArrayList<Employee> employeeList=EmployeeService.getDeveloperList();
		if(employeeList!=null) {
			System.out.println("Following are employees who are developers :"); 
		 for(Employee employee:employeeList) { 
	         System.out.println(employee.getName());  
	      }
		}
	}
	
	public static void printOneOrganizationEmployee() throws Exception{
		EmployeeService EmployeeService=new EmployeeServiceImpl();	
		ArrayList<Employee> employeeList=EmployeeService.getEmployeeList();
		if(employeeList!=null) {
			System.out.println("Following are employees  who have not worked in other organizations :");
		 for(Employee employee:employeeList) { 	          
			 if(EmployeeService.getCountOrganization(employee.getEmployeeId())==1) {
				 
				 Employee employeeData= EmployeeService.getEmployee(employee.getEmployeeId());
				 System.out.println(employeeData.getName());
			 }
	      }
		}
	}
	
	public static void printTwoYearEmployee() throws Exception{
		EmployeeService EmployeeService=new EmployeeServiceImpl();	
		ArrayList<Employee> employeeList=EmployeeService.getDeveloperList();
		if(employeeList!=null) {
			System.out.println("Following are employees who have experience more than two years and are developers :"); 
		 for(Employee employee:employeeList) { 			   
             if(EmployeeService.getSumDuration(employee.getEmployeeId())>2) {				 
				 Employee employeeData= EmployeeService.getEmployee(employee.getEmployeeId());
				 System.out.println(employeeData.getName());
			 }
	      }
		}
	}
	
	public static void main(String args[]) throws Exception {		
		addEmployee();
		printDeveloperEmployee();
		printOneOrganizationEmployee();
		printTwoYearEmployee();
	}

}

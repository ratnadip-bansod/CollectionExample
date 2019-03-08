package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import model.Employee;

public class EmployeeServiceImpl implements EmployeeService{
	public Connection getConnection() {
		Connection con=null;
		try{ 
			Class.forName("com.mysql.cj.jdbc.Driver");  
			con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/practice","root","mysql");  				  
		}catch(Exception e){ 
			System.out.println(e);
		}  
		return con;	
	} 
	
	public int insertEmployee(Employee employee){			
			int result = 0;			
			Connection con=getConnection();			
			PreparedStatement preparedStatement = null;			
			ResultSet resultSet = null;			
			StringBuffer queryBuffer = new StringBuffer();			
			try{
				queryBuffer.append(" insert into employee_data ");
				queryBuffer.append(" (name, age, designation)");			
				queryBuffer.append(" values (?,?,?) ");
				
				preparedStatement = con.prepareStatement(queryBuffer.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
				
				preparedStatement.setString(1, employee.getName());	
				preparedStatement.setInt(2, employee.getAge());
				preparedStatement.setString(3, employee.getDesignation());
					
			result = preparedStatement.executeUpdate(); 			
				 
				ResultSet rs = preparedStatement.getGeneratedKeys();
				int generatedKey = 0;
				if(rs != null && rs.next()){				
					result = rs.getInt(1);						
				}
				
				
			}catch(SQLException sqlException){
				sqlException.printStackTrace();
			}catch(Exception exception){
				exception.printStackTrace();
			}finally{
				if(preparedStatement != null){
					try {
						preparedStatement.close();
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return result;
		}
	
	public int insertEmployeeHistory(Employee employee){			
		int result = 0;			
		Connection con=getConnection();			
		PreparedStatement preparedStatement = null;			
		ResultSet resultSet = null;			
		StringBuffer queryBuffer = new StringBuffer();			
		try{
			queryBuffer.append(" insert into employee_history ");
			queryBuffer.append(" (employee_id, organization_name, duration,project_worked)");			
			queryBuffer.append(" values (?,?,?,?) ");
			
			preparedStatement = con.prepareStatement(queryBuffer.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, employee.getEmployeeId());	
			preparedStatement.setString(2, employee.getOrganizationName());
			preparedStatement.setInt(3, employee.getDuration());
			preparedStatement.setString(4, employee.getProjectWorked());
				
		result = preparedStatement.executeUpdate(); 			
			 
			ResultSet rs = preparedStatement.getGeneratedKeys();			
			if(rs != null && rs.next()){				
				result = rs.getInt(1);				
			}
			
			
		}catch(SQLException sqlException){
			sqlException.printStackTrace();
		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public int insertEmployeeQualification(Employee employee){			
		int result = 0;			
		Connection con=getConnection();			
		PreparedStatement preparedStatement = null;			
		ResultSet resultSet = null;			
		StringBuffer queryBuffer = new StringBuffer();			
		try{
			queryBuffer.append(" insert into employee_qualification ");
			queryBuffer.append(" (employee_id, degree_name, percentage,date)");			
			queryBuffer.append(" values (?,?,?,?) ");
			
			preparedStatement = con.prepareStatement(queryBuffer.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, employee.getEmployeeId());
			preparedStatement.setString(2, employee.getDegreeName());	
			preparedStatement.setFloat(3, employee.getPercentage());			
			/*
			 * DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); Calendar
			 * cal = Calendar.getInstance();
			 * preparedStatement.setString(4, dateFormat.format(cal));
			 */
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));
			preparedStatement.setString(4, dtf.format(now));
				
		   result = preparedStatement.executeUpdate(); 			
			 
			ResultSet rs = preparedStatement.getGeneratedKeys();
			int generatedKey = 0;
			if(rs != null && rs.next()){				
				result = rs.getInt(1);				
			}
			
			
		}catch(SQLException sqlException){
			sqlException.printStackTrace();
		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	

	public ArrayList<Employee> getDeveloperList() throws Exception{	
			
		ArrayList<Employee> employeeList = null;
		
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		
		StringBuffer queryBuffer = new StringBuffer(); 

		try{
			queryBuffer.append(" Select * from employee_data ");			
			
			queryBuffer.append(" where designation = 'developer' ");
			
			preparedStatement = getConnection().prepareStatement(queryBuffer.toString());			
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet != null ){
				employeeList = new ArrayList<Employee>();
				while(resultSet.next()){
					Employee employee = new Employee();	
					employee.setEmployeeId(resultSet.getInt("employee_id"));
					employee.setName(resultSet.getString("name"));					
					employeeList.add(employee);
				}
			}
		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return employeeList;
	}
	
	public ArrayList<Employee> getEmployeeList() throws Exception{	
		
		ArrayList<Employee> employeeList = null;
		
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		
		StringBuffer queryBuffer = new StringBuffer(); 

		try{
			queryBuffer.append(" Select * from employee_data ");				
			
			
			preparedStatement = getConnection().prepareStatement(queryBuffer.toString());			
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet != null ){
				employeeList = new ArrayList<Employee>();
				while(resultSet.next()){
					Employee employee = new Employee();					
					employee.setName(resultSet.getString("name"));	
					employee.setEmployeeId(resultSet.getInt("employee_id"));	
					employeeList.add(employee);
				}
			}
		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return employeeList;
	}
	
	public int getCountOrganization(int employeeID) throws Exception{	
		
		int result = 0;
		
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		
		StringBuffer queryBuffer = new StringBuffer(); 

		try{
			queryBuffer.append(" Select count(*) from employee_history where employee_id=?");				
			
			
			preparedStatement = getConnection().prepareStatement(queryBuffer.toString());
			preparedStatement.setInt(1, employeeID);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet != null ){			
				while(resultSet.next()){						
					result=resultSet.getInt(1);						
				}
			}
		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public Employee getEmployee(int employeeID) throws Exception{	
		
		Employee employee = new Employee();
		
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		
		StringBuffer queryBuffer = new StringBuffer(); 

		try{
			queryBuffer.append(" Select * from employee_data where employee_id=?");				
			
			
			preparedStatement = getConnection().prepareStatement(queryBuffer.toString());
			preparedStatement.setInt(1, employeeID);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet != null ){			
				while(resultSet.next()){					
					employee.setName(resultSet.getString("name"));
				}
			}
		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return employee;
	}
	
	public int getSumDuration(int employeeID) throws Exception{	
		
		int result = 0;
		
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		
		StringBuffer queryBuffer = new StringBuffer(); 

		try{
			queryBuffer.append(" Select sum(duration) from employee_history where employee_id=?");				
			
			
			preparedStatement = getConnection().prepareStatement(queryBuffer.toString());
			preparedStatement.setInt(1, employeeID);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet != null ){			
				while(resultSet.next()){						
					result=resultSet.getInt(1);						
				}
			}
		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
}

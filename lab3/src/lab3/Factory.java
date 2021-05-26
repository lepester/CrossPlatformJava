package lab3;
import java.util.ArrayList;
import java.util.Collection;

public class Factory {
	public ArrayList<Employee> Employees  = new ArrayList<Employee>();
	
	public int employeeHourCount() {
		int count = 0;
		for(int i = 0; i < Employees.size(); i++) {
			if(Employees.get(i) instanceof EmployeeHour)
				count++;
		}
		return count;
	}	
	
	public int employeeMonthCount() {
		int count = 0;
		for(int i = 0; i < Employees.size(); i++) {
			if(Employees.get(i) instanceof EmployeeMonth)
				count++;
		}
		return count;
	}	
}

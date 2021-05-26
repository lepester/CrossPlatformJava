package lab3;

public class Main {
	public static void main(String[] args) throws java.lang.Exception {
	Factory factory = new Factory();
	
	factory.Employees.add(new EmployeeHour("Misha"));
	factory.Employees.add(new EmployeeHour("Cheb"));
	factory.Employees.add(new EmployeeHour("Misha"));
	
	factory.Employees.add(new EmployeeMonth("Misha"));
	factory.Employees.add(new EmployeeMonth("Misha"));
	factory.Employees.add(new EmployeeMonth("Misha"));
	factory.Employees.add(new EmployeeMonth("Misha"));
	factory.Employees.add(new EmployeeMonth("Misha"));
	factory.Employees.add(new EmployeeMonth("Misha"));
	factory.Employees.add(new EmployeeMonth("Misha"));
	factory.Employees.add(new EmployeeMonth("Misha"));
	factory.Employees.add(new EmployeeMonth("Misha"));
	factory.Employees.add(new EmployeeMonth("Misha"));
	
	System.out.println("EmployeeHour " + factory.employeeHourCount());
	System.out.println("EmployeeMonth " + factory.employeeMonthCount());

 }
}
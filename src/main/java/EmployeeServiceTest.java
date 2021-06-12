public class EmployeeServiceTest {
    public static void main(String[] args){
        EmployeeService employeeService = new EmployeeService(5);

        Employee [] employees = employeeService.generateEmployees();

        employeeService.printEmployees();

        double s = employeeService.calculateSalaryAndBonus();

        employeeService.getById(2);
        System.out.println(employeeService.getById(1));

    }
}

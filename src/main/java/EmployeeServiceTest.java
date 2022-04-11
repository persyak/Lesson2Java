public class EmployeeServiceTest {
    public static void main(String[] args){
        EmployeeService employeeService = new EmployeeService();

        employeeService.generateEmployees(7);

        employeeService.printEmployees();

//        System.out.println(employeeService.calculateSalaryAndBonus());
//
//        employeeService.getById(2);
//
//        employeeService.getByName("Anna");
//
//        employeeService.sortByName();
//
//        employeeService.printEmployees();
//
//        System.out.println();
//
//        employeeService.sortByNameAndSalary();
//
        employeeService.printEmployees();

        employeeService.edit(employeeService.getById(2));

        employeeService.remove(3);
    }
}

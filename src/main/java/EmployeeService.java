import java.util.Random;

public class EmployeeService {
    public int initialEmployeeCount;
    private Employee[] employees;

    EmployeeService(int initialEmployeeCount) {
        this.initialEmployeeCount = initialEmployeeCount;
        employees = new Employee[initialEmployeeCount];
    }

    Employee[] generateEmployees() {
        return generateEmplyees(initialEmployeeCount);
    }

    Employee[] generateEmplyees(int employeeCount) {

        final int MIN_AGE = 18;
        final int MIN_SALARY = 1000;
        String gender;
        Random random = new Random();
        String[] positions = {"Developer", "Designer", "Manager"};
        String[] names = {"Liza", "Alex", "Tolik", "Anna", "Anton"};

        for (int i = 0; i < employeeCount; i++) {
            int randomIndex = random.nextInt(names.length);
            String name = names[randomIndex];
            randomIndex = random.nextInt(positions.length);
            int age = random.nextInt(47) + MIN_AGE;
            int salary = random.nextInt(5000) + MIN_SALARY;
            int fixedBags = random.nextInt(20) + 1;
            int workDays = random.nextInt(20) + 1;
            int rate = random.nextInt(10) + 1;


            switch (positions[randomIndex]) {
                case "Developer":
                    employees[i] = new Developer(i, name, age, salary, generateGender(name), fixedBags);
                    break;
                case "Designer":
                    employees[i] = new Designer(i, name, age, salary, generateGender(name), rate, workDays);
                    break;
                default:
                    employees[i] = new Manager(i, name, age, salary, generateGender(name));
            }
        }
        return employees;
    }

    void printEmployees() {
        for (Employee employee : employees) {
            if (employee instanceof Developer) {
                System.out.print("Developer name is ");
            } else if (employee instanceof Designer) {
                System.out.print("Designer name is ");
            } else {
                System.out.print("Manager name is ");
            }
            System.out.println(employee.name + ", age is " + employee.age +
                    ", gender is " + employee.gender + ", salary is " + employee.salary());
        }
    }

    double calculateSalaryAndBonus() {
        double salaryAndBonuses = 0;
        for (Employee employee : employees) {
            salaryAndBonuses += employee.salary();
        }
        return salaryAndBonuses;
    }

    Employee getById(long id) {
        validateId(id);
        return employees[(int) id];
    }

    Employee[] getByName(String name){
        int count = 0;
        Employee[] employeeByName;
        for(Employee employee: employees){
            if(employee.name.equals(name)){
                count++;
            }
        }
        employeeByName = new Employee[count];
        for(int i=0; i<count; i++){
            if(employees[i].name.equals(name)){
                employeeByName[i] = employees[i];
            }
        }
        return employeeByName;
    }

    Employee[] sortByName(){
        for(int i=0; i<employees.length; i++){
            for(int j=employees.length-1; j>=i; j--){
                if (employees[j-1].name.compareTo(employees[j].name)>0){
                    Employee temp = employees[j-1];
                    employees[j-1] = employees[j];
                    employees[j] = temp;
                }
            }
        }
        return employees;
    }

    Employee[] sortByNameAndSalary(){
        Employee[] employeesByName = sortByName();
        for(int i=0; i<employeesByName.length; i++){
            for(int j=employeesByName.length-1; j>=i; j--){
                if (employeesByName[j-1].name.equals(employeesByName[j].name) &&
                        employeesByName[j-1].salary()<employeesByName[j].salary()){
                    Employee temp = employees[j-1];
                    employees[j-1] = employees[j];
                    employees[j] = temp;
                }
            }
        }
        return employeesByName;
    }

    Employee edit(Employee employee){
        Employee editEmployee = getById(employee.id);
// TODO: Нужно реализовать метод
        return employee;
    }

    Employee remove(long id){
        validateId(id);
        Employee removedEmployee = getById(id);
        for(int i=(int)id; i<employees.length; i++){
            employees[i] = employees[i+1];
        }
        Employee[] newEmployees = new Employee[employees.length-1];
        int a = 0;
        System.arraycopy(employees, a, newEmployees, a, employees.length-1);
        employees = newEmployees;
        return employees[(int)id];
    }

    private String generateGender(String name) {
        if (name.equals("Liza") || name.equals("Anna")) {
            return "female";
        }
        return "male";
    }

    void validateId(long id) {
        if (id < 0 || id >= employees.length) {
            throw new IndexOutOfBoundsException("Employees list contains " + employees.length +
                    " emplyees. Please choose id between ZERO and " + (employees.length-1));
        }
    }
}

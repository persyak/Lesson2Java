import java.util.Random;

public class EmployeeService {
    private Employee[] employees;

    Employee generateEmployee(int index) {
        final int MIN_AGE = 18;
        final int MIN_SALARY = 1000;
        Random random = new Random();
        String[] positions = {"Developer", "Designer", "Manager"};
        String[] names = {"Liza", "Alex", "Tolik", "Anna", "Anton"};

        Employee employee;
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
                employee = new Developer(index + 1, name, age, salary, generateGender(name), fixedBags);
                break;
            case "Designer":
                employee = new Designer(index + 1, name, age, salary, generateGender(name), rate, workDays);
                break;
            default:
                employee = new Manager(index + 1, name, age, salary, generateGender(name));
        }
        return employee;
    }

    Employee[] generateEmployees(int employeeCount) {
        employees = new Employee[employeeCount];
        for (int i = 0; i < employeeCount; i++) {
            employees[i] = generateEmployee(i);
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
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].id == id) {
                return employees[i];
            }
        }
        return null;
    }

    private int getIndexById(long id) {
        validateId(id);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].id == id) {
                return i;
            }
        }
        return -1;
    }


    Employee[] getByName(String name) {
        int count = 0;
        Employee[] employeeByName;
        for (Employee employee : employees) {
            if (employee.name.equals(name)) {
                count++;
            }
        }
        if (count == 0) {
            return null;
        } else {
            int index = 0;
            employeeByName = new Employee[count];
            for (Employee employee : employees) {
                if (employee.name.equals(name)) {
                    employeeByName[index] = employee;
                    index++;
                }
            }
            return employeeByName;
        }
    }

    Employee[] sortByName() {
        for (int i = 1; i < employees.length; i++) {
            for (int j = employees.length - 1; j >= i; j--) {
                if (employees[j - 1].name.compareTo(employees[j].name) > 0) {
                    Employee temp = employees[j - 1];
                    employees[j - 1] = employees[j];
                    employees[j] = temp;
                }
            }
        }
        return employees;
    }

    Employee[] sortByNameAndSalary() {
        Employee[] employeesByName = sortByName();
        for (int i = 1; i < employeesByName.length; i++) {
            for (int j = employeesByName.length - 1; j >= i; j--) {
                if (employeesByName[j - 1].name.equals(employeesByName[j].name) &&
                        employeesByName[j - 1].salary() < employeesByName[j].salary()) {
                    Employee temp = employees[j - 1];
                    employees[j - 1] = employees[j];
                    employees[j] = temp;
                }
            }
        }
        return employeesByName;
    }

    Employee edit(Employee employee) {
        Employee editedEmployee = getById(employee.id);
        employee = generateEmployee(getIndexById(employee.id));
        return editedEmployee;
    }

    Employee remove(long id) {
        validateId(id);
        Employee removedEmployee = getById(id);
        for (int i = getIndexById(id); i < employees.length-1; i++) {
            employees[i] = employees[i + 1];
        }
        Employee[] newEmployees = new Employee[employees.length - 1];
        int a = 0;
        System.arraycopy(employees, a, newEmployees, a, employees.length - 1);
        employees = newEmployees;
        return removedEmployee;
    }

    private String generateGender(String name) {
        if (name.equals("Liza") || name.equals("Anna")) {
            return "female";
        }
        return "male";
    }

    void validateId(long id) {
        if (id < 1 || id >= employees.length + 1) {
            throw new IndexOutOfBoundsException("Employees list contains " + employees.length +
                    " employees. Please choose id between 1 and " + (employees.length));
        }
    }
}

public class Manager extends Employee{

    Manager(long id, String name, int age, int salary, String gender){
        super(id, name, age, salary, gender);
    }

    int salary() {
        return salary;
    }
}

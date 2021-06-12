import java.util.Random;

public abstract class Employee {
    long id;
    String name;
    int age;
    int salary;
    String gender;

    Employee (long id, String name, int age, int salary, String gender){
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
    }

    abstract int salary();

}

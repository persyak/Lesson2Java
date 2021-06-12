public class Designer extends Employee{
    int rate;
    int workDays;

    Designer(long id, String name, int age, int salary, String gender, int rate, int workDays){
        super(id, name, age, salary, gender);
        this.rate = rate;
        this.workDays = workDays;
    }

    int salary() {
        return (salary + rate*workDays);
    }
}

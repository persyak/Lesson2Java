import java.util.Random;

public class Developer extends Employee{
    int fixedBugs;

    Developer (long id, String name, int age, int salary, String gender, int fixedBugs){
        super(id, name, age, salary, gender);
        this.fixedBugs = fixedBugs;
    }

    private static final int COEFFICIENT = 200;

    Random random = new Random();
    boolean randomBoolean = random.nextBoolean();

    int salary(){
        return ((salary + fixedBugs*COEFFICIENT) * (randomBoolean? 2:0));
    }



}

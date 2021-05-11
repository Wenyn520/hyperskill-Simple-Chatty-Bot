//extends abstract class
public class Dog1 extends Animal1 {
    public Dog1(String name) {
        super(name);
    }

    @Override
    public void eat() {
        //calling superclass method
        System.out.println(getName()+" is eating");
    }

    @Override
    public void breathe() {
        System.out.println("Breathe in and out");

    }
}

//abstract class extends an abstract class
//implement interface
public abstract class Bird extends Animal1 implements CanFly{
    public Bird(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(getName()+" is pecking");
    }

    @Override
    public void breathe() {
        System.out.println("Breathe in and out");
    }

    @Override
    public void fly() {
        System.out.println(getName()+" is flying");
    }
}

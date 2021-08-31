//abstract class
public abstract class Animal1 {
    private String name;
    //abstract class can have constructor
    public Animal1(String name) {
        this.name = name;
    }
    //abstract methods
    //every animal can eat, breathe, so place it in this base abstract class
    public abstract void eat();
    public abstract void breathe();

    //can have regular methods
    public String getName() {
        return name;
    }
}

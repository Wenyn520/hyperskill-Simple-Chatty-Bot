public class Dog extends Animal{

    private int eyes, legs, tail, teeth;
    private String coat;

    //constructor without brain and body, since all Dogs have 1 brain and body
    //hence when call Dog no need to pass the value
    public Dog(String name, int size, int weight, int eyes, int legs, int tail, int teeth, String coat) {
        //calling its superclass
        //all dogs have 1 brain and body
        super(name, 1, 1, size, weight);
        this.eyes = eyes;
        this.legs = legs;
        this.tail = tail;
        this.teeth = teeth;
        this.coat = coat;
    }
    private void chew(){
        System.out.println("Dog.chew() is called");
    }

    @Override
    public void eat() {
        System.out.println("Dog.eat() is called");
        chew();
        //super.eat() means it is calling its eat() of superclass
        //is optional to call superclass
        super.eat();
    }
    public void walk(){
        System.out.println("Dog.walk() called");
        //java will find in Dog class first, if there is move(), then will call that one
        //if in Dog class dont have move(), will search in its superclass
        move(5);
    }
    public void run(){
        System.out.println("Dog.run() called");
        move(10);
    }

    @Override
    //example of covariant return type
    //where the return type of born(), is a child of parent class
    public Dog born() {
        return new Dog("New",1,1,2,4,1,10,"Coat");
    }
}

public class DeluxeBurger extends Hamburger {

    public DeluxeBurger() {
        super("Deluxe", "Sausage and bacon", 14.54, "White");
        //call hamburger class to add addition automatically
        super.addHamburgerAddition1("Chips",2.75);
        super.addHamburgerAddition2("Drink",1.81);
    }

    //to avoid adding additional items
    //by overriding method in DeluxeBurger
    @Override
    public void addHamburgerAddition1(String name, double price) {
        System.out.println("Cannot add additional item");
    }

    @Override
    public void addHamburgerAddition2(String name, double price) {
        System.out.println("Cannot add additional item");
    }

    @Override
    public void addHamburgerAddition3(String name, double price) {
        System.out.println("Cannot add additional item");
    }

    @Override
    public void addHamburgerAddition4(String name, double price) {
        System.out.println("Cannot add additional item");
    }
}

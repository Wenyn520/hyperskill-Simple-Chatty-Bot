public class Car extends Vehicle{
    //methods are set as private, to prevent access from outside
    private int doors;
    private int wheels;
    private String model;
    private String engine;
    private String colour;
    private int gears;
    private boolean isManual;
    private int currentGear;

    public Car(String name, String size, int doors, int wheels, int gears, boolean isManual) {
        super(name, size);
        this.doors = doors;
        this.wheels = wheels;
        this.gears = gears;
        this.isManual = isManual;
        this.currentGear = 1;
    }

    //methods are public
    //this setter will update field variable model, of the value in parameter model
    //setter method is to restrict input, only valid inputs are passed to variable
    public void setModel (String model){
        //equalsIgnoreCase <- compare strings ignoring case
        //only set model when input is as below
        if (model.equalsIgnoreCase("commodore") || model.equalsIgnoreCase("carrera")){
            //when access class field, need to put "this" keyword
            this.model = model;
        }
        else
            this.model = "Unknown";
    }
    //getter
    //return what the current model is
    public String getModel(){
        return this.model;
    }

    public void setCurrentGear(int currentGear) {
        this.currentGear = currentGear;
        System.out.println("Car.setCurrentGear() is called, change to "+this.currentGear);
    }
    public void changeVelocity (int speed, int direction){
        System.out.println("Car.changeVelocity() is called");
        move(speed,direction);
    }
}

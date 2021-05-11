//override equals()
public class Dog2 {
    private String name;

    public Dog2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public final boolean equals(Object obj) { //set it as final, so that Labrador cannot override it
        if(this==obj){ //default Object class equals() implementation
            return true;
        }
        if(obj instanceof Dog){ //if obj is null will return false
            String objName = ((Dog) obj).getName();
            return this.name.equals(objName); //comparing String
        }
        return false;
    }

}

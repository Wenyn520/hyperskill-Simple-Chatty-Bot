//instructor solution - arraylist challenge
public class Contact1 {
    private String name;
    private String phoneNumber;

    public Contact1(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    //to create new Contact
    public static Contact1 createContact(String name, String phoneNumber){
        //return constructor
        return new Contact1(name,phoneNumber);
    }
}

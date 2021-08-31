//final keyword practice
public class ExtendedPassword extends Password {
    private int decryptedPassword;

    public ExtendedPassword(int password) {
        super(password);
        this.decryptedPassword = password;
    }
    //by override the method, security compromised, as saved password can be change by not declaring Password
    //class final, which can let it being subclassed and override method
//    @Override
//    public void storePassword() {
//        System.out.println("Saving password as "+decryptedPassword);
//    }
}

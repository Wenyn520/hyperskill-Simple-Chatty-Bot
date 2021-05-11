//coding challenge 
public class Account {
    private int accountNumber;
    private double balance;
    private String customerName;
    private String email;
    private String phoneNumber;

    //constructor with parameters
    public Account (int accountNumber, double balance, String customerName, String email, String phoneNumber){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    //this is the default constructor Java auto create, which dont have parameters
    //no need manual create
    public Account (){
        //this() call main (complete) constructor to initialize value when empty constructor is called
        //this() has to be the first statement to be called in constructor
        this(0,0.0,"Default name","Default email","Default phone");
        System.out.println("This is default constructor");
    }

    //another constructor with 3 parameters only
    public Account(String customerName, String email, String phoneNumber) {
        //call main complete constructor to set values
        //accountNumber and balance is default to 0
        this(0,0.00,customerName,email,phoneNumber);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void deposit (double val){
        this.balance +=val;
        //to round to 2 decimal places
        this.balance = Double.parseDouble(String.format("%.2f",this.balance));
    }
    public void withdraw (double val){
        //can withdraw only when after withdrawal, balance is not zero
        if (this.balance-val>=0){
            this.balance -=val;
            this.balance = Double.parseDouble(String.format("%.2f",this.balance));
        }
        else
            System.out.println("Insufficient balance");
    }
}

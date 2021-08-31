import java.util.ArrayList;
//Coding challenge - Simple bank application
//All customer related info should stay at customer class
//All branch related info should stay in branch class
public class Branch {
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private String name;

    public Branch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //add new customer
    public void addCustomer(String name, double iniAmt){
        int index = findCustomer(name);
        if(index>=0){
            System.out.println("Customer already exists");
        }else{
            //create new customer
            Customer customer = new Customer(name, iniAmt);
            customers.add(customer);
            System.out.println("Customer successful added");
            printCustomers();
        }
    }
    //add txn to customer
    public void addTransaction(String name, double transaction){
        int index = findCustomer(name);
        if(index>=0){
            customers.get(index).addTransaction(transaction);
            System.out.println("Transaction successful added");
            printTransactions(name);
        }
        else{
            System.out.println("Customer not found");
        }
    }
    //print all transactions customer have
    public void printTransactions(String name){
        int index = findCustomer(name);
        if(index>=0){
            customers.get(index).printTransaction();
        }
        else{
            System.out.println("Customer not found");
        }
    }
    //find customer
    public int findCustomer(String name) {
        for (int i = 0; i < customers.size(); i++) {
            if ((customers.get(i).getName()).equals(name)) {
                return i;
            }
        }
        return -1;
    }

    //return Customer object if found
    public Customer findACustomer(String name){
        for (int i = 0; i < customers.size(); i++) {
            if ((customers.get(i).getName()).equals(name)) {
                return customers.get(i);
            }
        }
        return null;
    }
    //print names of all customers
    public void printCustomers(){
        if(customers.size()==0){
            System.out.println("No customers");
        }else{
            for(int i=0; i<customers.size();i++){
                System.out.println((i+1)+": "+customers.get(i).getName());
            }
        }

    }

}

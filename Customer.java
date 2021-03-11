import java.util.ArrayList;
//Coding challenge - Simple bank application
//all customer related info should stay in Customer.java
public class Customer {
    private ArrayList<Double> transactions = new ArrayList<Double>();
    private String name;
    private double initialAmount;

    public Customer(String name, double initialAmount) {
        this.name = name;
        this.initialAmount = initialAmount;
        transactions.add(this.initialAmount);
    }

    public String getName() {
        return name;
    }

    //add new transaction to customer
    public void addTransaction(double amt){
        //autoboxing
        transactions.add(amt);
    }
    //print transactions of a customer
    public void printTransaction(){
        if(transactions.size()==0){
            System.out.println("No transaction found");
        }
        else{
            for(int i=0; i<transactions.size(); i++){
                System.out.println((i+1)+": "+transactions.get(i));
            }
        }
    }
}

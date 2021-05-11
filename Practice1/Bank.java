import java.util.ArrayList;
//Coding challenge - Simple bank application
public class Bank {
    private ArrayList<Branch> branches = new ArrayList<Branch>();

    //add new branch
    public void addBranch(String name){
        //check if branch already exist
        int index = findBranch(name);
        if(index>=0){
            System.out.println("Branch already exist");
        }else{
            Branch branch = new Branch(name);
            branches.add(branch);
        }
    }

    //add new customer to existing branch
    public void addCustomerToBranch(String branchName, String customerName, double amt){
        int index = findBranch(branchName);
        if(index<0){
            System.out.println("Branch not found");
        }else{
            branches.get(index).addCustomer(customerName,amt);
        }
    }
    //add new transaction to existing customer
    public void addTransactionToCustomer(String branchName, String customerName, double amt){
        int index = findBranch(branchName);
        if(index<0){
            System.out.println("Branch not found");
        }else{
            branches.get(index).addTransaction(customerName,amt);
        }
    }

    //find a branch
    public int findBranch(String name){
        for (int i=0; i<branches.size(); i++){
            if((branches.get(i).getName()).equals(name)){
                return i;
            }
        }
        return -1;
    }

    //print out all branch name
    public void printBranches(){
        if(branches.size()==0){
            System.out.println("No branch found");
        }else{
            for(int i=0; i<branches.size(); i++){
                System.out.println((i+1)+": "+branches.get(i).getName());
            }
        }
    }
    //print all customers in a branch
    public void printCustomers(String branch){
        int index = findBranch(branch);
        if(index==-1){
            System.out.println("Branch not found");
        }else{
            Branch branchName = branches.get(index);
            branchName.printCustomers();
        }
    }

    //print all transactions of customer of a branch
    public void printTxn(String branch, String name){
        int index = findBranch(branch);
        if(index==-1){
            System.out.println("Branch not found");
        }else{
            Branch branchName = branches.get(index);
            Customer customer = branchName.findACustomer(name);
            if(customer==null){
                System.out.println("Customer not found");
            }else{
                customer.printTransaction();
            }
        }
    }
}

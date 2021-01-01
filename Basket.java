import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//Sorted Collections practice
//like a shopping cart
public class Basket {
    private final String name;
    private final Map<StockItem,Integer> list; //store item and quantity of item in shopping cart

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }
    //add to basket
    public int addToBasket(StockItem item, int quantity){
        if (item!=null&&quantity>0){
            //getOrDefault() return value
            //if item is alr in basket, return quantity of it, else if item not in basket, return quantity is 0
            int inBasket = list.getOrDefault(item,0);
            //add the quantity to be added
            list.put(item,inBasket+quantity); //new value will overwrite old value
            return inBasket; //return original quantity
        }
        return 0;
    }
    //remove item from basket
    public int removeFromBasket(StockItem item,int quantity){
        if(item!=null&&quantity>0){
            //if item is alr in basket, return quantity of it, else if item not in basket, return quantity is 0
            int inBasket = list.getOrDefault(item,0);
            int newQuantity = inBasket-quantity;
            if(newQuantity>0){ //removed quantity cannot larger than quantity in basket
                list.put(item,newQuantity); //will replace old value
                return quantity; //return remove quantity
            }else if(newQuantity==0){
                list.remove(item); //remove entire item from basket list
                return quantity;
            }
        }
        return 0; //indicating not remove
    }
    public void clearBasket(){
        //remove all mappings in Map
        this.list.clear();
    }
    public Map<StockItem,Integer> Items(){
        //return map view which cannot be modify
        return Collections.unmodifiableMap(list);
    }

    //print total number of items in basket, all item and their quantity, then total price of basket
    @Override
    public String toString() {
        //ternary operator - condition ? valueIfTrue : valueIfFalse
        //if size is 1 then print "item", else print "items"
        String s = "\nShopping basket "+name+" contains "+list.size()+(list.size()==1 ? " item" : " items")+"\n";
        double totalCost = 0.0;
        //entrySet() return Set elements from Map
        for(Map.Entry<StockItem,Integer> item:list.entrySet()){
            s = s+item.getKey()+". "+item.getValue()+" purchased\n";
            //total price = price per quantity*quantity
            totalCost+=item.getKey().getPrice()*item.getValue();
        }
        return s+"Total cost "+totalCost;
    }
}

package TCP;

import java.io.Serializable;

public class Product implements Serializable{
    private final int id;
    private final String name;
    private final double price;
    private int discount;
    private static final long serialVersionUID = 20231107L;

    public Product(int id, String name, double price, int discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", discount=" + discount + '}';
    } 
    
    public void calculateDiscount(){
        var whole = (int) Math.floor(this.price);
       
        while (whole > 0){
            int digit = whole % 10;
            this.discount += digit;
            whole /= 10;
        }        
    }
    
}

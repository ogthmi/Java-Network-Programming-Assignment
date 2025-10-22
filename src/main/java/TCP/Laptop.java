package TCP;

import java.io.Serializable;

public class Laptop implements Serializable {
    private int id;
    private String code, name;
    private int quantity;
    private static final long serialVersionUID = 20150711L;

    public Laptop(int id, String code, String name, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public void normalize(){
        normalizeName();
        normalizeQuantity();
    }

    private void normalizeName(){
        String[] tokens = name.split("\\s+");

        StringBuilder builder = new StringBuilder();

        builder.append(tokens[tokens.length - 1]);

        for (int i = 1; i < tokens.length - 1; i++){
            builder.append(" ").append(tokens[i]);
        }

        builder.append(" ").append(tokens[0]);

        name = builder.toString();
    }

    private void normalizeQuantity(){
        int temp = 0;

        while (quantity > 0){
            temp = temp * 10 + quantity % 10;
            quantity /= 10;
        }

        quantity = temp;
    }
}

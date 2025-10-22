package UDP;

import java.io.Serializable;

public class Product implements Serializable {
    private final String id;
    private final String code;
    private String name;
    private int quantity;
    private static final long serialVersionUID = 20161107;

    public Product(String id, String code, String name, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", code=" + code + ", name=" + name + ", quantity=" + quantity + '}';
    }

    public void normalize() {
        normalizeProductName();
        normalizeQuantity();
    }

    private void normalizeProductName() {
        String[] tokens = name.split("\\s+");
        StringBuilder sb = new StringBuilder();

        sb.append(tokens[tokens.length - 1]);
        for (int i = 1; i < tokens.length - 1; i++) {
            sb.append(" " + tokens[i]);
        }
        sb.append(" " + tokens[0]);

        name = sb.toString();
    }

    private void normalizeQuantity() {
        int correctQuantity = 0;
        while (quantity > 0) {
            correctQuantity = correctQuantity * 10 + quantity % 10;
            quantity /= 10;
        }
        quantity = correctQuantity;
    }
}

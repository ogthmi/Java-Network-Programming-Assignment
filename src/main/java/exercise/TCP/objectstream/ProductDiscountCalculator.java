package exercise.TCP.objectstream;

import TCP.Product;
import executor.TCP.TCPBase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ProductDiscountCalculator extends TCPBase {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ProductDiscountCalculator() {
        this.setPort(2209);
    }

    @Override
    protected void setStream() throws IOException {
        this.ois = new ObjectInputStream(socket.getInputStream());
        this.oos = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    protected void sendMessage(String message) throws Exception {
        oos.writeObject(message);
    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN541;ZhTrT7pi";
    }


    @Override
    protected void solve() throws IOException, ClassNotFoundException {
        var product = (Product) ois.readObject();
        System.out.println("Server resposse: " + product);

        product.calculateDiscount();
        System.out.println("Discounted: " + product);

        oos.writeObject(product);
        oos.flush();

        oos.close();
        ois.close();
    }
}

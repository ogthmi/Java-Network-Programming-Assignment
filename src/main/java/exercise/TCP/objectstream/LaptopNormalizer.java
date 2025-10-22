package exercise.TCP.objectstream;

import TCP.Laptop;
import executor.TCP.TCPBase;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LaptopNormalizer extends TCPBase {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public LaptopNormalizer(){this.setPort(2209);}

    @Override
    protected void setStream() throws Exception {
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    protected void sendMessage(String message) throws Exception {
        oos.writeObject(message);
        oos.flush();
    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN613;JYfXtNHH";
    }

    @Override
    protected void solve() throws Exception {
        var laptop = (Laptop) ois.readObject();
        System.out.println("Original laptop: " + laptop);

        laptop.normalize();
        System.out.println("Normalized laptop: " + laptop);

        oos.writeObject(laptop);
        oos.flush();

        ois.close();
        oos.close();
    }
}

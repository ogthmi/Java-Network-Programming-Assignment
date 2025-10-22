package exercise.TCP.objectstream;

import TCP.Address;
import executor.TCP.TCPBase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AddressNormalizer extends TCPBase {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public AddressNormalizer() {
        this.setPort(2209);
    }

    @Override
    protected void setStream() throws IOException {
        this.ois = new ObjectInputStream(socket.getInputStream());
        this.oos = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    protected void sendMessage(String message) throws Exception {

    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN541;2rtLo6of";
    }

    @Override
    protected void solve() throws IOException, ClassNotFoundException {
        var address = (Address) ois.readObject();
        System.out.println("Server response: " + address);

        address.normalize();
        System.out.println("After nomalized " + address);

        oos.writeObject(address);
        oos.flush();

        ois.close();
        oos.close();
    }

}

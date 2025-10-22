package exercise.UDP.object;

import UDP.Customer;
import executor.UDP.UDPBase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class CustomerNormalizer extends UDPBase {
    public CustomerNormalizer(InetAddress address, DatagramSocket socket) {
        super(address, socket);
    }

    @Override
    protected int getPort() {
        return 2209;
    }

    @Override
    protected String getCodeRequest() {
        return ";B22DCCN541;ozh4mMF5";
    }

    @Override
    protected void solve() throws Exception {
        byte[] buffer = receive();

        byte[] requestId = Arrays.copyOfRange(buffer, 0, 8);
        byte[] rawObj = Arrays.copyOfRange(buffer, 8, buffer.length);


        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(rawObj));
        Customer customer = (Customer) ois.readObject();
        System.out.println("Customer: " + customer);

        customer.normalize();
        System.out.println("Normalized: " + customer);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(customer);
        oos.flush();

        byte[] objBytes = baos.toByteArray();
        byte[] result = new byte[8 + objBytes.length];
        System.arraycopy(requestId, 0, result, 0, requestId.length);
        System.arraycopy(objBytes, 0, result, 8, objBytes.length);

        sendMessage(result);

        ois.close();
        oos.close();
    }
}

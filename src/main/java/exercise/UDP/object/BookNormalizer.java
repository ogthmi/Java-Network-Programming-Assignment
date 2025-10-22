package exercise.UDP.object;

import UDP.Book;
import executor.UDP.UDPBase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class BookNormalizer extends UDPBase {
    public BookNormalizer(InetAddress address, DatagramSocket socket) {
        super(address, socket);
    }

    @Override
    protected int getPort() {
        return 2209;
    }

    @Override
    protected String getCodeRequest() {
        return ";B22DCCN541;nJfIASzH";
    }

    @Override
    protected void solve() throws Exception {
        byte[] buffer = receive();

        byte[] requestId = Arrays.copyOfRange(buffer, 0, 8);
        byte[] rawObj = Arrays.copyOfRange(buffer, 8, buffer.length);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(rawObj));
        Book book = (Book) ois.readObject();
        System.out.println("Book: " + book);

        book.normalize();
        System.out.println("Normalize: " + book);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(book);
        oos.flush();

        byte[] objResponse = baos.toByteArray();
        byte[] response = new byte[8 + objResponse.length];
        System.arraycopy(requestId, 0, response, 0, requestId.length);
        System.arraycopy(objResponse, 0, response, 8, objResponse.length);

        sendMessage(response);

        ois.close();
        oos.close();
    }
}

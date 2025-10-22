package exercise.UDP.object;

import UDP.Student;
import executor.UDP.UDPBase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class StudentNormalizer extends UDPBase {
    public StudentNormalizer(InetAddress address, DatagramSocket socket) {
        super(address, socket);
    }

    @Override
    protected int getPort() {
        return 2209;
    }

    @Override
    protected String getCodeRequest() {
        return ";B22DCCN541;pbOIBztS";
    }

    @Override
    protected void solve() throws Exception {
        byte[] buffer = receive();

        byte[] requestId = Arrays.copyOfRange(buffer, 0, 8);
        byte[] objectBytes = Arrays.copyOfRange(buffer, 8, buffer.length);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(objectBytes));
        Student student = (Student) ois.readObject();
        System.out.println("Student: " + student);

        student.normalizeName();
        student.setEmail();
        System.out.println("Normalize: " + student);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(student);
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

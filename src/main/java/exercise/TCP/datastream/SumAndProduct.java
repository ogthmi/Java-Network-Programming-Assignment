package exercise.TCP.datastream;

import executor.TCP.TCPBase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SumAndProduct extends TCPBase {
    private DataOutputStream dos;
    private DataInputStream dis;

    public SumAndProduct() {
        this.setPort(2207);
    }

    @Override
    protected void setStream() throws IOException {
        this.dis = new DataInputStream(this.socket.getInputStream());
        this.dos = new DataOutputStream(this.socket.getOutputStream());
    }

    @Override
    protected void sendMessage(String message) throws IOException {
        dos.writeUTF(message);
    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN541;HEyj0YJ0";
    }

    @Override
    protected void solve() throws IOException {
        int a = dis.readInt(), b = dis.readInt();

        System.out.println("a=" + a + " b=" + b);

        int sum = a + b, product = a * b;
        System.out.println("Sum=" + sum + " Product=" + product);

        dos.writeInt(sum);
        dos.writeInt(product);
        System.out.println("Operation result resent.");

        dos.close();
        dis.close();
    }
}

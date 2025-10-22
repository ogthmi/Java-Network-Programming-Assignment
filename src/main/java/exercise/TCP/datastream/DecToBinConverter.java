package exercise.TCP.datastream;

import executor.TCP.TCPBase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DecToBinConverter extends TCPBase {
    private DataInputStream dis;
    private DataOutputStream dos;

    public DecToBinConverter(){
        this.setPort(2207);
    }

    @Override
    protected void setStream() throws IOException {
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    protected void sendMessage(String message) throws IOException {
        dos.writeUTF(message);
    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN541;lJLPtSsa";
    }

    @Override
    protected void solve() throws IOException {
        int dec = dis.readInt();
        System.out.println("Decimal: " + dec);

        String bin = Integer.toBinaryString(dec);
        System.out.println("Binary: " + bin);


        sendMessage(bin);

        dos.close();
        dis.close();
    }
}

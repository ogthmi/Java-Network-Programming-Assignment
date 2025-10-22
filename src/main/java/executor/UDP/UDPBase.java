package executor.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class UDPBase {
    private final DatagramSocket socket;
    private final InetAddress address;
    private int port;

    public UDPBase(InetAddress address, DatagramSocket socket) {
        this.address = address;
        this.socket = socket;
    }

    protected final void setPort() {
        this.port = getPort();
    }

    protected abstract int getPort();

    protected final void sendMessage(byte[] message) throws Exception {
        DatagramPacket dp = new DatagramPacket(message, message.length, address, port);
        socket.send(dp);
    }

    protected abstract String getCodeRequest();

    public final void execute() throws Exception {
        setPort();
        sendMessage(getCodeRequest().getBytes());
        System.out.println("Code request sent.");

        solve();
        System.out.println("Problem solved.");
    }

    protected final byte[] receive() throws Exception {
        byte[] buffer = new byte[1024];

        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp);

        return dp.getData();
    }

    protected abstract void solve() throws Exception;
}

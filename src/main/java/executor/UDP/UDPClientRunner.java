package executor.UDP;

import exercise.UDP.object.BookNormalizer;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientRunner {
    static final String serverIP = "203.162.10.109";

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName(serverIP);
            DatagramSocket socket = new DatagramSocket();

            var th = new BookNormalizer(address, socket);

            th.execute();

            socket.close();
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

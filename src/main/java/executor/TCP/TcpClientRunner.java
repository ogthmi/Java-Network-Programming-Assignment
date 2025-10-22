package executor.TCP;

import exercise.TCP.objectstream.LaptopNormalizer;

import java.net.Socket;

public class TcpClientRunner {
    static final String serverIP = "203.162.10.109";

    public static void main(String[] args) {
        var th = new LaptopNormalizer();

        try {
            Socket socket = new Socket(serverIP, th.getPort());

            th.setSocket(socket);

            th.execute();

            socket.close();
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

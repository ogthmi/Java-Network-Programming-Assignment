package exercise.UDP.datatype;

import executor.UDP.UDPBase;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class SumOfDigits extends UDPBase {
    public SumOfDigits(InetAddress address, DatagramSocket socket) {
        super(address, socket);
    }

    @Override
    protected int getPort() {
        return 2207;
    }

    @Override
    protected String getCodeRequest() {
        return ";B22DCCN541;xOxXtrXg";
    }

    @Override
    protected void solve() throws Exception {
        String serverResponse = new String(receive()).trim();
        System.out.println("Server response: " + serverResponse);

        String[] tokens = serverResponse.split(";");
        String requestId = tokens[0];

        int sum = 0;
        for (char c : tokens[1].toCharArray()) {
            sum += c - '0';
        }

        System.out.println("Sum of digits: " + sum);

        sendMessage((requestId + ";" + sum).getBytes());
    }
}

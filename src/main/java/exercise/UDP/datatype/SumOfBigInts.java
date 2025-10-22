package exercise.UDP.datatype;

import executor.UDP.UDPBase;

import java.math.BigInteger;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SumOfBigInts extends UDPBase {
    public SumOfBigInts(InetAddress address, DatagramSocket socket) {
        super(address, socket);
    }

    @Override
    protected int getPort() {
        return 2207;
    }

    @Override
    protected String getCodeRequest() {
        return ";B22DCCN541;2eBb5tUc";
    }

    @Override
    protected void solve() throws Exception {
        String response = new String(receive()).trim();
        System.out.println("Server response: " + response);

        String[] tokens = response.split(";");
        String requestId = tokens[0];

        BigInteger a = new BigInteger(tokens[1]);
        BigInteger b = new BigInteger(tokens[2]);

        BigInteger sum = a.add(b);
        BigInteger dif = a.subtract(b).abs();

        System.out.println("sum=" + sum + " dif=" + dif);

        String result = requestId + ";" + sum + "," + dif;

        sendMessage(result.getBytes());
    }
}

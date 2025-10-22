package exercise.UDP.datatype;

import executor.UDP.UDPBase;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SecondMaxMinOfList extends UDPBase {
    public SecondMaxMinOfList(InetAddress address, DatagramSocket socket) {
        super(address, socket);
    }

    @Override
    protected int getPort() {
        return 2207;
    }

    @Override
    protected String getCodeRequest() {
        return ";B22DCCN541;aw7KWXDM";
    }

    @Override
    protected void solve() throws Exception {
        String serverResponse = new String(receive()).trim();
        System.out.println("Server response: " + serverResponse);

        String[] tokens = serverResponse.split(";");
        String requestId = tokens[0];

        List<Integer> numbers = Arrays
                .stream(tokens[1].split(","))
                .map(Integer::parseInt)
                .toList();

        int secondMin = numbers
                .stream().sorted().skip(1)
                .findFirst().orElseThrow();

        int secondMax = numbers
                .stream().sorted(Comparator.reverseOrder()).skip(1)
                .findFirst().orElseThrow();

        System.out.println("secondMax=" + secondMax + " secondMin=" + secondMin);

        String result = requestId + ";" + secondMax + "," + secondMin;
        sendMessage(result.getBytes());
    }
}

package exercise.UDP.datatype;

import executor.UDP.UDPBase;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

public class MinMaxOfList extends UDPBase {
    public MinMaxOfList(InetAddress address, DatagramSocket socket) {
        super(address, socket);
    }

    @Override
    protected int getPort() {
        return 2207;
    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN541;cIFzMjqI";
    }

    @Override
    protected void solve() throws Exception {
        String serverResponse = new String(receive()).trim();
        System.out.println("Server response: " + serverResponse);

        String[] tokens = serverResponse.split(";");
        String requestId = tokens[0];

        List<Integer> numbers = Arrays
                .stream(tokens[1].split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        int min = numbers.stream().min(Integer::compareTo).orElse(0);
        int max = numbers.stream().max(Integer::compareTo).orElse(0);

        System.out.println("max=" + max + " min=" + min);

        String result = requestId + ";" + max + "," + min;
        sendMessage(result.getBytes());
    }
}

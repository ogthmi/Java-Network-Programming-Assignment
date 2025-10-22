package exercise.UDP.string;

import executor.UDP.UDPBase;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IgnoreCaseDictionarySorter extends UDPBase {
    public IgnoreCaseDictionarySorter(InetAddress address, DatagramSocket socket) {
        super(address, socket);
    }

    @Override
    protected int getPort() {
        return 2208;
    }

    @Override
    protected String getCodeRequest() {
        return ";B22DCCN541;XAIOHzuy";
    }

    @Override
    protected void solve() throws Exception {
        String serverResponse = new String(receive()).trim();
        System.out.println("Server response: " + serverResponse);

        String[] tokens = serverResponse.split(";");
        String requestId = tokens[0];

        List<String> words = new ArrayList<>(Arrays
                .stream(tokens[1].split("\\s+"))
                .toList()
        );
        words.sort((w1, w2) -> w2.compareToIgnoreCase(w1));

        String sorted = String.join(",", words);
        System.out.println("Sorted: " + sorted);

        sendMessage((requestId + ";" + sorted).getBytes());
    }
}

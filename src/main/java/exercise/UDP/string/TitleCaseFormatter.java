package exercise.UDP.string;

import executor.UDP.UDPBase;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TitleCaseFormatter extends UDPBase {
    public TitleCaseFormatter(InetAddress address, DatagramSocket socket) {
        super(address, socket);
    }

    @Override
    protected int getPort() {
        return 2208;
    }

    @Override
    protected String getCodeRequest() {
        return ";B22DCCN541;l9dFr2MF ";
    }

    @Override
    protected void solve() throws Exception {
        String serverResponse = new String(receive()).trim();
        System.out.println("Server response: " + serverResponse);

        String[] tokens = serverResponse.split(";");
        String requestId = tokens[0];

        String formatted = Arrays.stream(tokens[1].split("\\s+"))
                .map(t ->
                        t.substring(0, 1).toUpperCase() + t.substring(1).toLowerCase()
                )
                .collect(Collectors.joining(" "));

        System.out.println("Title case: " + formatted);

        sendMessage((requestId + ";" + formatted).getBytes());
    }
}

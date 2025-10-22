package exercise.UDP.string;

import executor.UDP.UDPBase;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class UniqueLetterCleaner2 extends UDPBase {
    public UniqueLetterCleaner2(InetAddress address, DatagramSocket socket) {
        super(address, socket);
    }

    @Override
    protected int getPort() {
        return 2208;
    }

    @Override
    protected String getCodeRequest() {
        return ";B22DCCN541;2vHEyVbP";
    }

    @Override
    protected void solve() throws Exception {
        String serverResponse = new String(receive()).trim();
        System.out.println("Server response: " + serverResponse);

        String[] tokens = serverResponse.split(";");
        String requestId = tokens[0];

        StringBuilder builder = new StringBuilder();

        for (int i = 0 ; i < tokens[1].length(); i++){
            String t = tokens[1].substring(i, i + 1);
            if (tokens[2].contains(t)) continue;
            builder.append(t);
        }

        System.out.println("Deduplicated: " + builder);
        sendMessage((requestId + ";" + builder.toString()).getBytes());
    }
}

package exercise.TCP.datastream;

import executor.TCP.TCPBase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SlopeChangeCounter extends TCPBase {
    private DataInputStream dis;
    private DataOutputStream dos;

    public SlopeChangeCounter() {
        this.setPort(2207);
    }

    @Override
    protected void setStream() throws IOException {
        this.dis = new DataInputStream(this.socket.getInputStream());
        this.dos = new DataOutputStream(this.socket.getOutputStream());
    }

    @Override
    protected void sendMessage(String message) throws IOException {
        dos.writeUTF(message);
    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN541;Z9PcvRK7";
    }

    @Override
    protected void solve() throws IOException {
        String serverResponse = dis.readUTF();
        System.out.println("Server response: " + serverResponse);

        List<Integer> numbers = Arrays.stream(serverResponse.split(","))
                .map(Integer::parseInt)
                .toList();

        int totalDiff = 0;
        int cntReversed = 0;
        Boolean isPrevAsc = null;
        for (int i = 1; i < numbers.size(); i++) {
            int cur = numbers.get(i);
            int prev = numbers.get(i - 1);

            int currDiff = cur - prev;
            totalDiff += Math.abs(currDiff);

            boolean isCurrAsc = cur > prev;

            if (isPrevAsc == null) {
                isPrevAsc = isCurrAsc;
                continue;
            }

            if (isCurrAsc != isPrevAsc) {
                isPrevAsc = isCurrAsc;
                cntReversed++;
            }
        }

        String res = cntReversed + "," + totalDiff;
        System.out.println("Slope change counts: " + res);

        dos.writeInt(cntReversed);
        dos.writeInt(totalDiff);

        dos.close();
        dis.close();
    }
}

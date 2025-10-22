package exercise.TCP.bytestream;

import executor.TCP.TCPBase;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class SumBalancedPartition extends TCPBase {
    private InputStream is;
    private OutputStream os;

    public SumBalancedPartition() {
        this.setPort(2206);
    }

    @Override
    protected void setStream() throws IOException {
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }

    @Override
    protected void sendMessage(String message) throws IOException {
        os.write((message + "\n").getBytes());
        os.flush();
    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN541;ta0lH1Za";
    }

    @Override
    protected void solve() throws IOException {
        byte[] buffer = new byte[1024];
        int len = is.read(buffer);

        String serverResponse = new String(buffer, 0, len);
        System.out.println("Server response: " + serverResponse);

        List<Integer> numbers = Arrays
                .stream(serverResponse.split(","))
                .map(Integer::parseInt)
                .toList();

        int sumLeft = 0;
        int sum = numbers.stream().reduce(0, Integer::sum);

        int minDiff = Integer.MAX_VALUE;
        int optIndex = 0, optSumLeft = 0, optSumRight = 0;

        for (int i = 0; i < numbers.size(); i++) {
            int sumRight = sum - sumLeft - numbers.get(i);

            int currentDiff = Math.abs(sumLeft - sumRight);
            if (currentDiff < minDiff) {
                optIndex = i;
                optSumLeft = sumLeft;
                optSumRight = sumRight;
                minDiff = currentDiff;
            }

            sumLeft += numbers.get(i);
        }

        String algoResult = optIndex + "," + optSumLeft + "," + optSumRight + "," + minDiff + "\n";
        System.out.println("Sum balanced:" + algoResult);

        sendMessage(algoResult);

        os.close();
        is.close();
    }
}

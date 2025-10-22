package exercise.TCP.bytestream;

import executor.TCP.TCPBase;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosetPairToAverage extends TCPBase {
    private InputStream is;
    private OutputStream os;

    public ClosetPairToAverage() {
        this.setPort(2206);
    }

    @Override
    protected void setStream() throws IOException {
        this.is = socket.getInputStream();
        this.os = socket.getOutputStream();
    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN541;BDG9TD9a";
    }

    @Override
    protected void sendMessage(String message) throws IOException {
        this.os.write((message + "\n").getBytes());
        this.os.flush();
    }

    @Override
    protected void solve() throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead = is.read(buffer);

        String serverResponse = new String(buffer, 0, bytesRead);
        System.out.println("Server response: " + serverResponse);

        List<Integer> numberList = Arrays.stream(serverResponse.split(","))
                .map(Integer::parseInt)
                .toList();

        ArrayList<Integer> numbers = new ArrayList<>(numberList);
        numbers.sort(Integer::compareTo);

        int sum = numbers.stream().reduce(0, Integer::sum);
        double doubleAvg = 2f * sum / numbers.size();

        double minDiff = Double.MAX_VALUE;
        int optNum1 = 0, optNum2 = 0;

        int left = 0, right = numbers.size() - 1;
        while (left < right) {
            double currSum = numbers.get(left) + numbers.get(right);

            double currDiff = Math.abs(currSum - doubleAvg);
            if (currDiff < minDiff) {
                minDiff = currDiff;
                optNum1 = numbers.get(left);
                optNum2 = numbers.get(right);
            }
            if (currSum < doubleAvg) {
                left++;
            } else {
                right--;
            }
        }

        String result = String.join(",",
                String.valueOf(optNum1), String.valueOf(optNum2)
        );
        System.out.println("Result: " + result);

        sendMessage(result);

        os.close();
        is.close();
    }
}

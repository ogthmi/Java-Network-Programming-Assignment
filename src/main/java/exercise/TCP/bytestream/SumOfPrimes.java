package exercise.TCP.bytestream;

import executor.TCP.TCPBase;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class SumOfPrimes extends TCPBase {
    private InputStream is;
    private OutputStream os;

    public SumOfPrimes() {
        this.setPort(2206);
    }

    @Override
    protected void setStream() throws IOException {
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }

    @Override
    protected void sendMessage(String message) throws IOException {
        this.os.write((message + "\n").getBytes());
        this.os.flush();
    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN541;TktPgCjL ";
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }

        return true;
    }

    @Override
    protected void solve() throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead = is.read(buffer);

        String serverResponse = new String(buffer, 0, bytesRead);
        System.out.println("Server response: " + serverResponse);

        List<Integer> numbers = Arrays.stream(serverResponse.split(","))
                .map(Integer::parseInt)
                .toList();

        int sumOfPrimes = numbers.stream()
                .filter(this::isPrime)
                .reduce(0, Integer::sum);

        System.out.println("Sum of primes: " + sumOfPrimes);
        sendMessage(String.valueOf(sumOfPrimes));

        os.close();
        is.close();
    }
}

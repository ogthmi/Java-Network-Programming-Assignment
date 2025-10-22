package exercise.TCP.bytestream;

import executor.TCP.TCPBase;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class SumOfList extends TCPBase {
    private InputStream is;
    private OutputStream os;

    public SumOfList() {
        this.setPort(2206);
    }

    @Override
    protected void setStream() throws IOException {
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }

    @Override
    protected void sendMessage(String message) throws IOException{
        this.os.write((message + "\n").getBytes());
        this.os.flush();
    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN541;fXkxr7sN ";
    }

    @Override
    protected void solve() throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead = is.read(buffer);

        String serverResponse = new String(buffer, 0, bytesRead);

        int sum = Arrays.stream(serverResponse.split("\\|"))
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);

        System.out.println("Sum=" + sum);

        sendMessage(String.valueOf(sum));

        os.close();
        is.close();
    }
}

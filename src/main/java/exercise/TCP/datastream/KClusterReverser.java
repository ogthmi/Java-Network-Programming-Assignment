package exercise.TCP.datastream;

import executor.TCP.TCPBase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class KClusterReverser extends TCPBase {
    private DataOutputStream dos;
    private DataInputStream dis;

    public KClusterReverser() {
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
        return "B22DCCN541;xkVDB165";
    }

    @Override
    protected void solve() throws IOException {
        int k = dis.readInt();
        String[] numbers = dis.readUTF().split(",");

        System.out.println("Server Response: ");
        System.out.println("k=" + k);
        System.out.println("Array= " + Arrays.toString(numbers));

        for (int i = 0; i < numbers.length; i += k) {
            int left = i;
            int right = Math.min(i + k - 1, numbers.length - 1);

            while (left < right) {
                String temp = numbers[left];
                numbers[left] = numbers[right];
                numbers[right] = temp;

                left++;
                right--;
            }
        }

        String result = String.join(",", numbers);
        System.out.println("Clustered and swapped result: " + result);

        sendMessage(result);

        dos.close();
        dis.close();
    }
}

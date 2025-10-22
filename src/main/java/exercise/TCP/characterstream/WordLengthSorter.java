package exercise.TCP.characterstream;

import executor.TCP.TCPBase;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class WordLengthSorter extends TCPBase {
    private BufferedReader br;
    private BufferedWriter bw;

    public WordLengthSorter() {
        this.setPort(2208);
    }

    @Override
    protected void setStream() throws IOException {
        this.br = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream())
        );
        this.bw = new BufferedWriter(
                new OutputStreamWriter(this.socket.getOutputStream())
        );
    }

    @Override
    protected void sendMessage(String message) throws IOException {
        this.bw.write(message);
        this.bw.newLine();
        this.bw.flush();
    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN541;ThAmmHiU";
    }

    @Override
    protected void solve() throws IOException {
        String serverResponse = br.readLine().trim();
        System.out.println("Server response: " + serverResponse);

        List<String> words = new ArrayList<String>(
                Arrays.stream(serverResponse.split(" "))
                        .toList()
        );

        words.sort(Comparator.comparingInt(String::length));

        String sorted = String.join(", ", words);
        System.out.println("Sorted word: " + sorted);

        sendMessage(sorted);

        br.close();
        bw.close();
    }
}

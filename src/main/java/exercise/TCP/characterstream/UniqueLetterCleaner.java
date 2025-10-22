package exercise.TCP.characterstream;

import executor.TCP.TCPBase;

import java.io.*;
import java.util.LinkedHashMap;

public class UniqueLetterCleaner extends TCPBase {
    private BufferedReader br;
    private BufferedWriter bw;

    public UniqueLetterCleaner() {
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
    protected String getCodeRequest() {
        return "B22DCCN541;tk53i4i1";
    }

    @Override
    protected void sendMessage(String message) throws IOException {
        this.bw.write(message);
        this.bw.newLine();
        this.bw.flush();
    }

    @Override
    protected void solve() throws IOException {
        String serverResponse = this.br.readLine().trim();
        System.out.println("Response: " + serverResponse);

        LinkedHashMap<Character, Integer> freq = new LinkedHashMap<>();

        StringBuilder builder = new StringBuilder();

        for (Character c : serverResponse.toCharArray()) {
            if (!Character.isLetter(c)) continue;

            freq.put(c, freq.getOrDefault(c, 0) + 1);
            if (freq.get(c) > 1) continue;

            builder.append(c);
        }

        System.out.println("String: " + builder);
        sendMessage(builder.toString());

        br.close();
        bw.close();
    }
}

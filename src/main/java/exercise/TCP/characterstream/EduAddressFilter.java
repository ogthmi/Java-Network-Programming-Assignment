package exercise.TCP.characterstream;

import executor.TCP.TCPBase;

import java.io.*;
import java.util.ArrayList;

public class EduAddressFilter extends TCPBase {
    private BufferedReader br;
    private BufferedWriter bw;

    public EduAddressFilter() {
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
        return "B22DCCN541;GmX8LmaC";
    }

    @Override
    protected void solve() throws IOException {
        String serverResponse = br.readLine();
        System.out.println("Server response: " + serverResponse);

        String[] emailList = serverResponse.split(", ");

        ArrayList<String> eduEmail = new ArrayList<>();
        for (String email : emailList) {
            if (email.contains(".edu")) {
                eduEmail.add(email);
            }
        }

        String result = String.join(", ", eduEmail);
        System.out.println("Edu address: " + result);

        sendMessage(result);

        br.close();
        bw.close();
    }
}

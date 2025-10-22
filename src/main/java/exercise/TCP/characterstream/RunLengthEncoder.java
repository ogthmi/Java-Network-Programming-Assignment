package exercise.TCP.characterstream;

import executor.TCP.TCPBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class RunLengthEncoder extends TCPBase {
    private BufferedReader br;
    private BufferedWriter bw;

    public RunLengthEncoder(){
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
    protected void sendMessage(String message) throws IOException{
        this.bw.write(message);
        this.bw.newLine();
        this.bw.flush();
    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN541;zxsTdEfZ";
    }

    
    private String RLE(String word){
        StringBuilder encoded = new StringBuilder();
        
        int count = 1;
        for (int i = 1; i <= word.length(); i++){
            if (i < word.length() && word.charAt(i) == word.charAt(i - 1)){
                count++;
            }
            else {
                encoded.append(word.charAt(i - 1));
                if (count > 1){
                    encoded.append(count);
                }
                count = 1;
            }
        }
        return encoded.toString();
    }

    @Override
    protected void solve() throws IOException{
        String serverResponse = this.br.readLine().trim();
        System.out.println("Server response: " + serverResponse);
        
        String[] words = serverResponse.split(" ");
        
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < words.length; i++){
            String tokenEncoded = RLE(
                    new StringBuilder(words[i]).reverse().toString()
            );
            encoded.append(tokenEncoded);
            if (i < words.length - 1){
                encoded.append(" ");
            }
        }
        
        String result = encoded.toString();
        System.out.println("Encoded: " + result);

        sendMessage(result);

        br.close();
        bw.close();
    }
    
}

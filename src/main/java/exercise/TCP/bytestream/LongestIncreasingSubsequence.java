package exercise.TCP.bytestream;

import executor.TCP.TCPBase;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LongestIncreasingSubsequence extends TCPBase {
    private InputStream is;
    private OutputStream os;

    public LongestIncreasingSubsequence() {
        this.setPort(2206);
    }

    @Override
    protected void setStream() throws IOException {
        this.is = this.socket.getInputStream();
        this.os = this.socket.getOutputStream();
    }

    @Override
    protected String getCodeRequest() {
        return "B22DCCN541;XkNGT3At";
    }

    @Override
    protected void sendMessage(String message) throws IOException {
        os.write((message + "\n").getBytes());
        os.flush();
    }

    @Override
    protected void solve() throws IOException {
        byte[] buffer = new byte[1024];
        int len = is.read(buffer);

        String serverResponse = new String(buffer, 0, len).trim();
        System.out.println(serverResponse);

        List<Integer> nums = Arrays.stream(serverResponse.split(",")).map(Integer::parseInt).toList();

        int n = nums.size();
        int[] dp = new int[n];
        int[] prev = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums.get(j) < nums.get(i) && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }

        int maxLen = 0;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                idx = i;
            }
        }

        List<Integer> lis = new ArrayList<>();
        while (idx != -1) {
            lis.add(nums.get(idx));
            idx = prev[idx];
        }
        Collections.reverse(lis);

        System.out.println("Longest Increasing Subsequence: " + lis);

        String subStr = lis.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        sendMessage(subStr + ";" + lis.size());

        os.close();
        is.close();
    }
}

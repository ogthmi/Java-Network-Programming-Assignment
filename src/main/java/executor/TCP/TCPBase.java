package executor.TCP;

import java.net.Socket;

public abstract class TCPBase {
    protected Socket socket;
    private int port;

    protected final void setSocket(Socket socket) throws Exception {
        this.socket = socket;
        setStream();
    }

    public final int getPort() {
        return port;
    }

    public final void setPort(int port) {
        this.port = port;
    }

    protected abstract void setStream() throws Exception;

    protected abstract void sendMessage(String message) throws Exception;

    protected abstract String getCodeRequest();

    protected abstract void solve() throws Exception;

    public final void execute() throws Exception {
        sendMessage(getCodeRequest());
        System.out.println("Code request sent.");

        solve();
        System.out.println("Problem sent.");
    }

    ;

}

package executor.RMI;

import exercise.RMI.data.NextPermutation;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClientRunner {
    static final String serverIP = "203.162.10.109";
    static final int serverPort = 1099;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(serverIP, serverPort);

            var th = new NextPermutation(registry);
            th.execute();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

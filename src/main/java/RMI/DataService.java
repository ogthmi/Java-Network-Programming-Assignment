package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataService extends Remote {
    String REGISTRY_NAME = "RMIDataService";

    Object requestData(String studentCode, String qCode) throws RemoteException;

    void submitData(String studentCode, String qCode, Object data) throws RemoteException;
}
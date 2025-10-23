package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CharacterService extends Remote {
    String REGISTRY_NAME = "RMICharacterService";

    String requestCharacter(String studentCode, String qCode) throws RemoteException;

    void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
}

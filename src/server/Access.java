package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import client.DataBaseAcces;

public interface Access extends Remote {
	public void executeQuery(DataBaseAcces data) throws RemoteException;
}

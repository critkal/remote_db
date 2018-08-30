package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import client.DataBaseAcces;

/**
 * 
 * @author Pedro Arthur and Gabriel Victor
 *
 *         Remote interface that specifies the methods to be provided from the
 *         server side.
 * 
 */
public interface Access extends Remote {
	public ResultQuery executeQuery(DataBaseAcces data) throws RemoteException;
}

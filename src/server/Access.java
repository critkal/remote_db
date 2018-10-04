package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import client.DataBaseInfo;

/**
 * 
 * @author Pedro Arthur and Gabriel Victor
 *
 *         Remote interface that specifies the methods to be provided from the
 *         server side.
 * 
 */
public interface Access extends Remote {
	public ResultQuery executeQuery(String query) throws RemoteException;

	public void connectDB(DataBaseInfo data) throws RemoteException, SQLException;
}

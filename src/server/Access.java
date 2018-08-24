package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

import client.DataBaseAcces;

public interface Access extends Remote {
	public ResultSet executeQuery(DataBaseAcces data) throws RemoteException;
}

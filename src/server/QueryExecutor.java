package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

import client.DataBaseAcces;

public class QueryExecutor extends UnicastRemoteObject implements Access {

	private static final long serialVersionUID = 1L;

	public QueryExecutor() throws RemoteException {
		super();
	}

	@Override
	public ResultSet executeQuery(DataBaseAcces data) throws RemoteException {
		Statement stmt = null;
		ResultSet result = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(data.getBD_URL(), data.getUser(), data.getPassword());
			stmt = connection.createStatement();
			result = stmt.executeQuery(data.getQuery());
			System.out.println(result);
			return result;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
				result.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return result;
	}

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(1099);
		QueryExecutor qe = new QueryExecutor();
		Naming.rebind("rmi://localhost/queryexecutor", qe);
		System.out.println("Server ready.");
	}

}

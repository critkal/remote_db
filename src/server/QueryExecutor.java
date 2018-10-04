package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

import client.DataBaseInfo;

/**
 * 
 * @author Pedro Arthur and Gabriel Victor
 * 
 *         This class uses the interface to implement the service that performs
 *         operations on the database indicated by the client.
 *
 */
public class QueryExecutor extends UnicastRemoteObject implements Access {

	private static final long serialVersionUID = 1L;

	public QueryExecutor() throws RemoteException {
		super();
	}

	private Connection connection;

	@Override
	public ResultQuery executeQuery(String query) throws RemoteException {

		ResultQuery queryResult = null;
		try (Statement stmt = this.connection.createStatement()) {

			if (!query.toLowerCase().contains("select") /*
														 * || query.toLowerCase().contains("update") ||
														 * query.toLowerCase().contains("delete")
														 */) {

				stmt.executeUpdate(query);
				queryResult = new ResultQuery("Operação bem sucedida.");

			} else {
				ResultSet result = stmt.executeQuery(query);
				queryResult = new ResultQuery(result);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return queryResult;
	}

	@Override
	public void connectDB(DataBaseInfo data) throws RemoteException, SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(data.getBD_URL(), data.getUser(), data.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(1099);
		QueryExecutor qe = new QueryExecutor();
		Naming.rebind("rmi://localhost/queryexecutor", qe);
		System.out.println("Server ready.");
	}

}

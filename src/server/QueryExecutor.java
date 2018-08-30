package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

import client.DataBaseAcces;

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

	@Override
	public ResultQuery executeQuery(DataBaseAcces data) throws RemoteException {

		ResultQuery queryResult = null;
		try (Connection connection = DriverManager.getConnection(data.getBD_URL(), data.getUser(), data.getPassword());
				Statement stmt = connection.createStatement();) {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String query = data.getQuery();

			if (query.toLowerCase().contains("insert") || query.toLowerCase().contains("update")
					|| query.toLowerCase().contains("delete")) {
				try {
					stmt.executeUpdate(query);
					queryResult = new ResultQuery("Operação bem sucedida.");
				} catch (SQLException e) {
					queryResult = new ResultQuery("Houve erro na operação.");
				}

			} else {
				try (ResultSet result = stmt.executeQuery(data.getQuery())) {
					queryResult = new ResultQuery(result);
				} catch (SQLException e) {
					queryResult = new ResultQuery("Houve erro na consulta.");
				}

			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		}
		return queryResult;
	}

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(1099);
		QueryExecutor qe = new QueryExecutor();
		Naming.rebind("rmi://localhost/queryexecutor", qe);
		System.out.println("Server ready.");
	}

}

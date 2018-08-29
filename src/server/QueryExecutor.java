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
	public void executeQuery(DataBaseAcces data) throws RemoteException {
		Statement stmt = null;
		ResultSet result = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(data.getBD_URL(), data.getUser(), data.getPassword());
			stmt = connection.createStatement();
			String query = data.getQuery();
			if(query.toLowerCase().contains("insert") || query.toLowerCase().contains("update")||query.toLowerCase().contains("delete")) {
				stmt.executeUpdate(query);
				System.out.println("Sucesso");
			}
			else {
				result = stmt.executeQuery(data.getQuery());
				
				ResultSetMetaData rsmd = result.getMetaData();
				int columnsNumber = rsmd.getColumnCount();

				while (result.next()) {
					for (int i = 1; i <= columnsNumber; i++) {
						if (i > 1)
							System.out.print(",  ");
						String columnValue = result.getString(i);
						System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
					}
					System.out.println("");
				}
				
				data.setResult(result);
				System.out.println("S");
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
				if (result != null) {
					result.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(1099);
		QueryExecutor qe = new QueryExecutor();
		Naming.rebind("rmi://localhost/queryexecutor", qe);
		System.out.println("Server ready.");
	}

}

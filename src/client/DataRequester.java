package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

import server.Access;

public class DataRequester {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String url, user, password, query;

		try {
			Access stub = (Access) Naming.lookup("rmi://localhost/queryexecutor");
			Scanner sc = new Scanner(System.in);
			System.out.println("Insira a url do banco de dados: ");
			url = sc.nextLine();
			System.out.println("\nInsira o nome do usu�rio: ");
			user = sc.nextLine();
			System.out.println("\n Insira senha do usu�rio: ");
			password = sc.nextLine();
			System.out.println("\n Insira a query a ser executada: ");
			query = sc.nextLine();
			sc.close();
			ResultSet result = stub.executeQuery(new DataBaseAcces(url, user, password, query));
			
			
			ResultSetMetaData rsmd = result.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			
			while (result.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = result.getString(i);
			        System.out.print(columnValue + " " + rsmd.getColumnName(i));
			    }
			    System.out.println("");
			}
			
		} catch (Exception e) {
			System.out.println("Client Exception: " + e.toString());
			e.printStackTrace();
		}
	}

}

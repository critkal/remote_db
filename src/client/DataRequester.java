package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import server.Access;
import server.ResultQuery;

/**
 * 
 * @author Pedro Arthur and Gabriel Victor
 * 
 *         Client implementation that calls remote methods available on the
 *         server.
 *
 */
public class DataRequester {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String url, user, password, query;

		try (Scanner sc = new Scanner(System.in)) {
			Access stub = (Access) Naming.lookup("rmi://localhost/queryexecutor");

			System.out.println("Insira a url do banco de dados: ");
			url = sc.nextLine();
			System.out.println("\nInsira o nome do usuario: ");
			user = sc.nextLine();
			System.out.println("\nInsira senha do usuario: ");
			password = sc.nextLine();
			System.out.println("\nInsira a query a ser executada: ");
			query = sc.nextLine();

			ResultQuery resultQuery;
			DataBaseAcces db = new DataBaseAcces(url, user, password, query);
			resultQuery = stub.executeQuery(db);

			System.out.println(resultQuery);

		} catch (Exception e) {
			System.out.println("Client Exception: " + e.toString());
			e.printStackTrace();
		}
	}

}

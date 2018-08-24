package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
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
			System.out.println("\nInsira o nome do usuário: ");
			user = sc.nextLine();
			System.out.println("\n Insira senha do usuário: ");
			password = sc.nextLine();
			System.out.println("\n Insira a query a ser executada: ");
			query = sc.nextLine();
			sc.close();
			ResultSet result = stub.executeQuery(new DataBaseAcces(url, user, password, query));
			System.out.println("resultado: " + result);
		} catch (Exception e) {
			System.out.println("Client Exception: " + e.toString());
			e.printStackTrace();
		}
	}

}

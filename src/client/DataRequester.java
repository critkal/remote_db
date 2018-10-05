package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
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
	public static void main(String[] args)
			throws MalformedURLException, RemoteException, NotBoundException, InterruptedException {
		String url, user, password, query;
		while (true) {
			try (Scanner sc = new Scanner(System.in)) {
				Access stub = (Access) Naming.lookup("rmi://localhost/queryexecutor");

				System.out.println("Insira a url do banco de dados: ");

				url = sc.nextLine();

				System.out.println("\nInsira o nome do usuario: ");
				user = sc.nextLine();
				System.out.println("\nInsira senha do usuario: ");
				password = sc.nextLine();

				DataBaseInfo db = new DataBaseInfo(url, user, password);
				stub.connectDB(db);
				while (true) {
					System.out.println("\nInsira a query a ser executada:");
					query = sc.nextLine();

					try {
						ResultQuery result = stub.executeQuery(query);
						System.out.println("\nResultado:");
						System.out.println(result);

					} catch (SQLException e) {
						System.out.println(e.getMessage() + " Deseja tentar novamente? s/n");
						if (sc.nextLine().toLowerCase().contains("s")) {
							continue;
						} else {
							break;
						}
					}

					System.out.println("\nDeseja continuar a executar querys nesse banco de dados? s/n");
					if (sc.nextLine().toLowerCase().contains("n")) {
						break;
					}
				}
				System.out.println("Encerrando sistema");

			} catch (Exception e) {
				System.out.println(e.getMessage());
				sy
				Thread.currentThread().sleep(2000); // 1 segundo
				continue;
			}
		}
	}

}

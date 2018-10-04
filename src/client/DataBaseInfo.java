package client;

import java.io.Serializable;

/**
 * 
 * @author Pedro Arthur and Gabriel Victor
 *
 *         Class representing the object sent from the client to the server
 *         containing the data needed to access the database.
 */
public class DataBaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String BD_URL;
	private String user;
	private String password;

	public DataBaseInfo(String bd_URL, String user, String password) {
		super();
		this.BD_URL = bd_URL;
		this.user = user;
		this.password = password;
	}

	public String getBD_URL() {
		return BD_URL;
	}

	public void setBD_URL(String bd_URL) {
		BD_URL = bd_URL;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

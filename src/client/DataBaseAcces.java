package client;

import java.io.Serializable;

public class DataBaseAcces implements Serializable {

	private static final long serialVersionUID = 1L;

	private String BD_URL;
	private String user;
	private String password;
	private String query;

	public DataBaseAcces(String bD_URL, String user, String password, String query) {
		super();
		BD_URL = bD_URL;
		this.user = user;
		this.password = password;
		this.query = query;
	}

	public String getBD_URL() {
		return BD_URL;
	}

	public void setBD_URL(String bD_URL) {
		BD_URL = bD_URL;
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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}

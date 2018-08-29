package server;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ResultQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private ArrayList<HashMap<String, String>> result;

	public ResultQuery(String message) {
		this.message = message;
		this.result = null;
	}

	public ResultQuery(ResultSet resultSet) throws SQLException {
		this.result = new ArrayList<>();

		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		while (resultSet.next()) {
			HashMap<String, String> resultMap = new HashMap<>();
			for (int i = 1; i <= columnsNumber; i++) {
				String columnValue = resultSet.getString(i);
				resultMap.put(rsmd.getColumnName(i), columnValue);
			}
			this.result.add(resultMap);
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<HashMap<String, String>> getResult() {
		return result;
	}

	public void setResult(ArrayList<HashMap<String, String>> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		if (this.message != null) {
			return this.message;
		} else {
			return this.result.toString();
		}
	}

}

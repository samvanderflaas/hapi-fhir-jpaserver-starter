package ca.uhn.fhir.jpa.starter.interceptors;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class CustomDataSource extends HikariDataSource {

	private final ThreadLocal<String> schemaContext = new ThreadLocal<>();

	public void setSchema(String schema) {
		schemaContext.set(schema);
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = super.getConnection();
		String schema = schemaContext.get();
		if (schema != null) {
			connection.setSchema(schema); // Set schema dynamically
		}
		return connection;
	}
}

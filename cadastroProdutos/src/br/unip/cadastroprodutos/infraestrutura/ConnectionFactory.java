package br.unip.cadastroprodutos.infraestrutura;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class ConnectionFactory {
	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String HOST = "DESKTOP-FERNAND";
	private static final String USER = "sa";
	private static final String PASSWORD = "123";
	private static final SQLServerDataSource DS = new SQLServerDataSource();

	private ConnectionFactory() {}
	
	public static Connection conexaoSQLServer() {
		try {
	        DS.setUser(USER);
	        DS.setPassword(PASSWORD);
	        DS.setServerName(HOST);
	        DS.setDatabaseName("CadastroProdutos");
			return DS.getConnection();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Conexão banco de dados Sql Server");
		}
		return null;
	}
}

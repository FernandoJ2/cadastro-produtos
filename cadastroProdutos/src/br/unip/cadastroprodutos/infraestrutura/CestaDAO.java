package br.unip.cadastroprodutos.infraestrutura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import br.unip.cadastroprodutos.negocio.Cesta;

public class CestaDAO {
	private static final Logger LOGGER = Logger.getLogger(CestaDAO.class.getName());
	private static final String INSERT_CESTA = "insert into Cesta (data, codCliente, qtd, numItens, total, idPedido) values(?,?,?,?,?,?)";

	public int adicionar(Cesta obj) {
		String[] idRetornado = { "id" };

		try (Connection conexao = ConnectionFactory.conexaoSQLServer();
				PreparedStatement pstmt = conexao.prepareStatement(INSERT_CESTA, idRetornado);) {
			pstmt.setDate(1, obj.getData());
			pstmt.setInt(2, obj.getCliente().getCodCliente());
			pstmt.setInt(3, obj.getQtd());
			pstmt.setInt(4, obj.getNumItens());
			pstmt.setDouble(5, obj.getTotal());
			pstmt.setInt(6, obj.getPedido().getId());

			if (pstmt.executeUpdate() == 0) {
				LOGGER.info("Insert falhou, nenhuma linha afetada.");
				return 0;
			}
			pstmt.getGeneratedKeys();

			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			LOGGER.info("Erro na query SQL");
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}
		return 0;
	}
}

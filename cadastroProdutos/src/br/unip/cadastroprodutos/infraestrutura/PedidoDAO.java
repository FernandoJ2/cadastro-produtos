package br.unip.cadastroprodutos.infraestrutura;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import br.unip.cadastroprodutos.negocio.Pedido;

public class PedidoDAO {
	private static final Logger LOGGER = Logger.getLogger(PedidoDAO.class.getName());
	private static final String INSERT_PEDIDO = "insert into Pedido (ir, frete, enderecoEntrega, totalPedido) values(?,?,?,?) ";

	public int adicionar(Pedido obj) {
		String[] idRetornado = { "id" };

		try (Connection conexao = ConnectionFactory.conexaoSQLServer();
				PreparedStatement pstmt = conexao.prepareStatement(INSERT_PEDIDO, idRetornado);) {
			pstmt.setDouble(1, obj.getIr());
			pstmt.setDouble(2, obj.getFrete());
			pstmt.setString(3, obj.getEnderecoEntrega());
			pstmt.setDouble(4, obj.getTotalPedido());

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

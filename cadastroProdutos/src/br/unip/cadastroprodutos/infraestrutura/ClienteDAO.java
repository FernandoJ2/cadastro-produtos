package br.unip.cadastroprodutos.infraestrutura;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import br.unip.cadastroprodutos.negocio.Cliente;

public class ClienteDAO {
	private static final Logger LOGGER = Logger.getLogger(ClienteDAO.class.getName());
	private static final String INSERT_CLIENTE = "insert into Cliente (dtNascimento, nome, sexo) values(?,?,?) ";
	private static final String SELECT_CLIENTES = "select codCliente, dtNascimento, nome, sexo from Cliente";
	
	public int adicionar(Cliente obj) {
		String[] idRetornado = { "id" };

		try (Connection conexao = ConnectionFactory.conexaoSQLServer(); PreparedStatement pstmt = conexao.prepareStatement(INSERT_CLIENTE, idRetornado);) {
			pstmt.setDate(1, (Date) obj.getDtNascimento());
			pstmt.setString(2, obj.getNome());
			pstmt.setString(3, obj.getSexo());

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
		}catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}
		return 0;
	}
	
	public List<Cliente> obterTodos() {
		try (Connection conexao = ConnectionFactory.conexaoSQLServer(); PreparedStatement pstmt = conexao.prepareStatement(SELECT_CLIENTES);) {
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Cliente> clientes = new ArrayList<>();
				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getInt("codCliente"));
					cliente.setDtNascimento(rs.getDate("dtNascimento"));
					cliente.setNome(rs.getString("nome"));
					cliente.setSexo(rs.getString("sexo"));
					clientes.add(cliente);
				}
				return clientes;
			}
		} catch (SQLException e) {
			LOGGER.info("Erro na query obter estação por id.");
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}
		return Collections.emptyList();
	}
}

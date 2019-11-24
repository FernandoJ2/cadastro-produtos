package br.unip.cadastroprodutos.infraestrutura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import br.unip.cadastroprodutos.negocio.Produto;

public class ProdutoDAO {
	private static final Logger LOGGER = Logger.getLogger(ProdutoDAO.class.getName());
	private static final String INSERT_PRODUTO = "insert into Produto (valorProd, descricao) values(?,?) ";
	private static final String SELECT_PRODUTOS = "select codProduto, valorProd, descricao from Produto";
	
	public int adicionar(Produto obj) {
		String[] idRetornado = { "id" };

		try (Connection conexao = ConnectionFactory.conexaoSQLServer(); PreparedStatement pstmt = conexao.prepareStatement(INSERT_PRODUTO, idRetornado);) {
			pstmt.setDouble(1, obj.getValorProd());
			pstmt.setString(2, obj.getDescricao());

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
	
	public List<Produto> obterTodos() {
		try (Connection conexao = ConnectionFactory.conexaoSQLServer(); PreparedStatement pstmt = conexao.prepareStatement(SELECT_PRODUTOS);) {
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Produto> produtos = new ArrayList<>();
				while (rs.next()) {
					Produto produto = new Produto();
					produto.setCodProduto(rs.getInt("codProduto"));
					produto.setValorProd(rs.getDouble("valorProd"));
					produto.setDescricao(rs.getString("descricao"));
					produtos.add(produto);
				}
				return produtos;
			}
		} catch (SQLException e) {
			LOGGER.info("Erro na query obter estação por id.");
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}
		return Collections.emptyList();
	}
}

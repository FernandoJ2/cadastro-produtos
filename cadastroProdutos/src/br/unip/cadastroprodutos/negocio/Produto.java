package br.unip.cadastroprodutos.negocio;

public class Produto {
	private int codProduto;
	private double valorProd;
	private String descricao;

	public int getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}

	public double getValorProd() {
		return valorProd;
	}

	public void setValorProd(double valorProd) {
		this.valorProd = valorProd;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}

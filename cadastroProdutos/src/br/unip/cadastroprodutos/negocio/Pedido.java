package br.unip.cadastroprodutos.negocio;

public class Pedido {
	private double ir;
	private double frete;
	private double totalPedido;
	private String enderecoEntraga;

	public double getIr() {
		return ir;
	}

	public void setIr(double ir) {
		this.ir = ir;
	}

	public double getFrete() {
		return frete;
	}

	public void setFrete(double frete) {
		this.frete = frete;
	}

	public double getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(double totalPedido) {
		this.totalPedido = totalPedido;
	}

	public String getEnderecoEntraga() {
		return enderecoEntraga;
	}

	public void setEnderecoEntraga(String enderecoEntraga) {
		this.enderecoEntraga = enderecoEntraga;
	}
}
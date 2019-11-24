package br.unip.cadastroprodutos.negocio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Cesta {
	private Date data;
	private Cliente cliente;
	private List<Produto> item = new ArrayList<>();
	private int qtd;
	private int numItens;
	private double total;
	private Pedido pedido;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getItem() {
		return item;
	}

	public void setItem(List<Produto> item) {
		this.item = item;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public int getNumItens() {
		return numItens;
	}

	public void setNumItens(int numItens) {
		this.numItens = numItens;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}

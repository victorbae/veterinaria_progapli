package br.com.unoesc.veterinaria.model;

import java.util.Date;

public class Venda {

	Integer idvenda;
	Date dataVenda;
	Cliente cliente;
	// Filial filial;
	Produto produto;
	Double valorDesconto;
	Double valorTotal;

	public Venda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Venda(Integer idvenda, Date dataVenda, Cliente cliente, Double valorDesconto, Double valorTotal) {
		super();
		this.idvenda = idvenda;
		this.dataVenda = dataVenda;
		this.cliente = cliente;
		this.valorDesconto = valorDesconto;
		this.valorTotal = valorTotal;
	}

	public Integer getIdvenda() {
		return idvenda;
	}

	public void setIdvenda(Integer idvenda) {
		this.idvenda = idvenda;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idvenda == null) ? 0 : idvenda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (idvenda == null) {
			if (other.idvenda != null)
				return false;
		} else if (!idvenda.equals(other.idvenda))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Venda: " + idvenda + " Data:" + dataVenda + " Cliente" + cliente + " Valor Total:" + valorTotal;
	}

}

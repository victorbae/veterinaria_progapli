package br.com.unoesc.veterinaria.model;

import java.util.Date;
import java.util.List;

public class Venda {

	private Integer idVenda;
	private Date dataVenda;
	private Cliente cliente;
	private Filial filial;
	private Double valorDesconto;
	private Double valorTotal;
	private List<VendaProduto> carrinho;

	public Venda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Venda(Integer idVenda, Date dataVenda, Cliente cliente, Double valorDesconto, Double valorTotal) {
		super();
		this.idVenda = idVenda;
		this.dataVenda = dataVenda;
		this.cliente = cliente;
		this.valorDesconto = valorDesconto;
		this.valorTotal = valorTotal;
	}

	public Integer getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
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

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
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

	public List<VendaProduto> getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(List<VendaProduto> carrinho) {
		this.carrinho = carrinho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVenda == null) ? 0 : idVenda.hashCode());
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
		if (idVenda == null) {
			if (other.idVenda != null)
				return false;
		} else if (!idVenda.equals(other.idVenda))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Venda: " + idVenda + " Data:" + dataVenda + " Cliente" + cliente + " Valor Total:" + valorTotal;
	}

}

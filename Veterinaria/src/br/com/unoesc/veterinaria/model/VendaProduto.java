package br.com.unoesc.veterinaria.model;

public class VendaProduto {

	private Integer IdVendaProduto;
	private Venda venda;
	private Produto produto;
	private Double quantidade;
	private Double valorUnitario;
	private Double valorTotal;

	public VendaProduto() {
	}

	public VendaProduto(Integer idVendaProduto, Venda venda, Produto produto, Double quantidade, Double valorUnitario,
			Double valorTotal) {
		super();
		IdVendaProduto = idVendaProduto;
		this.venda = venda;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.setValorTotal(valorTotal);
	}

	public Integer getIdVendaProduto() {
		return IdVendaProduto;
	}

	public void setIdVendaProduto(Integer idVendaProduto) {
		IdVendaProduto = idVendaProduto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double calculaValorTotal(Double qnt, Double valorUnitario) {
		return qnt * valorUnitario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IdVendaProduto == null) ? 0 : IdVendaProduto.hashCode());
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
		VendaProduto other = (VendaProduto) obj;
		if (IdVendaProduto == null) {
			if (other.IdVendaProduto != null)
				return false;
		} else if (!IdVendaProduto.equals(other.IdVendaProduto))
			return false;
		return true;
	}

}

package br.com.unoesc.veterinaria.model.filtros;

import java.time.LocalDate;

import br.com.unoesc.veterinaria.model.Cliente;

public class FiltrosVenda {

	private Cliente cliente;
	private LocalDate dataVenda;
	private Double condicaoValor;
	private String operacao;

	public FiltrosVenda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FiltrosVenda(Cliente cliente, LocalDate dataVenda, Double condicaoValor, String operacao) {
		super();
		this.cliente = cliente;
		this.dataVenda = dataVenda;
		this.condicaoValor = condicaoValor;
		this.operacao = operacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Double getCondicaoValor() {
		return condicaoValor;
	}

	public void setCondicaoValor(Double condicaoValor) {
		this.condicaoValor = condicaoValor;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

}

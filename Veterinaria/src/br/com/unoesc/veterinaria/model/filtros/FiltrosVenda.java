package br.com.unoesc.veterinaria.model.filtros;

import java.time.LocalDate;

import br.com.unoesc.veterinaria.model.Cliente;

public class FiltrosVenda {

	private Cliente cliente;
	private LocalDate dataVenda;
	private String tipoCondicaoValor;

	public FiltrosVenda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FiltrosVenda(Cliente cliente, LocalDate dataVenda, String tipoCondicaoValor) {
		super();
		this.cliente = cliente;
		this.dataVenda = dataVenda;
		this.tipoCondicaoValor = tipoCondicaoValor;
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

	public String getTipoCondicaoValor() {
		return tipoCondicaoValor;
	}

	public void setTipoCondicaoValor(String tipoCondicaoValor) {
		this.tipoCondicaoValor = tipoCondicaoValor;
	}

}

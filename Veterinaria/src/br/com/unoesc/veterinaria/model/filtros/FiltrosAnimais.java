package br.com.unoesc.veterinaria.model.filtros;

import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.TipoAnimal;

public class FiltrosAnimais {

	private Cliente cliente;
	private Raca raca;
	private TipoAnimal tipoAnimal;

	public FiltrosAnimais() {
	}

	public FiltrosAnimais(Cliente cliente, Raca raca, TipoAnimal tipoAnimal) {
		super();
		this.cliente = cliente;
		this.raca = raca;
		this.tipoAnimal = tipoAnimal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public TipoAnimal getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(TipoAnimal tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

}

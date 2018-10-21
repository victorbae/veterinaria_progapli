package br.com.unoesc.veterinaria.controller;

import java.util.Date;

import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Venda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class ControllerVenda {

	@FXML
	private TableView<Venda> tvVendas;

	@FXML
	private TableColumn<Venda, Integer> tcId;

	@FXML
	private TableColumn<Venda, Cliente> tcCliente;

	@FXML
	private TableColumn<Venda, Filial> tcFilial;

	@FXML
	private TableColumn<Venda, Double> tcValorDesconto;

	@FXML
	private TableColumn<Venda, Date> tcDataVenda;

	@FXML
	private TableColumn<Venda, Double> tcValorTotal;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnVisualizar;

	@FXML
	private BorderPane bpPrincipal;

	private PaginaInicialController pagInicialController;

	@FXML
	void Visualizar(ActionEvent event) {
	}

	@FXML
	void Excluir(ActionEvent event) {

	}

	@FXML
	void Novo(ActionEvent event) {
	}

}

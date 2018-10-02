package br.com.unoesc.veterinaria.controller;

import br.com.unoesc.veterinaria.model.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerProduto {

	@FXML
	private TableView<Produto> tvProdutos;

	@FXML
	private TableColumn<Produto, Integer> tcId;

	@FXML
	private TableColumn<Produto, String> tcNome;

	@FXML
	private TableColumn<Produto, Double> tcQuantidade;

	@FXML
	private TableColumn<Produto, Double> tcValorEntrada;

	@FXML
	private TableColumn<Produto, Double> tcMargemLucro;

	@FXML
	private TableColumn<Produto, Integer> tcIdEstoque;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnEditar;

	@FXML
	void Editar(ActionEvent event) {

	}

	@FXML
	void Excluir(ActionEvent event) {

	}

	@FXML
	void Novo(ActionEvent event) {

	}
}

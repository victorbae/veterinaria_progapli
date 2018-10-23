package br.com.unoesc.veterinaria.controller;

import java.util.Date;

import br.com.unoesc.veterinaria.banco.FuncionarioBanco;
import br.com.unoesc.veterinaria.dao.FuncionarioDao;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Funcionario;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerFuncionario {

	@FXML
	private TableColumn<Funcionario, String> tcNome;

	@FXML
	private TableColumn<Funcionario, Cliente> tcNomeCliente;

	@FXML
	private Button btExcluir;

	@FXML
	private TableView<Funcionario> tvFuncionarios;

	@FXML
	private Button btSalvar;

	@FXML
	private TableColumn<Funcionario, String> tcCPF;

	@FXML
	private TableColumn<Funcionario, Integer> tcId;

	@FXML
	private TableColumn<Funcionario, Date> tcDtNascimento;

	@FXML
	private TableColumn<Funcionario, Filial> tcNomeFilial;

	@FXML
	private Button btNovo;

	FuncionarioDao funcionariodao = new FuncionarioBanco();

	@FXML
	private void initialize() {
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcId.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
		tcCPF.setCellValueFactory(new PropertyValueFactory<>("Cpf"));
		tcNomeFilial.setCellValueFactory(new PropertyValueFactory<>("filial"));
		tcNomeCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		tvFuncionarios.setItems(FXCollections.observableArrayList(funcionariodao.listar()));
	}

	@FXML
	void salvar(ActionEvent event) {

	}

	@FXML
	void excluir(ActionEvent event) {

	}

	@FXML
	void novo(ActionEvent event) {

	}

}

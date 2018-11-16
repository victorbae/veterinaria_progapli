package br.com.unoesc.veterinaria.controller;

import java.time.LocalDate;

import br.com.unoesc.veterinaria.banco.FuncionarioBanco;
import br.com.unoesc.veterinaria.dao.FuncionarioDao;
import br.com.unoesc.veterinaria.dialogs.FuncionarioDialogFactory;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Funcionario;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeFuncionario;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaFilial;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerFuncionario {

	@FXML
	private TableColumn<Funcionario, String> tcNome;

	@FXML
	private TableColumn<Funcionario, String> tcNomeCliente;

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
	private TableColumn<Funcionario, LocalDate> tcDtNascimento;

	@FXML
	private TableColumn<Funcionario, Filial> tcNomeFilial;

	@FXML
	private Button btNovo;

	FuncionarioDao funcionariodao = new FuncionarioBanco();

	Funcionario funcionario = new Funcionario();

	boolean escolhido = false;

	@FXML
	private void initialize() {
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcDtNascimento.setCellValueFactory(new PropertyValueFactory<>("data_Nascimento"));
		tcCPF.setCellValueFactory(new PropertyValueFactory<>("Cpf"));
		tcNomeFilial.setCellValueFactory(new PropertyValueFactory<>("filial"));
		tcNomeCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		tvFuncionarios.setItems(FXCollections.observableArrayList(funcionariodao.listarSomenteParaFilialLogada()));
	}

	@FXML
	void salvar(ActionEvent event) {
		preencheFuncionario();
		if (escolhido) {
			EstaticosDeFuncionario.editando = true;
			EstaticosDeFuncionario.funcionario = this.funcionario;
			dialogFunc();
			escolhido = false;
			EstaticosDeFuncionario.editando = false;
			EstaticosDeFuncionario.funcionario = new Funcionario();
			;
			initialize();
		}
	}

	@FXML
	void excluir(ActionEvent event) {
		preencheFuncionario();
		if (escolhido) {
			funcionariodao.excluir(this.funcionario);
			escolhido = false;
			initialize();
		}
	}

	@FXML
	void novo(ActionEvent event) {
		dialogFunc();
		initialize();
	}

	void preencheFuncionario() {
		this.funcionario = tvFuncionarios.getSelectionModel().getSelectedItem();
		this.escolhido = true;
	}

	void dialogFunc() {
		Stage stageDono = (Stage) btNovo.getScene().getWindow();
		FuncionarioDialogFactory funcionarioDialog = new FuncionarioDialogFactory(stageDono);
		boolean clicadoSalvar = funcionarioDialog.showDialog();
		if (clicadoSalvar) {
			this.funcionario.getCliente().setIdCliente(EstaticosParaFilial.cliente.getIdCliente());
			initialize();
		}
	}
}
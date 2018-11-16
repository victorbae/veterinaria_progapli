package br.com.unoesc.veterinaria.controller;

import java.util.Date;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dialogs.ClienteDialogFactory;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaCliente;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerCliente {

	@FXML
	private TableView<Cliente> tvCliente;

	@FXML
	private TableColumn<Cliente, String> tcNomeCompleto;

	@FXML
	private TableColumn<Cliente, String> tcCpf;

	@FXML
	private TableColumn<Cliente, Date> tcDataNascimento;

	@FXML
	private TableColumn<Cliente, String> tcEndereco;

	@FXML
	private TableColumn<Cliente, String> tcTelefone;

	@FXML
	private TableColumn<Cliente, Filial> tcFilial;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnEditar;

	private Cliente cliente;

	private ClienteDao clienteDao = new ClienteBanco();

	@FXML
	private void initialize() {
		tcNomeCompleto.setCellValueFactory(new PropertyValueFactory<>("nomeCompleto"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tcDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
		tcFilial.setCellValueFactory(new PropertyValueFactory<>("filial"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		tvCliente.setItems(FXCollections.observableArrayList(clienteDao.listarSomenteParaFilialLogada()));
	}

	@FXML
	void Editar(ActionEvent event) {
		if (tvCliente.getSelectionModel().getSelectedItem() != null) {
			EstaticosParaCliente.cliente = tvCliente.getSelectionModel().getSelectedItem();
			EstaticosParaCliente.isEditando = true;
		}
		Stage stageDono = (Stage) btnNovo.getScene().getWindow();
		ClienteDialogFactory clienteDialog = new ClienteDialogFactory(stageDono);

		boolean clicadoSalvar = clienteDialog.showDialog();

		if (clicadoSalvar) {
			atualizaLista();
		}
	}

	@FXML
	void Excluir(ActionEvent event) {
		if (tvCliente.getSelectionModel().getSelectedItem() != null) {
			cliente = tvCliente.getSelectionModel().getSelectedItem();
			clienteDao.excluir(cliente);
		}
		atualizaLista();
	}

	@FXML
	void Novo(ActionEvent event) {
		Stage stageDono = (Stage) btnNovo.getScene().getWindow();
		ClienteDialogFactory clienteDialog = new ClienteDialogFactory(stageDono);

		boolean clicadoSalvar = clienteDialog.showDialog();

		if (clicadoSalvar) {
			atualizaLista();
		}
	}

	public void atualizaLista() {
		tvCliente.setItems(FXCollections.observableArrayList(clienteDao.listar()));
		tvCliente.refresh();
	}

}

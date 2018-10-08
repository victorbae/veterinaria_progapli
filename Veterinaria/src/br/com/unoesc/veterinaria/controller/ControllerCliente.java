package br.com.unoesc.veterinaria.controller;

import java.util.Date;

import br.com.unoesc.veterinaria.dialogs.ClienteDialogFactory;
import br.com.unoesc.veterinaria.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControllerCliente {

	@FXML
	private TableView<Cliente> tvCliente;

	@FXML
	private TableColumn<Cliente, Integer> tcIdCliente;

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

//	@FXML
//	private TableColumn<Cliente, Filial> tcFilial;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnEditar;

	@FXML
	void Editar(ActionEvent event) {
		Stage stageDono = (Stage) btnNovo.getScene().getWindow();
		ClienteDialogFactory clienteDialog = new ClienteDialogFactory(stageDono);

		boolean clicadoSalvar = clienteDialog.showDialog();

		if (clicadoSalvar) {
			atualizaLista();
		}
	}

	@FXML
	void Excluir(ActionEvent event) {

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

	}

}

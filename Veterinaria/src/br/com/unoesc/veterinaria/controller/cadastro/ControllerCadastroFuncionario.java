package br.com.unoesc.veterinaria.controller.cadastro;

import br.com.unoesc.veterinaria.dialogs.ClienteDialogFactory;
import br.com.unoesc.veterinaria.dialogs.ClienteDialogFactoryRapid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCadastroFuncionario {

	@FXML
	private Button btnSalvar;

	@FXML
	private DatePicker dtDataNascimento;

	@FXML
	private TextField tfCpf;

	@FXML
	private ComboBox<?> cbxFilial;

	@FXML
	private Button btnCancelar;

	@FXML
	private ComboBox<?> cbxCliente;

	@FXML
	private Hyperlink ChamaTelaAddCliente;

	@FXML
	private Button btnLimpar;

	@FXML
	private TextField tfNome;

	@FXML
	void Cancelar(ActionEvent event) {

	}

	@FXML
	void Salvar(ActionEvent event) {

	}

	@FXML
	void Limpar(ActionEvent event) {

	}

	@FXML
	void ChamaTelaAddClienteRapid(ActionEvent event) {
		Stage stageDono = (Stage) ChamaTelaAddCliente.getScene().getWindow();
		ClienteDialogFactoryRapid clienteDialog = new ClienteDialogFactoryRapid(stageDono);
		clienteDialog.showDialog();
	}
}
package br.com.unoesc.veterinaria.controller.cadastro;

import java.sql.Date;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.banco.FuncionarioBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.FilialDao;
import br.com.unoesc.veterinaria.dao.FuncionarioDao;
import br.com.unoesc.veterinaria.dialogs.ClienteDialogFactoryRapid;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Funcionario;
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
	private ComboBox<Filial> cbxFilial;

	@FXML
	private Button btnCancelar;

	@FXML
	private ComboBox<Cliente> cbxCliente;

	@FXML
	private Hyperlink ChamaTelaAddCliente;

	@FXML
	private Button btnLimpar;

	@FXML
	private TextField tfNome;

	private Stage dialogStage;

	private boolean clicadoSalvar;

	Funcionario funcionario = new Funcionario();

	FuncionarioDao funcionariodao = new FuncionarioBanco();

	FilialDao filialdao = new FilialBanco();

	ClienteDao clientedao = new ClienteBanco();

	@FXML
	private void initialize() {
		populaComboCliente();
		populaComboFilial();
	}

	@FXML
	void Cancelar(ActionEvent event) {

	}

	@FXML
	void Salvar(ActionEvent event) {
		preencheFuncionario();
		funcionariodao.inserir(funcionario);

		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}
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

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}

	void preencheFuncionario() {
		funcionario = new Funcionario();
		funcionario.setNome(String.valueOf(tfNome.getText()));
		funcionario.setCpf(String.valueOf(tfCpf.getText()));
		funcionario.setCliente(cbxCliente.getValue());
		funcionario.setFilial(cbxFilial.getValue());
		funcionario.setData_Nascimento(Date.valueOf(dtDataNascimento.getValue()));
	}

	private void populaComboFilial() {
		for (Filial filial : filialdao.listar()) {
			cbxFilial.getItems().add(filial);
		}
	}

	private void populaComboCliente() {
		for (Cliente cliente : clientedao.listar()) {
			cbxCliente.getItems().add(cliente);
		}
	}
}
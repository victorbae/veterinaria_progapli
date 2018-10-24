package br.com.unoesc.veterinaria.controller.cadastro;

import java.sql.Date;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.FilialDao;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCadastroCliente {

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfCpf;

	@FXML
	private DatePicker dtDataNascimento;

	@FXML
	private TextField tfTelefone;

	@FXML
	private TextField tfEndereco;

	@FXML
	private ComboBox<Filial> cbxFilial;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnLimpar;

	private Cliente cliente;

	private Stage dialogStage;

	private boolean clicadoSalvar;

	private ClienteDao clienteDao = new ClienteBanco();

	private FilialDao filialDao = new FilialBanco();

	@FXML
	private void initialize() {
		populaCombo();
	}

	@FXML
	void Cancelar(ActionEvent event) {

	}

	@FXML
	void Limpar(ActionEvent event) {
		limpaTela();
	}

	@FXML
	void Salvar(ActionEvent event) {
		preencheCliente();

		clienteDao.inserir(cliente);

		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}

	}

	public void preencheCliente() {
		cliente = new Cliente();
		cliente.setNomeCompleto(tfNome.getText());
		cliente.setCpf(tfCpf.getText());
		cliente.setEndereco(tfEndereco.getText());
		cliente.setDataNascimento(Date.valueOf(dtDataNascimento.getValue()));
		cliente.setTelefone(tfTelefone.getText());
		cliente.setFilial(EstaticosParaCliente.achaFilial(cbxFilial.getValue().getIdFilial()));
	}

	public void limpaTela() {
		tfNome.clear();

		tfCpf.clear();

		tfTelefone.clear();

		tfEndereco.clear();

		cbxFilial.getSelectionModel().clearSelection();
	}

	private void populaCombo() {
		for (Filial filial : filialDao.listar()) {
			cbxFilial.getItems().add(filial);
		}
	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}
}

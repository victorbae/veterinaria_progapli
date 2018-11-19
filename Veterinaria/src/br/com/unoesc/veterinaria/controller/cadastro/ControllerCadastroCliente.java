package br.com.unoesc.veterinaria.controller.cadastro;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.FilialDao;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaCliente;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaFilial;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCadastroCliente {

	@FXML
	private Label lblMaster;

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
		this.cliente = EstaticosParaCliente.cliente;
		populaCombo();

		if (EstaticosParaCliente.isEditando) {
			populaTela();
			lblMaster.setText("Editando Cliente");
		}
	}

	@FXML
	void Cancelar(ActionEvent event) {
		limpaTela();
		dialogStage.close();
	}

	@FXML
	void Limpar(ActionEvent event) {
		limpaTela();
	}

	@FXML
	void Salvar(ActionEvent event) {
		try {
			preencheCliente();

			if (EstaticosParaCliente.isEditando) {
				clienteDao.alterar(cliente);
				EstaticosParaCliente.isEditando = false;
			} else {
				clienteDao.inserir(cliente);
			}
			clicadoSalvar = true;
			if (dialogStage != null) {
				dialogStage.close();
			}
		} catch (Exception e) {
			EstaticosParaGeral.chamaErroNaoPreenchido(dialogStage);
		}
	}

	public void populaTela() {
		tfNome.setText(cliente.getNomeCompleto());
		tfCpf.setText(cliente.getCpf());
		dtDataNascimento.setValue(cliente.getDataNascimento());
		tfTelefone.setText(cliente.getTelefone());
		tfEndereco.setText(cliente.getEndereco());
		cbxFilial.setValue(cliente.getFilial());
	}

	public void preencheCliente() {
		if (!EstaticosParaCliente.isEditando) {
			cliente = new Cliente();
		} else {
			cliente.setIdCliente(EstaticosParaCliente.cliente.getIdCliente());

		}
		cliente.setNomeCompleto(tfNome.getText());
		cliente.setCpf(tfCpf.getText());
		cliente.setEndereco(tfEndereco.getText());
		cliente.setDataNascimento(dtDataNascimento.getValue());
		cliente.setTelefone(tfTelefone.getText());
		cliente.setFilial(EstaticosParaFilial.achaFilial(cbxFilial.getValue().getIdFilial()));
	}

	public void limpaTela() {
		if (!EstaticosParaCliente.isEditando) {
			tfNome.clear();
			tfCpf.clear();
		}
		tfTelefone.clear();
		tfEndereco.clear();
		cbxFilial.getSelectionModel().clearSelection();
		dtDataNascimento.setValue(null);
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

	public Cliente getCliente() {
		return this.cliente;
	}
}

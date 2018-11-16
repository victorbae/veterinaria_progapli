package br.com.unoesc.veterinaria.controller.cadastro;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.banco.FuncionarioBanco;
import br.com.unoesc.veterinaria.dao.FilialDao;
import br.com.unoesc.veterinaria.dao.FuncionarioDao;
import br.com.unoesc.veterinaria.dialogs.FuncionarioDialogFactory;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Funcionario;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaFilial;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class ControllerCadastroFilial {

	@FXML
	private TextField tfTelefone;

	@FXML
	private Label tfTitulo;

	@FXML
	private TextField tfCapEstoque;

	@FXML
	private ComboBox<Funcionario> cbxGerente;

	@FXML
	private TextField tfEndereco;

	@FXML
	private Button btnSalvarbt;

	@FXML
	private TextField tfCNPJ;

	@FXML
	private TextField tfNome;

	private Stage dialogStage;

	private boolean clicadoSalvar;

	Filial filial = new Filial();

	FilialDao filialdao = new FilialBanco();

	FuncionarioDao funcionariodao = new FuncionarioBanco();

	@FXML
	void initialize() {
		if (EstaticosParaFilial.editando) {
			tfTitulo.setText("Alterando Filial");
			this.filial = EstaticosParaFilial.filial;
			preencheTela();
		}
		populaCombo();
	}

	@FXML
	void btnSalvar(ActionEvent event) {
		preencheFilial();
		if (EstaticosParaFilial.editando) {
			filialdao.alterar(filial);
		} else {
			filialdao.inserir(filial);
		}
		clicadoSalvar = true;
		dialogStage.close();

	}

	@FXML
	void btnLimpar(ActionEvent event) {
		limparTela();
	}

	@FXML
	void btnCancelar(ActionEvent event) {
		EstaticosParaFilial.filial = new Filial();
		EstaticosParaFilial.editando = false;
		dialogStage.close();
	}

	@FXML
	void novoGerente(ActionEvent event) {
		dialogFunc();
	}

	void preencheFilial() {
		this.filial.setCapacidadeEstoque(Integer.valueOf(tfCapEstoque.getText()));
		this.filial.setCnpj(tfCNPJ.getText());
		this.filial.setEndereco(tfEndereco.getText());
		this.filial.setGerente(cbxGerente.getValue());
		this.filial.setNome(tfNome.getText());
		this.filial.setTelefone(tfTelefone.getText());
	}

	void preencheTela() {
		this.filial = EstaticosParaFilial.filial;
		tfCapEstoque.setText(String.valueOf(filial.getCapacidadeEstoque()));
		tfCNPJ.setText(filial.getCnpj());
		tfEndereco.setText(filial.getEndereco());
		tfNome.setText(filial.getNome());
		tfTelefone.setText(filial.getTelefone());
		cbxGerente.setValue(filial.getGerente());
	}

	void limparTela() {
		tfCapEstoque.setText("");
		tfCNPJ.setText("");
		tfEndereco.setText("");
		tfNome.setText("");
		tfTelefone.setText("");
		cbxGerente.setValue(null);
	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}

	void dialogFunc() {
		Stage stageDono = (Stage) btnSalvarbt.getScene().getWindow();
		FuncionarioDialogFactory funcionarioDialog = new FuncionarioDialogFactory(stageDono);
		boolean clicadoSalvar = funcionarioDialog.showDialog();
		if (clicadoSalvar) {
			cbxGerente.setValue(EstaticosParaFilial.funcionario);

		}
	}

	private void populaCombo() {
		for (Funcionario funcionario : funcionariodao.listar()) {
			cbxGerente.getItems().add(funcionario);
		}
	}
}

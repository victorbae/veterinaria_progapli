package br.com.unoesc.veterinaria.controller.cadastro;

import java.sql.Date;
import java.time.LocalDate;

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
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeFuncionario;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaCliente;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaFilial;
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
		this.funcionario = EstaticosDeFuncionario.funcionario;
		if (EstaticosDeFuncionario.editando) {
			preencheTela();
		}
	}

	@FXML
	void Cancelar(ActionEvent event) {
		dialogStage.close();
	}

	@FXML
	void Salvar(ActionEvent event) {
		preencheFuncionario();
		if (EstaticosDeFuncionario.editando) {
			funcionariodao.alterar(funcionario);
			EstaticosDeFuncionario.editando = false;
			EstaticosDeFuncionario.funcionario = new Funcionario();
			dialogStage.close();
		} else {
			funcionariodao.inserir(funcionario);
			EstaticosParaFilial.funcionario = this.funcionario;
			dialogStage.close();
		}
		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}
	}

	@FXML
	void Limpar(ActionEvent event) {
		this.funcionario = new Funcionario();
		initialize();

	}

	@FXML
	void ChamaTelaAddClienteRapid(ActionEvent event) {
		Stage stageDono = (Stage) ChamaTelaAddCliente.getScene().getWindow();
		ClienteDialogFactoryRapid clienteDialog = new ClienteDialogFactoryRapid(stageDono);
		clienteDialog.showDialog();
		
		
		cbxCliente.setValue(EstaticosParaCliente.cliente);
		EstaticosParaCliente.cliente = new Cliente();
		EstaticosParaFilial.cliente = clienteDialog.retornaCliente();
	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}

	void preencheFuncionario() {
		funcionario = new Funcionario();
		funcionario.setIdFuncionario(EstaticosDeFuncionario.funcionario.getIdFuncionario());
		funcionario.setNome(String.valueOf(tfNome.getText()));
		funcionario.setCpf(String.valueOf(tfCpf.getText()));
		funcionario.setCliente(cbxCliente.getValue());
		funcionario.setFilial(cbxFilial.getValue());
		funcionario.setData_Nascimento(dtDataNascimento.getValue());
	}

	void preencheTela() {
		tfNome.setText(String.valueOf(this.funcionario.getNome()));
		tfCpf.setText(String.valueOf(this.funcionario.getCpf()));
		cbxCliente.setValue(EstaticosParaCliente.achaCliente(this.funcionario.getCliente().getIdCliente()));
		cbxFilial.setValue(this.funcionario.getFilial());
		dtDataNascimento.setValue(LocalDate.parse(String.valueOf(this.funcionario.getData_Nascimento())));
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
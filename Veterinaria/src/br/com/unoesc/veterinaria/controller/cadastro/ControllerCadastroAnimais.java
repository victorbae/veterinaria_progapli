package br.com.unoesc.veterinaria.controller.cadastro;

import java.io.IOException;

import br.com.unoesc.veterinaria.banco.AnimaisBanco;
import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.banco.Tipo_AnimalBanco;
import br.com.unoesc.veterinaria.dao.AnimaisDao;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.dao.Tipo_AnimalDao;
import br.com.unoesc.veterinaria.dialogs.RacaDialogFactory;
import br.com.unoesc.veterinaria.model.Animais;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.Tipo_Animal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaAnimal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCadastroAnimais {
	@FXML
	private TextField tfNome;

	@FXML
	private DatePicker dtDataNascimento;

	@FXML
	private ComboBox<Raca> cbxRaca;

	@FXML
	private ComboBox<Cliente> cbxCliente;

	@FXML
	private ComboBox<Tipo_Animal> cbxTipoAnimal;

	@FXML
	private Button btnOutraRaca;

	@FXML
	private Button btnOutroTipoAnimal;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnCancelar;

	private Animais animais;

	private Stage dialogStage;

	private boolean clicadoSalvar;
	private AnimaisDao animaisDao = new AnimaisBanco();
	private ClienteDao clienteDao = new ClienteBanco();
	private Tipo_AnimalDao tipoAnimalDao = new Tipo_AnimalBanco();
	private RacaDao racaDao = new RacaBanco();

	@FXML
	private void initialize() {
		this.animais = EstaticosParaAnimal.animal;
		populaCombo();

		if (EstaticosParaAnimal.isEditando) {
			populaTela();
		}

		populaCombo();
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
		preencheAnimais();
		if (EstaticosParaAnimal.isEditando) {
			animaisDao.alterar(animais);
			EstaticosParaCliente.isEditando = false;
		} else {
			animaisDao.inserir(animais);
		}
		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}
	}

	@FXML
	void OutraRaca(ActionEvent event) {
		Stage stageDono = (Stage) btnOutraRaca.getScene().getWindow();
		RacaDialogFactory adicionaProdutoVendaDialog = new RacaDialogFactory(stageDono);

		boolean clicadoSalvar = adicionaProdutoVendaDialog.showDialog();

		if (clicadoSalvar) {
			dialogStage.close();

		}
	}

	public void populaTela() {
		tfNome.setText(animais.getNome());
		dtDataNascimento.setValue(animais.getData_Nascimento());
		cbxRaca.setValue(animais.getRaca());
		cbxCliente.setValue(animais.getCliente());
		cbxTipoAnimal.setValue(animais.getTipo_animal());
	}

	@FXML
	void OutroTipoAnimal(ActionEvent event) {
		try {

			Parent root = FXMLLoader
					.load(getClass().getResource("/br/com/unoesc/veterinaria/fxml/cadastro/CadastroTipoAnimal.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 600, 600);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void preencheAnimais() {
		animais = new Animais();
		animais.setNome(tfNome.getText());
		animais.setData_Nascimento(dtDataNascimento.getValue());
		animais.setRaca(EstaticosParaAnimal.achaRaca(cbxRaca.getValue().getIdRaca()));
		animais.setCliente(EstaticosParaAnimal.achaCliente(cbxCliente.getValue().getIdCliente()));
		animais.setTipo_animal(EstaticosParaAnimal.achaTipoAnimal(cbxTipoAnimal.getValue().getIdTipoAnimal()));
	}

	public void limpaTela() {
		tfNome.clear();
		dtDataNascimento.setValue(null);
		cbxRaca.getSelectionModel().clearSelection();
		cbxCliente.getSelectionModel().clearSelection();
		cbxTipoAnimal.getSelectionModel().clearSelection();

	}

	private void populaCombo() {
		for (Cliente cliente : clienteDao.listar()) {
			cbxCliente.getItems().add(cliente);
		}
		for (Raca raca : racaDao.listar()) {
			cbxRaca.getItems().add(raca);
		}
		for (Tipo_Animal tipoAnimal : tipoAnimalDao.listar()) {
			cbxTipoAnimal.getItems().add(tipoAnimal);
		}

	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}

}

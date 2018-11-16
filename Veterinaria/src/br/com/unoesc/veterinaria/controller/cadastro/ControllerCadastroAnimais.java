package br.com.unoesc.veterinaria.controller.cadastro;

import org.controlsfx.control.textfield.TextFields;

import br.com.unoesc.veterinaria.banco.AnimaisBanco;
import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.banco.TipoAnimalBanco;
import br.com.unoesc.veterinaria.dao.AnimaisDao;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.dao.TipoAnimalDao;
import br.com.unoesc.veterinaria.dialogs.RacaDialogFactory;
import br.com.unoesc.veterinaria.dialogs.TipoAnimalDialogFactory;
import br.com.unoesc.veterinaria.model.Animais;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaAnimal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaCliente;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaRaca;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaTipoAnimal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerCadastroAnimais {

	@FXML
	private TextField tfNome;

	@FXML
	private DatePicker dtDataNascimento;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnOutraRaca;

	@FXML
	private Button btnOutroTipoAnimal;

	@FXML
	private TextField tfRaca;

	@FXML
	private TextField tfTipoAnimal;

	@FXML
	private TextField tfCliente;

	private Tooltip tooltipRaca = new Tooltip();
	private Tooltip tooltipTipoAnimal = new Tooltip();

	private Animais animais;

	private Stage dialogStage;

	private boolean clicadoSalvar;
	private AnimaisDao animaisDao = new AnimaisBanco();
	private ClienteDao clienteDao = new ClienteBanco();
	private TipoAnimalDao tipoAnimalDao = new TipoAnimalBanco();
	private RacaDao racaDao = new RacaBanco();

	@FXML
	private void initialize() {
		this.animais = EstaticosParaAnimal.animal;
		tfRaca.setDisable(true);
		TextFields.bindAutoCompletion(tfCliente, clienteDao.listar());
		TextFields.bindAutoCompletion(tfTipoAnimal, tipoAnimalDao.listar());

		tooltipRaca.setShowDelay(Duration.millis(230d));
		tooltipRaca.setText("Adicionar nova raça.");
		btnOutraRaca.setTooltip(tooltipRaca);

		tooltipTipoAnimal.setShowDelay(Duration.millis(230d));
		tooltipTipoAnimal.setText("Adicionar novo tipo animal.");
		btnOutroTipoAnimal.setTooltip(tooltipTipoAnimal);

		if (EstaticosParaAnimal.isEditando) {
			populaTela();
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
		preencheAnimais();

		if (EstaticosParaAnimal.isEditando) {
			animaisDao.alterar(animais);
			EstaticosParaAnimal.isEditando = false;
		} else {
			animaisDao.inserir(animais);
		}
		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}
	}

	@FXML
	void carregaRaca(ActionEvent event) {
		tfRaca.setDisable(false);
		TextFields.bindAutoCompletion(tfRaca,
				racaDao.findByTipoAnimal(EstaticosParaTipoAnimal.achaTipoAnimalByNome(tfTipoAnimal.getText())));
	}

	public void populaTela() {
		tfNome.setText(animais.getNome());
		dtDataNascimento.setValue(animais.getData_Nascimento());
		tfTipoAnimal.setText(animais.getTipo_animal().getNome());
		tfRaca.setText(animais.getRaca().getNome());
		tfCliente.setText(animais.getCliente().getNomeCompleto());
	}

	@FXML
	void OutraRaca(ActionEvent event) {
		Stage stageDono = (Stage) btnOutraRaca.getScene().getWindow();
		RacaDialogFactory adicionaRacaDialog = new RacaDialogFactory(stageDono);

		boolean clicadoSalvar = adicionaRacaDialog.showDialog();

		if (clicadoSalvar) {
			dialogStage.close();

		}
	}

	@FXML
	void OutroTipoAnimal(ActionEvent event) {
		Stage stageDono = (Stage) btnOutroTipoAnimal.getScene().getWindow();
		TipoAnimalDialogFactory adicionaTipoAnimalDialog = new TipoAnimalDialogFactory(stageDono);

		boolean clicadoSalvar = adicionaTipoAnimalDialog.showDialog();

		if (clicadoSalvar) {
			dialogStage.close();

		}
	}

	public void preencheAnimais() {
		animais = new Animais();
		animais.setNome(tfNome.getText());
		animais.setData_Nascimento(dtDataNascimento.getValue());
		animais.setTipo_animal(EstaticosParaTipoAnimal.achaTipoAnimalByNome(tfTipoAnimal.getText()));
		animais.setRaca(EstaticosParaRaca.achaRacaByNome(tfRaca.getText()));
		animais.setCliente(EstaticosParaCliente.achaClienteByName(tfCliente.getText()));
	}

	public void limpaTela() {
		tfNome.clear();
		dtDataNascimento.setValue(null);
		tfRaca.clear();
		tfCliente.clear();
		tfTipoAnimal.clear();

	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}

}

package br.com.unoesc.veterinaria.controller;

import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.banco.TipoAnimalBanco;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.dao.TipoAnimalDao;
import br.com.unoesc.veterinaria.dialogs.RacaDialogFactory;
import br.com.unoesc.veterinaria.dialogs.TipoAnimalDialogFactory;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.TipoAnimal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaRaca;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaTipoAnimal;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerTipoAnimalRaca {

	@FXML
	private TableView<TipoAnimal> tvTipoAnimal;

	@FXML
	private TableColumn<TipoAnimal, String> tcNome;

	@FXML
	private TableColumn<TipoAnimal, Raca> tcRaca;

	@FXML
	private Button btnNovoTipoAnimal;

	@FXML
	private Button btnExcluirTipoAnimal;

	@FXML
	private Button btnEditarTipoAnimal;

	@FXML
	private TableView<Raca> tvRaca;

	@FXML
	private TableColumn<Raca, String> tcNomeRaca;

	@FXML
	private Button btnNovaRaca;

	@FXML
	private Button btnEditarRaca;

	@FXML
	private Button btnExcluirRaca;

	Raca raca;
	TipoAnimal tipoAnimal;
	private Stage dialogStage;
	private boolean clicadoSalvar;
	RacaDao racaDao = new RacaBanco();
	TipoAnimalDao tipoAnimalDao = new TipoAnimalBanco();

	@FXML
	private void initialize() {
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcNomeRaca.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcRaca.setCellValueFactory(new PropertyValueFactory<>("raca"));
		tvTipoAnimal.setItems(FXCollections.observableArrayList(tipoAnimalDao.listar()));
		tvRaca.setItems(FXCollections.observableArrayList(racaDao.listar()));
		atualizaListaRaca();
		atualizaListaTipoAnimal();
	}

	@FXML
	void EditarRaca(ActionEvent event) {
		if (tvRaca.getSelectionModel().getSelectedItem() != null) {
			EstaticosParaRaca.raca = tvRaca.getSelectionModel().getSelectedItem();
			EstaticosParaRaca.isEditando = true;
		}
		Stage stageDono = (Stage) btnEditarRaca.getScene().getWindow();
		RacaDialogFactory racaDialog = new RacaDialogFactory(stageDono);

		boolean clicadoSalvar = racaDialog.showDialog();

		if (clicadoSalvar) {
			atualizaListaRaca();
		}
	}

	@FXML
	void EditarTipoAnimal(ActionEvent event) {
		if (tvTipoAnimal.getSelectionModel().getSelectedItem() != null) {
			EstaticosParaTipoAnimal.tipoAnimal = tvTipoAnimal.getSelectionModel().getSelectedItem();
			EstaticosParaTipoAnimal.isEditando = true;
		}
		Stage stageDono = (Stage) btnEditarTipoAnimal.getScene().getWindow();
		TipoAnimalDialogFactory tipoAnimalDialog = new TipoAnimalDialogFactory(stageDono);

		boolean clicadoSalvar = tipoAnimalDialog.showDialog();

		if (clicadoSalvar) {
			atualizaListaTipoAnimal();
		}
	}

	@FXML
	void ExcluirRaca(ActionEvent event) {
		if (tvRaca.getSelectionModel().getSelectedItem() != null) {
			raca = tvRaca.getSelectionModel().getSelectedItem();
			racaDao.excluir(raca);
		}
		atualizaListaRaca();
	}

	@FXML
	void ExcluirTipoAnimal(ActionEvent event) {
		if (tvTipoAnimal.getSelectionModel().getSelectedItem() != null) {
			tipoAnimal = tvTipoAnimal.getSelectionModel().getSelectedItem();
			tipoAnimalDao.excluir(tipoAnimal);
		}
		atualizaListaTipoAnimal();
	}

	@FXML
	void NovaRaca(ActionEvent event) {

		Stage stageDono = (Stage) btnNovaRaca.getScene().getWindow();
		RacaDialogFactory adicionaRacaDialog = new RacaDialogFactory(stageDono);

		boolean clicadoSalvar = adicionaRacaDialog.showDialog();

		if (clicadoSalvar) {
			dialogStage.close();
			atualizaListaRaca();
		}

	}

	@FXML
	void NovoTipoAnimal(ActionEvent event) {

		Stage stageDono = (Stage) btnNovoTipoAnimal.getScene().getWindow();
		TipoAnimalDialogFactory adicionaTipoAnimalDialog = new TipoAnimalDialogFactory(stageDono);

		boolean clicadoSalvar = adicionaTipoAnimalDialog.showDialog();

		if (clicadoSalvar) {
			dialogStage.close();
			atualizaListaTipoAnimal();
		}

	}

	public void atualizaListaTipoAnimal() {
		tvTipoAnimal.setItems(FXCollections.observableArrayList(tipoAnimalDao.listar()));
		tvTipoAnimal.refresh();

	}

	public void atualizaListaRaca() {

		tvRaca.setItems(FXCollections.observableArrayList(racaDao.listar()));
		tvRaca.refresh();
	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}
}
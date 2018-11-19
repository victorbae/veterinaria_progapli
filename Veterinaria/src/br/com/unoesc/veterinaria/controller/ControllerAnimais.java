package br.com.unoesc.veterinaria.controller;

import java.io.IOException;

import br.com.unoesc.veterinaria.banco.AnimaisBanco;
import br.com.unoesc.veterinaria.dao.AnimaisDao;
import br.com.unoesc.veterinaria.dialogs.AnimaisDialogFactory;
import br.com.unoesc.veterinaria.dialogs.RelatorioAnimalDialogFactory;
import br.com.unoesc.veterinaria.model.Animais;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.TipoAnimal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeAcesso;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaAnimal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerAnimais {
	@FXML
	private TableView<Animais> tvAnimais;

	@FXML
	private TableColumn<Animais, String> tcNome;

	@FXML
	private TableColumn<Animais, DatePicker> tcDataNascimento;

	@FXML
	private TableColumn<Animais, Raca> tcRaca;

	@FXML
	private TableColumn<Animais, Cliente> tcCliente;

	@FXML
	private TableColumn<Animais, TipoAnimal> tcTipo_Animal;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnTipoAnimal;

	@FXML
	private Button btnRaca;

	@FXML
	private Button btnExibeRelatorio;

	Animais animais;

	AnimaisDao animaisDao = new AnimaisBanco();

	@FXML
	private void initialize() {
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcDataNascimento.setCellValueFactory(new PropertyValueFactory<>("data_Nascimento"));
		tcRaca.setCellValueFactory(new PropertyValueFactory<>("raca"));
		tcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		tcTipo_Animal.setCellValueFactory(new PropertyValueFactory<>("tipo_animal"));
		tvAnimais.setItems(FXCollections.observableArrayList(animaisDao.listar()));
	}

	@FXML
	void Editar(ActionEvent event) {
		if (tvAnimais.getSelectionModel().getSelectedItem() != null) {
			EstaticosParaAnimal.animal = tvAnimais.getSelectionModel().getSelectedItem();
			EstaticosParaAnimal.isEditando = true;
		}
		Stage stageDono = (Stage) btnEditar.getScene().getWindow();
		AnimaisDialogFactory animaisDialog = new AnimaisDialogFactory(stageDono);

		boolean clicadoSalvar = animaisDialog.showDialog();

		if (clicadoSalvar) {
			atualizaLista();
		}
	}

	@FXML
	void Excluir(ActionEvent event) {
		if (tvAnimais.getSelectionModel().getSelectedItem() != null) {
			animais = tvAnimais.getSelectionModel().getSelectedItem();
			animaisDao.excluir(animais);
		}
		atualizaLista();
	}

	@FXML
	void Novo(ActionEvent event) {
		EstaticosParaAnimal.isEditando = false;
		Stage stageDono = (Stage) btnNovo.getScene().getWindow();
		AnimaisDialogFactory animaisDialog = new AnimaisDialogFactory(stageDono);

		boolean clicadoSalvar = animaisDialog.showDialog();

		if (clicadoSalvar) {
			atualizaLista();
		}
	}

	@FXML
	void tipoAnimal(ActionEvent event) {
		if (EstaticosDeAcesso.isLogado()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/TipoAnimal.fxml"));
			try {
				AnchorPane cursoView = (AnchorPane) loader.load();
				EstaticosParaGeral.bpPrincipalAux.setCenter(cursoView);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@FXML
	void raca(ActionEvent event) {
		if (EstaticosDeAcesso.isLogado()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/Raca.fxml"));
			try {
				AnchorPane cursoView = (AnchorPane) loader.load();
				EstaticosParaGeral.bpPrincipalAux.setCenter(cursoView);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@FXML
	void exibeRelatorio(ActionEvent event) {
		Stage stageDono = (Stage) btnExibeRelatorio.getScene().getWindow();
		RelatorioAnimalDialogFactory animalDialog = new RelatorioAnimalDialogFactory(stageDono);

		boolean clicadoSalvar = animalDialog.showDialog();

		if (clicadoSalvar) {

		}
	}

	public void atualizaLista() {
		tvAnimais.setItems(FXCollections.observableArrayList(animaisDao.listar()));
		tvAnimais.refresh();
	}
}

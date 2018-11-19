package br.com.unoesc.veterinaria.controller;

import java.io.IOException;
import java.net.URL;

import br.com.unoesc.veterinaria.banco.TipoAnimalBanco;
import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.TipoAnimalDao;
import br.com.unoesc.veterinaria.dialogs.TipoAnimalDialogFactory;
import br.com.unoesc.veterinaria.model.TipoAnimal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeAcesso;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaTipoAnimal;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ControllerTipoAnimal {

	@FXML
	private TableView<TipoAnimal> tvTipoAnimal;

	@FXML
	private TableColumn<TipoAnimal, String> tcNome;

	@FXML
	private TableColumn<TipoAnimal, Integer> tcQntAnimais;

	@FXML
	private Button btnExibeRelatorio;

	@FXML
	private Button btNovo;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btEditar;

	@FXML
	private Button btVoltar;

	private TipoAnimal tipoAnimal;

	private TipoAnimalDao tipoAnimalDao = new TipoAnimalBanco();

	@FXML
	private void initialize() {
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcQntAnimais.setCellValueFactory(new PropertyValueFactory<>("qntAnimais"));
		tvTipoAnimal.setItems(FXCollections.observableArrayList(tipoAnimalDao.listar()));
		atualizaListaTipoAnimal();
	}

	@FXML
	void editar(ActionEvent event) {
		if (tvTipoAnimal.getSelectionModel().getSelectedItem() != null) {
			EstaticosParaTipoAnimal.tipoAnimal = tvTipoAnimal.getSelectionModel().getSelectedItem();
			EstaticosParaTipoAnimal.isEditando = true;
		}
		Stage stageDono = (Stage) btEditar.getScene().getWindow();
		TipoAnimalDialogFactory tipoAnimalDialog = new TipoAnimalDialogFactory(stageDono);

		boolean clicadoSalvar = tipoAnimalDialog.showDialog();

		if (clicadoSalvar) {
			atualizaListaTipoAnimal();
		}
	}

	@FXML
	void excluir(ActionEvent event) {
		if (tvTipoAnimal.getSelectionModel().getSelectedItem() != null) {
			tipoAnimal = tvTipoAnimal.getSelectionModel().getSelectedItem();
			tipoAnimalDao.excluir(tipoAnimal);
		}
		atualizaListaTipoAnimal();
	}

	@FXML
	void novo(ActionEvent event) {
		EstaticosParaTipoAnimal.isEditando = false;
		Stage stageDono = (Stage) btNovo.getScene().getWindow();
		TipoAnimalDialogFactory adicionaTipoAnimalDialog = new TipoAnimalDialogFactory(stageDono);

		boolean clicadoSalvar = adicionaTipoAnimalDialog.showDialog();

		if (clicadoSalvar) {
			atualizaListaTipoAnimal();
		}

	}

	@FXML
	void exibeRelatorio(ActionEvent event) {
		URL url = getClass().getResource("/br/com/unoesc/veterinaria/relatorios/RelatorioTipoAnimal.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null,
					ConexaoPrincipal.retornaconecao());
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void voltar(ActionEvent event) {
		if (EstaticosDeAcesso.isLogado()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/Animais.fxml"));
			try {
				AnchorPane cursoView = (AnchorPane) loader.load();
				EstaticosParaGeral.bpPrincipalAux.setCenter(cursoView);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void atualizaListaTipoAnimal() {
		tvTipoAnimal.setItems(FXCollections.observableArrayList(tipoAnimalDao.listar()));
		tvTipoAnimal.refresh();

	}

}
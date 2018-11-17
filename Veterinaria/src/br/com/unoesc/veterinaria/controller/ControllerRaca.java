package br.com.unoesc.veterinaria.controller;

import java.io.IOException;
import java.net.URL;

import br.com.unoesc.veterinaria.banco.AnimaisBanco;
import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.AnimaisDao;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.dialogs.RacaDialogFactory;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.TipoAnimal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeAcesso;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaRaca;
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

public class ControllerRaca {
	@FXML
	private TableView<Raca> tvRaca;

	@FXML
	private TableColumn<Raca, String> tcNome;

	@FXML
	private TableColumn<Raca, TipoAnimal> tcTipoAnimal;

	@FXML
	private Button btnExibeRelatorio;

	@FXML
	private Button btEditar;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btNovo;

	@FXML
	private Button btVoltar;

	private boolean clicadoSalvar;

	private Raca raca;
	private RacaDao racaDao = new RacaBanco();
	private AnimaisDao animalDao = new AnimaisBanco();

	@FXML
	private void initialize() {
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcTipoAnimal.setCellValueFactory(new PropertyValueFactory<>("tipoAnimal"));
		tvRaca.setItems(FXCollections.observableArrayList(racaDao.listar()));
		tvRaca.refresh();
	}

	public void atualizaListaRaca() {

		tvRaca.setItems(FXCollections.observableArrayList(racaDao.listar()));
		tvRaca.refresh();
	}

	@FXML
	void editar(ActionEvent event) {
		if (tvRaca.getSelectionModel().getSelectedItem() != null) {
			EstaticosParaRaca.raca = tvRaca.getSelectionModel().getSelectedItem();
			EstaticosParaRaca.isEditando = true;
		}
		Stage stageDono = (Stage) btEditar.getScene().getWindow();
		RacaDialogFactory racaDialog = new RacaDialogFactory(stageDono);

		boolean clicadoSalvar = racaDialog.showDialog();

		if (clicadoSalvar) {
			atualizaListaRaca();
		}
	}

	@FXML
	void excluir(ActionEvent event) {
		if (tvRaca.getSelectionModel().getSelectedItem() != null) {
			raca = tvRaca.getSelectionModel().getSelectedItem();
			racaDao.excluir(raca);
		}
		atualizaListaRaca();
	}

	@FXML
	void novo(ActionEvent event) {

		Stage stageDono = (Stage) btNovo.getScene().getWindow();
		RacaDialogFactory adicionaRacaDialog = new RacaDialogFactory(stageDono);

		boolean clicadoSalvar = adicionaRacaDialog.showDialog();

		if (clicadoSalvar) {
			atualizaListaRaca();
		}
	}

	@FXML
	void exibeRelatorio(ActionEvent event) {
		URL url = getClass().getResource("/br/com/unoesc/veterinaria/relatorios/RelatorioRaca.jasper");
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

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}
}

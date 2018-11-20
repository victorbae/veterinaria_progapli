package br.com.unoesc.veterinaria.controller;

import java.io.IOException;
import java.util.Date;

import br.com.unoesc.veterinaria.banco.VendaBanco;
import br.com.unoesc.veterinaria.dao.VendaDao;
import br.com.unoesc.veterinaria.dialogs.RelatorioVendaDialogFactory;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Venda;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaVenda;
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

public class ControllerVenda {

	@FXML
	private TableView<Venda> tvVendas;

	@FXML
	private TableColumn<Venda, Cliente> tcCliente;

	@FXML
	private TableColumn<Venda, Filial> tcFilial;

	@FXML
	private TableColumn<Venda, Double> tcValorDesconto;

	@FXML
	private TableColumn<Venda, Date> tcDataVenda;

	@FXML
	private TableColumn<Venda, Double> tcValorTotal;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnVisualizar;

	@FXML
	private Button btnExibeRelatorio;

	private VendaDao vendaDao = new VendaBanco();

	private Venda venda;

	@FXML
	private void initialize() {

		EstaticosParaVenda.isVisualizando = false;
		tcDataVenda.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
		tcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		tcFilial.setCellValueFactory(new PropertyValueFactory<>("filial"));
		tcValorDesconto.setCellValueFactory(new PropertyValueFactory<>("valorDesconto"));
		tcValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		tvVendas.setItems(FXCollections.observableArrayList(vendaDao.listar()));
		tvVendas.refresh();
	}

	@FXML
	void Visualizar(ActionEvent event) {
		if (tvVendas.getSelectionModel().getSelectedItem() != null) {
			EstaticosParaVenda.isVisualizando = true;
			EstaticosParaVenda.venda = tvVendas.getSelectionModel().getSelectedItem();
			chamaTelaNovaVenda();
		}
	}

	@FXML
	void Excluir(ActionEvent event) {
		if (tvVendas.getSelectionModel().getSelectedItem() != null) {
			venda = tvVendas.getSelectionModel().getSelectedItem();
			if (vendaDao.excluir(venda) == false) {
				EstaticosParaGeral.naoExcluir((Stage) btnExcluir.getScene().getWindow());
			}
		}
		atualizaLista();
	}

	@FXML
	void Novo(ActionEvent event) {
		EstaticosParaVenda.isVisualizando = false;
		chamaTelaNovaVenda();
	}

	public void atualizaLista() {
		tvVendas.setItems(FXCollections.observableArrayList(vendaDao.listar()));
		tvVendas.refresh();
	}

	private void chamaTelaNovaVenda() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/cadastro/CadastroVenda.fxml"));
		try {
			AnchorPane cursoView = (AnchorPane) loader.load();
			EstaticosParaGeral.bpPrincipalAux.setCenter(cursoView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	void exibeRelatorio(ActionEvent event) {
		Stage stageDono = (Stage) btnExibeRelatorio.getScene().getWindow();
		RelatorioVendaDialogFactory adicionaProdutoVendaDialog = new RelatorioVendaDialogFactory(stageDono);
		boolean clicadoSalvar = adicionaProdutoVendaDialog.showDialog();
	}
}
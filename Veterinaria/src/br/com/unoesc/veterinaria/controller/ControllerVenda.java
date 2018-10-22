package br.com.unoesc.veterinaria.controller;

import java.util.Date;

import br.com.unoesc.veterinaria.banco.VendaBanco;
import br.com.unoesc.veterinaria.dao.VendaDao;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Venda;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

	private VendaDao vendaDao = new VendaBanco();

	private Venda venda;

	@FXML
	private void initialize() {

		tcDataVenda.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
		tcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		tcFilial.setCellValueFactory(new PropertyValueFactory<>("filial"));
		tcValorDesconto.setCellValueFactory(new PropertyValueFactory<>("valorDesconto"));
		tcValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		tvVendas.setItems(FXCollections.observableArrayList(vendaDao.listar()));
	}

	@FXML
	void Visualizar(ActionEvent event) {
	}

	@FXML
	void Excluir(ActionEvent event) {
		if (tvVendas.getSelectionModel().getSelectedItem() != null) {
			venda = tvVendas.getSelectionModel().getSelectedItem();
			vendaDao.excluir(venda);
		}
		atualizaLista();
	}

	@FXML
	void Novo(ActionEvent event) {

	}

	public void atualizaLista() {
		tvVendas.setItems(FXCollections.observableArrayList(vendaDao.listar()));
		tvVendas.refresh();
	}

}

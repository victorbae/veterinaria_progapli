package br.com.unoesc.veterinaria.controller;

import br.com.unoesc.veterinaria.banco.ProdutoBanco;
import br.com.unoesc.veterinaria.dao.ProdutoDao;
import br.com.unoesc.veterinaria.dialogs.ProdutoDialogFactory;
import br.com.unoesc.veterinaria.dialogs.RelatorioProdutoDialogFactory;
import br.com.unoesc.veterinaria.model.Produto;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaProduto;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerProduto {

	@FXML
	private TableView<Produto> tvProdutos;

	@FXML
	private TableColumn<Produto, String> tcNome;

	@FXML
	private TableColumn<Produto, Double> tcQuantidade;

	@FXML
	private TableColumn<Produto, Double> tcValorEntrada;

	@FXML
	private TableColumn<Produto, Double> tcMargemLucro;

	@FXML
	private TableColumn<Produto, Integer> tcIdEstoque;

	@FXML
	private Button btnExibeRelatorio;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnEditar;

	Produto produto;

	ProdutoDao produtoDao = new ProdutoBanco();

	@FXML
	private void initialize() {
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeEstoque"));
		tcValorEntrada.setCellValueFactory(new PropertyValueFactory<>("valorEntrada"));
		tcMargemLucro.setCellValueFactory(new PropertyValueFactory<>("margemLucro"));
		tvProdutos.setItems(FXCollections.observableArrayList(produtoDao.listar()));
	}

	@FXML
	void Editar(ActionEvent event) {
		if (tvProdutos.getSelectionModel().getSelectedItem() != null) {
			EstaticosParaProduto.produto = tvProdutos.getSelectionModel().getSelectedItem();
			EstaticosParaProduto.isEditando = true;
		}
		Stage stageDono = (Stage) btnNovo.getScene().getWindow();
		ProdutoDialogFactory produtoDialog = new ProdutoDialogFactory(stageDono);

		boolean clicadoSalvar = produtoDialog.showDialog();

		if (clicadoSalvar) {
			atualizaLista();
		}
	}

	@FXML
	void Excluir(ActionEvent event) {
		if (tvProdutos.getSelectionModel().getSelectedItem() != null) {
			produto = tvProdutos.getSelectionModel().getSelectedItem();
			produtoDao.excluir(produto);
		}
		atualizaLista();
	}

	@FXML
	void Novo(ActionEvent event) {
		EstaticosParaProduto.isEditando = false;
		Stage stageDono = (Stage) btnNovo.getScene().getWindow();
		ProdutoDialogFactory produtoDialog = new ProdutoDialogFactory(stageDono);

		boolean clicadoSalvar = produtoDialog.showDialog();

		if (clicadoSalvar) {
			atualizaLista();
		}
	}

	@FXML
	void exibeRelatorio(ActionEvent event) {
		Stage stageDono = (Stage) btnExibeRelatorio.getScene().getWindow();
		RelatorioProdutoDialogFactory adicionaProdutoVendaDialog = new RelatorioProdutoDialogFactory(stageDono);

		adicionaProdutoVendaDialog.showDialog();

	}

	public void atualizaLista() {
		tvProdutos.setItems(FXCollections.observableArrayList(produtoDao.listar()));
		tvProdutos.refresh();
	}

}

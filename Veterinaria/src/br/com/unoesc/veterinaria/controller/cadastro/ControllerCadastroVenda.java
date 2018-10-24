package br.com.unoesc.veterinaria.controller.cadastro;

import java.sql.Date;
import java.util.List;

import org.controlsfx.control.textfield.TextFields;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.VendaBanco;
import br.com.unoesc.veterinaria.banco.VendaProdutoBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.VendaDao;
import br.com.unoesc.veterinaria.dao.VendaProdutoDao;
import br.com.unoesc.veterinaria.dialogs.AdicionaProdutoVendaDialogFactory;
import br.com.unoesc.veterinaria.model.Produto;
import br.com.unoesc.veterinaria.model.Venda;
import br.com.unoesc.veterinaria.model.VendaProduto;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaCliente;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaVenda;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerCadastroVenda {

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnLimpar;

	@FXML
	private TextField tfCliente;

	@FXML
	private DatePicker dtDataVenda;

	@FXML
	private TextField tfValorDesconto;

	@FXML
	private TextField tfValorTotal;

	@FXML
	private TableView<VendaProduto> tvCarinho;

	@FXML
	private TableColumn<VendaProduto, Produto> tcNomeProduto;

	@FXML
	private TableColumn<VendaProduto, Double> tcQuantidade;

	@FXML
	private TableColumn<VendaProduto, Double> tcValorUnitario;

	@FXML
	private TableColumn<VendaProduto, Double> tcValorTotal;

	@FXML
	private Button btnExcluirProduto;

	@FXML
	private Button btnAdicionarProduto;

	private Venda venda;

	private VendaProduto vendaProduto;

	private VendaDao vendaDao = new VendaBanco();

	private VendaProdutoDao vendaProdutoDao = new VendaProdutoBanco();

	private ClienteDao clienteDao = new ClienteBanco();

	@FXML
	private void initialize() {
//		tfValorTotal.setDisable(true);

		EstaticosParaVenda.tableViewCarinhoAux = tvCarinho;

		TextFields.bindAutoCompletion(tfCliente, clienteDao.listar());

		tcNomeProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
		tcQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tcValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
		tcValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		tvCarinho.setItems(FXCollections.observableArrayList(EstaticosParaVenda.carrinhoAux));
	}

	@FXML
	void Salvar(ActionEvent event) {
		populaVenda();
		vendaDao.inserir(venda);
		salvaCarrinho(EstaticosParaVenda.carrinhoAux);
	}

	@FXML
	void Cancelar(ActionEvent event) {

	}

	@FXML
	void Limpar(ActionEvent event) {
		limpaTudo();
	}

	@FXML
	void AdicionarProduto(ActionEvent event) {
		Stage stageDono = (Stage) btnAdicionarProduto.getScene().getWindow();
		AdicionaProdutoVendaDialogFactory adicionaProdutoVendaDialog = new AdicionaProdutoVendaDialogFactory(stageDono);

		boolean clicadoSalvar = adicionaProdutoVendaDialog.showDialog();

		if (clicadoSalvar) {
			atualizaListaCarinho();
		}
	}

	public static void atualizaListaCarinho() {
		EstaticosParaVenda.tableViewCarinhoAux
				.setItems(FXCollections.observableArrayList(EstaticosParaVenda.carrinhoAux));
		EstaticosParaVenda.tableViewCarinhoAux.refresh();
	}

	@FXML
	void ExcluirProduto(ActionEvent event) {
		if (tvCarinho.getSelectionModel().getSelectedItem() != null) {
			vendaProduto = tvCarinho.getSelectionModel().getSelectedItem();
			EstaticosParaVenda.carrinhoAux.remove(vendaProduto);
		}
		atualizaListaCarinho();
	}

	private void limpaTudo() {
		tfCliente.clear();
		tfValorDesconto.clear();
		tfValorTotal.clear();
		tvCarinho.getItems().removeAll(venda.getCarrinho());
	}

	public void populaVenda() {
		venda = new Venda();

		venda.setCliente(EstaticosParaCliente.achaClienteByName(tfCliente.getText()));
		venda.setDataVenda(Date.valueOf(dtDataVenda.getValue()));
		EstaticosParaCliente.achaClienteByName(tfCliente.getText()).getFilial().setIdFilial(1);
		venda.setFilial(EstaticosParaCliente.achaClienteByName(tfCliente.getText()).getFilial());
		venda.setValorDesconto(Double.valueOf(tfValorDesconto.getText()));
		venda.setValorTotal(Double.valueOf(tfValorTotal.getText()));

		colocaVendaNoCarrinho(EstaticosParaVenda.carrinhoAux);
	}

	public void colocaVendaNoCarrinho(List<VendaProduto> carrinho) {
		for (VendaProduto vendaProduto : carrinho) {
			vendaProduto.setVenda(venda);
		}
	}

	public void salvaCarrinho(List<VendaProduto> carrinho) {
		for (VendaProduto vendaProduto : carrinho) {
			vendaProdutoDao.inserir(vendaProduto);
		}
	}

}

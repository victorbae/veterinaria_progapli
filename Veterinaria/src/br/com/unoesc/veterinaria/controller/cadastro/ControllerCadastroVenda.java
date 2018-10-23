package br.com.unoesc.veterinaria.controller.cadastro;

import java.sql.Date;

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
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
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

	private VendaDao vendaDao = new VendaBanco();

	private VendaProduto vendaProduto;

	private VendaProdutoDao vendaProdutoDao = new VendaProdutoBanco();

	private ClienteDao clienteDao = new ClienteBanco();

	@FXML
	private void initialize() {
//		tfValorTotal.setDisable(true);

		EstaticosParaGeral.tvCarinhoAux = tvCarinho;

		TextFields.bindAutoCompletion(tfCliente, clienteDao.listar());

		tcNomeProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
		tcQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tcValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
		tcValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
//		tvCarinho.setItems(FXCollections.observableArrayList(EstaticosParaVenda.carrinhoAux));
	}

	@FXML
	void Salvar(ActionEvent event) {
		populaVenda();
		vendaDao.inserir(venda);
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
			vendaProduto = EstaticosParaVenda.vendaProduto;
		}
	}

// TODO Arrumar esta merda esse listar traz as parada NULL e nao pode
	public static void atualizaListaCarinho() {
		EstaticosParaGeral.tvCarinhoAux
				.setItems(FXCollections.observableArrayList(EstaticosParaVenda.venda.getCarrinho()));
		EstaticosParaGeral.tvCarinhoAux.refresh();
	}

	@FXML
	void ExcluirProduto(ActionEvent event) {
		if (tvCarinho.getSelectionModel().getSelectedItem() != null) {
			vendaProduto = tvCarinho.getSelectionModel().getSelectedItem();
			vendaProdutoDao.excluir(vendaProduto);
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
		venda = EstaticosParaVenda.venda;

		venda.setCliente(EstaticosParaCliente.achaClienteByName(tfCliente.getText()));
		venda.setDataVenda(Date.valueOf(dtDataVenda.getValue()));
//		venda.setFilial(filial);
		venda.setValorDesconto(Double.valueOf(tfValorDesconto.getText()));
		venda.setValorTotal(Double.valueOf(tfValorTotal.getText()));
	}

}

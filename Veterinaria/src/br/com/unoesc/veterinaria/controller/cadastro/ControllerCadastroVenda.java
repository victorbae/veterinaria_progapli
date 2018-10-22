package br.com.unoesc.veterinaria.controller.cadastro;

import org.controlsfx.control.textfield.TextFields;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.VendaBanco;
import br.com.unoesc.veterinaria.banco.VendaProdutoBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.VendaDao;
import br.com.unoesc.veterinaria.dao.VendaProdutoDao;
import br.com.unoesc.veterinaria.dialogs.AdicionaProdutoVendaDialogFactory;
import br.com.unoesc.veterinaria.model.Venda;
import br.com.unoesc.veterinaria.model.VendaProduto;
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
	private TableColumn<VendaProduto, String> tcNomeProduto;

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
		tfValorTotal.setDisable(true);
		TextFields.bindAutoCompletion(tfCliente, clienteDao.listar());

		tcNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nomeCompleto"));
		tcQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tcValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
		tcValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		tvCarinho.setItems(FXCollections.observableArrayList(vendaProdutoDao.listar()));
	}

	@FXML
	void Salvar(ActionEvent event) {
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
			atualizaLista();
			vendaProduto = EstaticosParaVenda.vendaProduto;
		}
	}

	private void atualizaLista() {
		tvCarinho.setItems(FXCollections.observableArrayList(vendaProdutoDao.listar()));
		tvCarinho.refresh();

	}

	@FXML
	void ExcluirProduto(ActionEvent event) {
		if (tvCarinho.getSelectionModel().getSelectedItem() != null) {
			vendaProduto = tvCarinho.getSelectionModel().getSelectedItem();
			vendaProdutoDao.excluir(vendaProduto);
		}
		atualizaLista();
	}

	private void limpaTudo() {
		tfCliente.clear();
		tfValorDesconto.clear();
		tfValorTotal.clear();
		tvCarinho.getItems().removeAll(vendaProdutoDao.listar());
	}

}

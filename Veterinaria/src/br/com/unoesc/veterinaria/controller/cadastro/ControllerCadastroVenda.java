package br.com.unoesc.veterinaria.controller.cadastro;

import java.io.IOException;
import java.util.List;

import org.controlsfx.control.textfield.TextFields;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.VendaBanco;
import br.com.unoesc.veterinaria.banco.VendaProdutoBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.VendaDao;
import br.com.unoesc.veterinaria.dao.VendaProdutoDao;
import br.com.unoesc.veterinaria.dialogs.AdicionaProdutoVendaDialogFactory;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Produto;
import br.com.unoesc.veterinaria.model.Venda;
import br.com.unoesc.veterinaria.model.VendaProduto;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaCliente;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaVenda;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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

	@FXML
	private Button btnAplicaDesconto;

	@FXML
	private Label lblDescontoAplicado;

	private Venda venda;

	private Cliente cliente;

	private VendaProduto vendaProduto;

	private VendaDao vendaDao = new VendaBanco();

	private VendaProdutoDao vendaProdutoDao = new VendaProdutoBanco();

	private ClienteDao clienteDao = new ClienteBanco();

	@FXML
	private void initialize() {

		tfValorTotal.setEditable(false);
		btnAplicaDesconto.setDisable(false);
		tfValorDesconto.setEditable(true);
		lblDescontoAplicado.setText("Nenhum desconto aplicado.");

		if (EstaticosParaVenda.isVisualizando) {
			venda = EstaticosParaVenda.venda;
			populaTela();
			tvCarinho.setItems(FXCollections.observableArrayList(vendaProdutoDao.listarPelaVenda(venda)));
			lblDescontoAplicado.setText("Desconto de R$" + venda.getValorDesconto() + " aplicado.");
			bloqueiaTudo(true);
		}

		EstaticosParaVenda.tfValorTotalAux = tfValorTotal;
		EstaticosParaVenda.tableViewCarinhoAux = tvCarinho;
		EstaticosParaVenda.tfValorDescontoAux = tfValorDesconto;

		TextFields.bindAutoCompletion(tfCliente, clienteDao.listar());

		tcNomeProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
		tcQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tcValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
		tcValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));

		if (!EstaticosParaVenda.isVisualizando) {
			tvCarinho.setItems(FXCollections.observableArrayList(EstaticosParaVenda.carrinhoAux));
			bloqueiaTudo(false);
		}
	}

	@FXML
	void Salvar(ActionEvent event) {
		if (!EstaticosParaVenda.isVisualizando) {
			populaVenda();
			vendaDao.inserir(venda);
			salvaCarrinho(EstaticosParaVenda.carrinhoAux);
			voltaTelaVenda();
		}
	}

	@FXML
	void Cancelar(ActionEvent event) {
		if (!EstaticosParaVenda.isVisualizando) {
			limpaTudo();
		}
		resetCarrinho();
		voltaTelaVenda();
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

	@FXML
	void ExcluirProduto(ActionEvent event) {
		if (tvCarinho.getSelectionModel().getSelectedItem() != null) {
			vendaProduto = tvCarinho.getSelectionModel().getSelectedItem();
			EstaticosParaVenda.carrinhoAux.remove(vendaProduto);
		}
		ControllerCadastroVenda.atualizaTotalVenda();
		atualizaListaCarinho();
	}

	@FXML
	void AplicarDesconto(ActionEvent event) {
		if (Double.valueOf(tfValorTotal.getText()) != null && Double.valueOf(tfValorDesconto.getText()) != null
				&& Double.valueOf(tfValorTotal.getText()) > Double.valueOf(tfValorDesconto.getText())) {
			Double valorSemDesconto = Double.valueOf(tfValorTotal.getText());
			Double desconto = Double.valueOf(tfValorDesconto.getText());
			Double valorComDesconto = valorSemDesconto - desconto;
			tfValorTotal.setText(valorComDesconto.toString());
			lblDescontoAplicado.setText("Desconto de R$" + desconto + " aplicado.");
			btnAplicaDesconto.setDisable(true);
			tfValorDesconto.setEditable(false);
			EstaticosParaVenda.venda.setValorDesconto(desconto);
		}
	}

	public static void atualizaListaCarinho() {
		EstaticosParaVenda.tableViewCarinhoAux
				.setItems(FXCollections.observableArrayList(EstaticosParaVenda.carrinhoAux));
		EstaticosParaVenda.tableViewCarinhoAux.refresh();
	}

	public static void atualizaTotalVenda() {
		if (EstaticosParaVenda.valorTotalVenda(EstaticosParaVenda.carrinhoAux) != null) {
			EstaticosParaVenda.tfValorTotalAux
					.setText(String.valueOf(EstaticosParaVenda.valorTotalVenda(EstaticosParaVenda.carrinhoAux)));
		}
	}

	private void limpaTudo() {
		if (!EstaticosParaVenda.isVisualizando) {
			tfCliente.clear();
			tfValorDesconto.clear();
			tfValorTotal.clear();
			dtDataVenda.setValue(null);
			resetCarrinho();
			atualizaListaCarinho();
		}
	}

	public void populaVenda() {
		venda = new Venda();
		cliente = new Cliente();
		cliente = EstaticosParaCliente.achaClienteByName(tfCliente.getText());

		venda.setCliente(cliente);
		venda.setDataVenda(dtDataVenda.getValue());
		venda.setFilial(cliente.getFilial());
		venda.setValorDesconto(EstaticosParaVenda.venda.getValorDesconto());
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

	public void resetCarrinho() {
		EstaticosParaVenda.carrinhoAux.clear();
	}

	private void voltaTelaVenda() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/Venda.fxml"));
		try {
			AnchorPane cursoView = (AnchorPane) loader.load();
			EstaticosParaGeral.bpPrincipalAux.setCenter(cursoView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void populaTela() {
		tfCliente.setText(venda.getCliente().getNomeCompleto());
		dtDataVenda.setValue(venda.getDataVenda());
		tfValorDesconto.setText(venda.getValorDesconto().toString());
		tfValorTotal.setText(venda.getValorTotal().toString());
	}

	public void bloqueiaTudo(boolean block) {
		tfCliente.setDisable(block);
		dtDataVenda.setDisable(block);
		tfValorDesconto.setDisable(block);
		tfValorTotal.setDisable(block);
		btnAdicionarProduto.setDisable(block);
		btnExcluirProduto.setDisable(block);
	}

}
